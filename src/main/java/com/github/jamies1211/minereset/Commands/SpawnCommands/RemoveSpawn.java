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
 * Created by Jamie on 29-May-16.
 */
public class RemoveSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String spawnPoint = args.<String>getOne("spawnPoint").get();
		String spawnValue = spawnPoint.replace("Spawn", "").replace("d", "D");

		if (spawnValue.equalsIgnoreCase("Default")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.NoRemoveDefaultSpawn));
		} else if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnValue)) {

			config.getNode("3 - Spawn").removeChild("Spawn" + spawnValue);
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.RemoveSpawnPoint.replace("%spawnValue", spawnValue)));
			MineReset.plugin.save();

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.SpawnPointNotExist));
		}

		return CommandResult.success();
	}
}
