package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 06-Jul-16.
 */
public class ChangeSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String mine = args.<String>getOne("mine").get().toUpperCase();
		final String spawnPoint = args.<String>getOne("spawnPoint").get();
		String spawnValue = spawnPoint.replace("Spawn", "").replace("d", "D");

		String group = null;

		for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
			if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(mine)) {
				group = groupObject.toString();
			}
		}

		if (group != null) {

			if (!config.getNode("4 - MineGroups", group, mine, "SpawnPoint").getString().equalsIgnoreCase(spawnValue)) {
				if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnValue)) {
					config.getNode("4 - MineGroups", group, mine, "SpawnPoint").setValue(spawnValue);
					MineReset.plugin.save();
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + mine + " " + Messages.MineSpawnChanged + " " + "Spawn" + spawnValue));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.SpawnPointNotExist));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + mine + " " + Messages.SpawnPointAlreadyInUse));
			}

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + mine + " " + Messages.MineDoesNotExist));
		}

		return CommandResult.success();
	}
}
