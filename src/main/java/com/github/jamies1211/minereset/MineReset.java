package com.github.jamies1211.minereset;

/**
 * Created by Jamie on 27-May-16.
 */
import com.github.jamies1211.minereset.Actions.FillMineAction;
import com.github.jamies1211.minereset.Actions.TimeUntillFill;
import com.github.jamies1211.minereset.Commands.*;
import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.Task;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Plugin(id = "minereset", name = "MineReset", version = "1.0",
		description = "Resets mines",
		authors = {"JamieS1211"},
		url = "http://pixelmonweb.officialtjp.com")
public class MineReset {

	Scheduler scheduler = Sponge.getScheduler();
	Task.Builder taskBuilder = scheduler.createTaskBuilder();

	@Inject
	private Logger logger;
	private ConfigurationNode config;
	public static MineReset plugin;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private File defaultConfig;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private ConfigurationLoader<CommentedConfigurationNode> configManager;

	public Logger getLogger() {
		return this.logger;
	}

	@Listener
	public void onServerLoadComplete(GameLoadCompleteEvent event) {
		plugin = this;
		reload();

		taskBuilder.execute(new Runnable() {
			public void run() {
				cycle();
			}
		}).interval(1, TimeUnit.SECONDS).name("MineTick").submit(plugin);
	}

	public ConfigurationNode getConfig() {
		return this.config;
	}

	public File getDefaultConfig() {
		return this.defaultConfig;
	}

	public ConfigurationLoader<CommentedConfigurationNode> getConfigManager() {
		return this.configManager;
	}

	@Listener
	public void onServerStart(GameInitializationEvent event) {
		getLogger().info("Mine reset has started.");

		//EXTENT_BUFFER_FACTORY = Sponge.getRegistry().getExtentBufferFactory();

		final HashMap<List<String>, CommandSpec> subcommands = new HashMap<List<String>, CommandSpec>();
		subcommands.put(Arrays.asList("help"), CommandSpec.builder()
				.permission("minereset.help")
				.description(Text.of("Shows mine command help"))
				.extendedDescription(Text.of("Use this command to list all the safari sub commands and how to use them"))
				.executor(new MineHelp())
				.build());

		subcommands.put(Arrays.asList("reload"), CommandSpec.builder()
				.permission("minereset.reload")
				.description(Text.of("Reloads mine config"))
				.extendedDescription(Text.of("Use this to reload the config"))
				.executor(new ConfigReload())
				.build());

		subcommands.put(Arrays.asList("save"), CommandSpec.builder()
				.permission("minereset.save")
				.description(Text.of("Saves the mine config being used over one in folder"))
				.extendedDescription(Text.of("Use this command to save mine config"))
				.executor(new ConfigSave())
				.build());

		subcommands.put(Arrays.asList("setspawn"), CommandSpec.builder()
				.permission("minereset.setspawn")
				.description(Text.of("Saves the global mine spawn"))
				.extendedDescription(Text.of("Use this command to save the spot players are sent to when they are in a mine on mine reset"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("x"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("y"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("z"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("facing"))))
				.executor(new MineSetSpawn())
				.build());

		subcommands.put(Arrays.asList("time"), CommandSpec.builder()
				.permission("minereset.check.time")
				.description(Text.of("Check time remaining before mine resets"))
				.extendedDescription(Text.of("/mine time [mine]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new CheckTime())
				.build());

		subcommands.put(Arrays.asList("clear"), CommandSpec.builder()
				.permission("minereset.clear")
				.description(Text.of("Completely empty a min"))
				.extendedDescription(Text.of("/mine clear [mine]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new ClearMine())
				.build());

		subcommands.put(Arrays.asList("fill"), CommandSpec.builder()
				.permission("minereset.fill")
				.description(Text.of("Manually fill a mine"))
				.extendedDescription(Text.of("/mine fill [mine]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new FillMine())
				.build());

		subcommands.put(Arrays.asList("definegroup"), CommandSpec.builder()
				.permission("minereset.define.group")
				.description(Text.of("Define a new mine group"))
				.extendedDescription(Text.of("/mine definegroup [group]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("group"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("resetTime"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("initialDelay"))))
				.executor(new DefineGroup())
				.build());

		subcommands.put(Arrays.asList("definemine"), CommandSpec.builder()
				.permission("minereset.define.mine")
				.description(Text.of("Define a new mine"))
				.extendedDescription(Text.of("/mine definemine [group] [name] [x1] [y1] [z1] [x2] [y2] [z2]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("group"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("x1"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("y1"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("z1"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("x2"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("y2"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("z2"))))
				.executor(new DefineMine())
				.build());

		subcommands.put(Arrays.asList("addore"), CommandSpec.builder()
				.permission("minereset.mine.addore")
				.description(Text.of("Adds a new ore to a mine"))
				.extendedDescription(Text.of("/mine addore [name] [percentage]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("percentage"))))
				.executor(new AddOre())
				.build());

		final CommandSpec safariCommand = CommandSpec.builder()
				.permission("minereset.help")
				.description(Text.of("Shows mine command help"))
				.extendedDescription(Text.of("Use this command to list all the safari sub commands and how to use them"))
				.executor(new MineHelp())
				.children(subcommands)
				.build();
		Sponge.getCommandManager().register(this, safariCommand, "mine");


		try {
			if (!defaultConfig.exists()) {
				defaultConfig.createNewFile();
				config = configManager.load();
				configManager.save(config);
				setupconfig();
				save();
				getLogger().info("Created new configuration file as none detected!");
			} else {
				getLogger().info("Detected configuration file and loaded");
			}
			config = configManager.load();

		} catch (IOException exception) {
			getLogger().info("The default configuration could not be loaded or created!");
		}
	}

	private void setupconfig() {
		this.config.getNode("1 - ConfigMode").setValue(1);
		this.config.getNode("2 - RemindSecondList").setValue("1, 5, 15, 30, 60, 120, 180, 300");
		this.config.getNode("3 - Spawn", "SpawnX").setValue(3.5);
		this.config.getNode("3 - Spawn", "SpawnY").setValue(85);
		this.config.getNode("3 - Spawn", "SpawnZ").setValue(9.5);
		this.config.getNode("3 - Spawn", "SpawnDirection").setValue("West");
		save();
	}

	public void save() {
		try {
			getConfigManager().save(this.config);
		} catch (final IOException e) {
			getLogger().info("Failed to save config file!");
		}
	}

	public void reload() {
		try {
			this.config = getConfigManager().load();
			getLogger().info("File Loaded");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public int secondsSinceStart;

	public void cycle() {

		final String configReminderTimes = config.getNode("2 - RemindSecondList").getString();
		final List<String> remindTimes = new ArrayList<String>(Arrays.asList(configReminderTimes.split(", ")));

		for (final Object groupObject: config.getNode("4 - MineGroups").getChildrenMap().keySet()) {

			int timeUntilNextFill = TimeUntillFill.getTimeUntilFill(groupObject.toString());
			Set<Object> listOfMines = new TreeSet<>(config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().keySet());
			listOfMines.remove("resetTime");
			listOfMines.remove("initialDelay");

			if(remindTimes.contains(Integer.toString(timeUntilNextFill))) { // If time before group of mines reset is on remind time list send messages.

				for (Player player : Sponge.getServer().getOnlinePlayers()) {
					player.sendMessage(ChatTypes.ACTION_BAR, TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &e&l" +
							listOfMines + " will reset in: " + SecondsToString.secondsToTimeString(timeUntilNextFill)));
				}

				MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &e&l" +
						listOfMines + " will reset in: " + SecondsToString.secondsToTimeString(timeUntilNextFill)));


			} else if (timeUntilNextFill == 0) { // If time before group of mines should reset is 0 reset all mines in group.
				if (Sponge.getServer().getOnlinePlayers().size() > 0) {
					for (final Object mineObject : listOfMines) {
						FillMineAction.fill(groupObject.toString(), mineObject.toString(), null);
					}
					MessageChannel.TO_ALL.send(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &e" +
							listOfMines + " are resetting now"));
				} else {
					MessageChannel.TO_ALL.send(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &e" +
							listOfMines + " won't reset now as no player is online"));
				}
			}
		}
		secondsSinceStart++;
	}
}