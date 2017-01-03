package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
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

		final String spawnPoint = args.<String>getOne("spawnPoint").get();
		String spawnValue = spawnPoint.replace("Spawn", "").replace("d", "D");
		String spawnName = "Spawn" + spawnValue;

		if (spawnValue.equalsIgnoreCase("Default")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + noRemoveDefaultSpawn));
		} else if (GeneralDataInteraction.getSpawnPointMap().keySet().contains(spawnName)) {

			GeneralDataInteraction.deleteSpawnPoint(spawnName);
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + removeSpawnPoint.replace("%spawnValue", spawnValue)));

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + spawnPointNotExist));
		}

		return CommandResult.success();
	}
}
