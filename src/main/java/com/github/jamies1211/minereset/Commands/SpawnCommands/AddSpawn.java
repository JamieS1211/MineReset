package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Set;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 29-May-16.
 */
public class AddSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final double x = args.<Double>getOne("x").get();
		final double y = args.<Double>getOne("y").get();
		final double z = args.<Double>getOne("z").get();
		String facing = args.<String>getOne("facing").get();

		if (src instanceof Player) {
			Player player = (Player) src;
			int spawnPoint;
			Set<Object> spawnPointSet = GeneralDataInteraction.getSpawnPointMap().keySet();

			for (spawnPoint = 1; spawnPoint <= 100; spawnPoint++) {
				if (!spawnPointSet.contains("Spawn" + spawnPoint)) {
					break;
				}
			}

			if (spawnPoint < 100) {
				String spawnName = "Spawn" + spawnPoint;

				if (!(facing.equalsIgnoreCase("North")
						|| facing.equalsIgnoreCase("South")
						|| facing.equalsIgnoreCase("East")
						|| facing.equalsIgnoreCase("West"))) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(noDirectionToSpawn));
					facing = "North";
				}

				String worldUUIDString = player.getWorld().getUniqueId().toString();
				GeneralDataInteraction.setSpawnPoint(spawnName, x, y, z, facing, worldUUIDString);

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + addSpawnPoint
						.replace("%spawnName%", spawnName)
						.replace("%x%", Double.toString(x))
						.replace("%y%", Double.toString(y)
						.replace("%z%", Double.toString(z))
						.replace("%facing%", facing)
						.replace("%worldUUIDString%", worldUUIDString))));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + maxSpawnCount));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + playerOnlyCommand));
		}


		return CommandResult.success();
	}
}
