package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 29-May-16.
 */
public class RemoveSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String spawnPoint = args.<String>getOne("spawnPoint").get();
		String spawnValue = spawnPoint.replace("Spawn", "").replace("d", "D");

		if (spawnValue.equalsIgnoreCase("Default")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + NoRemoveDefaultSpawn));
		} else if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnValue)) {

			config.getNode("3 - Spawn").removeChild("Spawn" + spawnValue);
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + RemoveSpawnPoint.replace("%spawnValue", spawnValue)));
			GeneralDataConfig.getConfig().get();

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + SpawnPointNotExist));
		}

		return CommandResult.success();
	}
}
