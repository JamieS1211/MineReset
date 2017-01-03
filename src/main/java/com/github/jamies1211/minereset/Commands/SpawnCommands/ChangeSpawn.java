package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Actions.GetMineGroup;
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
 * Created by Jamie on 06-Jul-16.
 */
public class ChangeSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String mine = args.<String>getOne("mine").get().toUpperCase();
		final String spawnPoint = args.<String>getOne("spawnPoint").get();
		String spawnValue = spawnPoint.replace("Spawn", "").replace("d", "D");

		String group = GetMineGroup.getMineGroup(mine);

		if (group != null) {

			if (!config.getNode("4 - MineGroups", group, mine, "SpawnPoint").getString().equalsIgnoreCase(spawnValue)) {
				if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnValue)) {
					config.getNode("4 - MineGroups", group, mine, "SpawnPoint").setValue(spawnValue);
					GeneralDataConfig.getConfig().get();
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineSpawnChanged
							.replace("%mine%", mine)
							.replace("%spawnValue%", spawnValue)));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + SpawnPointNotExist));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + SpawnPointAlreadyInUse.replace("%mine%", mine)));
			}

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineDoesNotExist.replace("%mine%", mine)));
		}

		return CommandResult.success();
	}
}
