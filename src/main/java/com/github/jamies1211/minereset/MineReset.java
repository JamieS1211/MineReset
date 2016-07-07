package com.github.jamies1211.minereset;

/**
 * Created by Jamie on 27-May-16.
 */
import com.github.jamies1211.minereset.Actions.FillMineAction;
import com.github.jamies1211.minereset.Actions.SendMessages;
import com.github.jamies1211.minereset.Actions.TimeUntillFill;
import com.github.jamies1211.minereset.Actions.UpdateConfig;
import com.github.jamies1211.minereset.Commands.ConfigCommands.*;
import com.github.jamies1211.minereset.Commands.FillingCommands.ClearMine;
import com.github.jamies1211.minereset.Commands.FillingCommands.FillBlock;
import com.github.jamies1211.minereset.Commands.FillingCommands.FillMine;
import com.github.jamies1211.minereset.Commands.GroupCommands.DefineGroup;
import com.github.jamies1211.minereset.Commands.GroupCommands.DeleteGroup;
import com.github.jamies1211.minereset.Commands.GroupCommands.UpdateGroup;
import com.github.jamies1211.minereset.Commands.InfoCommands.*;
import com.github.jamies1211.minereset.Commands.MineSetupCommands.*;
import com.github.jamies1211.minereset.Commands.SpawnCommands.AddSpawn;
import com.github.jamies1211.minereset.Commands.SpawnCommands.ChangeSpawn;
import com.github.jamies1211.minereset.Commands.SpawnCommands.RemoveSpawn;
import com.github.jamies1211.minereset.Commands.SpawnCommands.UpdateSpawn;
import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.Task;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Plugin(id = "minereset", name = "MineReset", version = "1.0.4",
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

		taskBuilder.execute(new Runnable() {
			public void run() {
				cycle();
			}
		}).interval(1, TimeUnit.SECONDS).name("MineTick").submit(plugin);
	}

	@Listener
	public void gameLoadComplere(GameStartingServerEvent event) {
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
		UpdateConfig.update1to2();
		reload();
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

		final HashMap<List<String>, CommandSpec> subcommands = new HashMap<List<String>, CommandSpec>();
		subcommands.put(Arrays.asList("help"), CommandSpec.builder()
				.permission("minereset.help")
				.description(Text.of(Messages.HelpDescription))
				.extendedDescription(Text.of(Messages.HelpExtendedDescription))
				.executor(new MineHelp())
				.build());

		subcommands.put(Arrays.asList("time"), CommandSpec.builder()
				.permission("minereset.check.time")
				.description(Text.of(Messages.TimeDescription))
				.extendedDescription(Text.of(Messages.TimeExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new CheckTime())
				.build());

		subcommands.put(Arrays.asList("reload"), CommandSpec.builder()
				.permission("minereset.reload")
				.description(Text.of(Messages.ReloadDescription))
				.extendedDescription(Text.of(Messages.ReloadExtendedDescription))
				.executor(new ConfigReload())
				.build());

		subcommands.put(Arrays.asList("save"), CommandSpec.builder()
				.permission("minereset.save")
				.description(Text.of(Messages.SaveDescription))
				.extendedDescription(Text.of(Messages.SaveExtendedDescription))
				.executor(new ConfigSave())
				.build());

		subcommands.put(Arrays.asList("addspawn"), CommandSpec.builder()
				.permission("minereset.addspawn")
				.description(Text.of(Messages.AddSpawnDescription))
				.extendedDescription(Text.of(Messages.AddSpawnExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("x"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("y"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("z"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("facing"))))
				.executor(new AddSpawn())
				.build());

		subcommands.put(Arrays.asList("removespawn"), CommandSpec.builder()
				.permission("minereset.removespawn")
				.description(Text.of(Messages.RemoveSpawnDescription))
				.extendedDescription(Text.of(Messages.RemoveSpawnExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("spawnPoint"))))
				.executor(new RemoveSpawn())
				.build());

		subcommands.put(Arrays.asList("Updatespawn"), CommandSpec.builder()
				.permission("minereset.updatespawn")
				.description(Text.of(Messages.UpdateSpawnDescription))
				.extendedDescription(Text.of(Messages.UpdateSpawnExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("spawnPoint"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("x"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("y"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("z"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("facing"))))
				.executor(new UpdateSpawn())
				.build());

		subcommands.put(Arrays.asList("changespawn"), CommandSpec.builder()
				.permission("minereset.changespawn")
				.description(Text.of(Messages.ChangeSpawnDescription))
				.extendedDescription(Text.of(Messages.ChangeSpawnExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("mine"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("spawnPoint"))))
				.executor(new ChangeSpawn())
				.build());

		subcommands.put(Arrays.asList("clear"), CommandSpec.builder()
				.permission("minereset.clear")
				.description(Text.of(Messages.ClearDescription))
				.extendedDescription(Text.of(Messages.ClearExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new ClearMine())
				.build());

		subcommands.put(Arrays.asList("fill"), CommandSpec.builder()
				.permission("minereset.fill")
				.description(Text.of(Messages.FillDescription))
				.extendedDescription(Text.of(Messages.FillExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new FillMine())
				.build());

		subcommands.put(Arrays.asList("fillblock"), CommandSpec.builder()
				.permission("minereset.fillblock")
				.description(Text.of(Messages.FillblockDescription))
				.extendedDescription(Text.of(Messages.FillblockExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new FillBlock())
				.build());

		subcommands.put(Arrays.asList("definegroup"), CommandSpec.builder()
				.permission("minereset.define.group")
				.description(Text.of(Messages.DefineGroupDescription))
				.extendedDescription(Text.of(Messages.DefineGroupExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("group"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("resetTime"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("initialDelay"))))
				.executor(new DefineGroup())
				.build());

		subcommands.put(Arrays.asList("updategroup"), CommandSpec.builder()
				.permission("minereset.update.group")
				.description(Text.of(Messages.UpdateGroupDescription))
				.extendedDescription(Text.of(Messages.UpdateGroupExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("group"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("resetTime"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("initialDelay"))))
				.executor(new UpdateGroup())
				.build());

		subcommands.put(Arrays.asList("deletegroup"), CommandSpec.builder()
				.permission("minereset.define.group")
				.description(Text.of(Messages.DeleteGroupDescription))
				.extendedDescription(Text.of(Messages.DeleteGroupExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("group"))),
						GenericArguments.onlyOne(GenericArguments.remainingJoinedStrings(Text.of("safe"))))
				.executor(new DeleteGroup())
				.build());

		subcommands.put(Arrays.asList("definemine"), CommandSpec.builder()
				.permission("minereset.define.mine")
				.description(Text.of(Messages.DefineMineDescription))
				.extendedDescription(Text.of(Messages.DefineMineExtendedDescription))
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

		subcommands.put(Arrays.asList("redefinemine"), CommandSpec.builder()
				.permission("minereset.redefine.mine")
				.description(Text.of(Messages.RedefineMineDescription))
				.extendedDescription(Text.of(Messages.RedefineMineExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("group"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("x1"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("y1"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("z1"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("x2"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("y2"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("z2"))))
				.executor(new RedefineMine())
				.build());

		subcommands.put(Arrays.asList("deletemine"), CommandSpec.builder()
				.permission("minereset.delete.mine")
				.description(Text.of(Messages.DeleteMineDescription))
				.extendedDescription(Text.of(Messages.DeleteMineExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new DeleteMine())
				.build());

		subcommands.put(Arrays.asList("list"), CommandSpec.builder()
				.permission("minereset.list")
				.description(Text.of(Messages.ListDescription))
				.extendedDescription(Text.of(Messages.ListExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("type"))))
				.executor(new ListContents())
				.build());

		subcommands.put(Arrays.asList("info"), CommandSpec.builder()
				.permission("minereset.details")
				.description(Text.of(Messages.InfoDescription))
				.extendedDescription(Text.of(Messages.InfoExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("type"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new Details())
				.build());

		subcommands.put(Arrays.asList("addore"), CommandSpec.builder()
				.permission("minereset.mine.addore")
				.description(Text.of(Messages.AddoreDescription))
				.extendedDescription(Text.of(Messages.AddoreExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("percentage"))))
				.executor(new AddOre())
				.build());

		subcommands.put(Arrays.asList("updateore"), CommandSpec.builder()
				.permission("minereset.mine.updateore")
				.description(Text.of(Messages.UpdateoreDescription))
				.extendedDescription(Text.of(Messages.UpdateoreExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))),
						GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("percentage"))))
				.executor(new UpdateOre())
				.build());

		subcommands.put(Arrays.asList("updatefallback"), CommandSpec.builder()
				.permission("minereset.mine.updatefallback")
				.description(Text.of(Messages.UpdatefallbackDescription))
				.extendedDescription(Text.of(Messages.UpdatefallbackExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
				.executor(new UpdateFallback())
				.build());

		subcommands.put(Arrays.asList("listreminders"), CommandSpec.builder()
				.permission("minereset.mine.listremindtime")
				.description(Text.of(Messages.ListReminderDescription))
				.extendedDescription(Text.of(Messages.ListReminderExtendedDescription))
				.executor(new ListReminder())
				.build());

		subcommands.put(Arrays.asList("addremindtime"), CommandSpec.builder()
				.permission("minereset.mine.addremindtime")
				.description(Text.of(Messages.AddRemindTimeDescription))
				.extendedDescription(Text.of(Messages.AddRemindTimeExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("time"))))
				.executor(new AddRemindTime())
				.build());

		subcommands.put(Arrays.asList("removeremindtime"), CommandSpec.builder()
				.permission("minereset.mine.removeremindtime")
				.description(Text.of(Messages.RemoveRemindTimeDescription))
				.extendedDescription(Text.of(Messages.RemoveRemindTimeExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("time"))))
				.executor(new RemoveRemindTime())
				.build());

		subcommands.put(Arrays.asList("setupsmartfill"), CommandSpec.builder()
				.permission("minereset.mine.setup.smartfill")
				.description(Text.of(Messages.MineSetupSmartFillDescription))
				.extendedDescription(Text.of(Messages.MineSetupSmartFillExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))),
						GenericArguments.onlyOne(GenericArguments.bool(Text.of("enabled"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("radius"))),
						GenericArguments.onlyOne(GenericArguments.bool(Text.of("onlyAir"))))
				.executor(new MineSetupSmartFill())
				.build());

		subcommands.put(Arrays.asList("addairblock"), CommandSpec.builder()
				.permission("minereset.mine.addairblock")
				.description(Text.of(Messages.AddAirBlockDescription))
				.extendedDescription(Text.of(Messages.AddAirBlockExtendedDescription))
				.executor(new AddAirBlock())
				.build());

		subcommands.put(Arrays.asList("removeairblock"), CommandSpec.builder()
				.permission("minereset.mine.removeairblock")
				.description(Text.of(Messages.RemoveAirBlockDescription))
				.extendedDescription(Text.of(Messages.RemoveAirBlockExtendedDescription))
				.executor(new RemoveAirBlock())
				.build());

		subcommands.put(Arrays.asList("updateChatSettings"), CommandSpec.builder()
				.permission("minereset.update.chatsettings")
				.description(Text.of(Messages.UpdateChatSettingsDescription))
				.extendedDescription(Text.of(Messages.UpdateChatSettingsExtendedDescription))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("type"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("option"))))
				.executor(new UpdateChatSettings())
				.build());

		final CommandSpec mineCommand = CommandSpec.builder()
				.permission("minereset.help")
				.description(Text.of(Messages.HelpDescription))
				.extendedDescription(Text.of(Messages.HelpExtendedDescription))
				.executor(new MineHelp())
				.children(subcommands)
				.build();
		Sponge.getCommandManager().register(this, mineCommand, "mine");
	}

	private void setupconfig() {
		this.config.getNode("1 - ConfigMode").setValue(2);
		this.config.getNode("2 - RemindSecondList").setValue("1, 5, 15, 30, 60, 120, 180, 300");
		this.config.getNode("3 - Spawn", "SpawnDefault", "SpawnX").setValue(0.5);
		this.config.getNode("3 - Spawn", "SpawnDefault", "SpawnY").setValue(85.0);
		this.config.getNode("3 - Spawn", "SpawnDefault", "SpawnZ").setValue(0.5);
		this.config.getNode("3 - Spawn", "SpawnDefault", "SpawnDirection").setValue("West");
		this.config.getNode("3 - Spawn", "SpawnDefault", "SpawnWorld").setValue(Sponge.getServer().getDefaultWorld().get().getUniqueId().toString());
		this.config.getNode("5 - Lists", "AirBlocks").setValue(
				"\"minecraft:torch\", \"minecraft:redstone_torch\", \"minecraft:redstone_wire\", \"minecraft:powered_repeater\", \"minecraft:unpowered_repeater\", \"minecraft:powered_comparator\",\n" +
				"\"minecraft:unpowered_comparator\", \"minecraft:wooden_button\", \"minecraft:stone_button\", \"minecraft:lever\", \"minecraft:tripwire_hook\", \"minecraft:stone_pressure_plate\",\n" +
				"\"minecraft:wooden_pressure_plate\", \"minecraft:light_weighted_pressure_plate\", \"minecraft:heavy_weighted_pressure_plate\", \"minecraft:trapdoor\", \"minecraft:iron_trapdoor\",\n" +
				"\"minecraft:fence_gate\", \"minecraft:spruce_fence_gate\", \"minecraft:birch_fence_gate\", \"minecraft:jungle_fence_gate\", \"minecraft:dark_oak_fence_gate\", \"minecraft:acacia_fence_gate\",\n" +
				"\"minecraft:wooden_door\", \"minecraft:iron_door\", \"minecraft:spruce_door\", \"minecraft:birch_door\", \"minecraft:jungle_door\", \"minecraft:acacia_door\", \"minecraft:dark_oak_door\", \n" +
				"\"minecraft:rail\", \"minecraft:golden_rail\", \"minecraft:detector_rail\", \"minecraft:activator_rail\", \"minecraft:tallgrass\", \"minecraft:sapling\", \"minecraft:deadbush\",\n" +
				"\"minecraft:yellow_flower\", \"minecraft:red_flower\", \"minecraft:brown_mushroom\", \"minecraft:red_mushroom\", \"minecraft:ladder\", \"minecraft:snow_layer\", \"minecraft:fence\",\n" +
				"\"minecraft:nether_brick_fence\", \"minecraft:iron_bars\", \"minecraft:glass_pane\", \"minecraft:vine\", \"minecraft:waterlily\", \"minecraft:cobblestone_wall\", \"minecraft:anvil\",\n" +
				"\"minecraft:stained_glass_pane\", \"minecraft:carpet\", \"minecraft:double_plant\", \"minecraft:wall_sign\", \"minecraft:standing_sign\", \"minecraft:skull\", \"minecraft:brewing_stand\",\n" +
				"\"minecraft:skull\", \"minecraft:standing_banner\"");
		this.config.getNode("6 - ChatSettings", "FillingText").setValue("1");
		this.config.getNode("6 - ChatSettings", "ReminderText").setValue("2");

		// 0 = disabled
		// 1 = chat
		// 2 = action bar
		// 3 = chat & action bar
		save();
	}

	public void save() {
		try {
			getConfigManager().save(this.config);
		} catch (final IOException e) {
			getLogger().info("Failed to save config file!");
		}
		this.reload();
	}

	public void reload() {
		try {
			this.config = getConfigManager().load();
			getLogger().info("File Loaded");
			remindTimes = new ArrayList<>(Arrays.asList(config.getNode("2 - RemindSecondList").getString().split(", ")));
			airBlocks = new ArrayList<>(Arrays.asList(config.getNode("5 - Lists", "AirBlocks").getString().split(", ")));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public int secondsSinceStart;

	public static List<String> remindTimes;

	public static List<String> airBlocks;

	public void cycle() {
		if (Sponge.getServer().getOnlinePlayers().size() > 0) {

			for (final Object groupObject: config.getNode("4 - MineGroups").getChildrenMap().keySet()) {

				int timeUntilNextFill = TimeUntillFill.getTimeUntilFill(groupObject.toString());
				Set<Object> listOfMines = new TreeSet<>(config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().keySet());
				listOfMines.remove("resetTime");
				listOfMines.remove("initialDelay");

				// 0 = disabled
				// 1 = chat
				// 2 = action bar
				// 4 = title

				if (this.remindTimes.contains(Integer.toString(timeUntilNextFill))) { // If time before group of mines reset is on remind time list send messages.

					int reminderChatType = this.config.getNode("6 - ChatSettings", "ReminderText").getInt();

					if (listOfMines.size() > 0) {

						if (!SendMessages.messageToAllPlayers(reminderChatType, Messages.MinePrefix + "&l" + listOfMines +
								" " + Messages.WillResetIn + " " + SecondsToString.secondsToTimeString(timeUntilNextFill)) &&
								reminderChatType != 0) {
							MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.InvalidRemindChatType));
						}

						MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix +
								listOfMines + " " + Messages.WillResetIn + " " + SecondsToString.secondsToTimeString(timeUntilNextFill)));

					}

				} else if (timeUntilNextFill == 0) { // If time before group of mines should reset is 0 reset all mines in group.
					for (final Object mineObject : listOfMines) {
						FillMineAction.fill(groupObject.toString(), mineObject.toString(), null, null);
					}

					if (listOfMines.size() > 0) {

						int fillingChatType = this.config.getNode("6 - ChatSettings", "FillingText").getInt();

						if (listOfMines.size() > 1) {

							if (!SendMessages.messageToAllPlayers(fillingChatType, Messages.MinePrefix + listOfMines + " " + Messages.ResetingNowPlural) &&
									fillingChatType != 0) {
								MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.InvalidFillChatType));
							}

							MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix +
									listOfMines + " " + Messages.ResetingNowPlural));


						} else {

							if (!SendMessages.messageToAllPlayers(fillingChatType, Messages.MinePrefix + listOfMines + " " + Messages.ResetingNowSingular) &&
									fillingChatType != 0) {
								MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.InvalidFillChatType));
							}

							MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix +
									listOfMines + " " + Messages.ResetingNowSingular));

						}
					}

				}
			}
		} else {
			if (secondsSinceStart % 300 == 0) {
				MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.NoPlayerOnline));
			}
		}
		secondsSinceStart++;
	}
}