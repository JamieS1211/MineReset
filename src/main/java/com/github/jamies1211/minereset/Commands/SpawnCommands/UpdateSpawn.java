package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import com.github.jamies1211.minereset.Messages;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 29-May-16.
 */
public class UpdateSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String spawnPoint = args.<String>getOne("spawnPoint").get();
		final double x = args.<Double>getOne("x").get();
		final double y = args.<Double>getOne("y").get();
		final double z = args.<Double>getOne("z").get();
		String facing = args.<String>getOne("facing").get();

		String spawnValue = spawnPoint.replace("Spawn", "").replace("d", "D");

		if (src instanceof Player) {
			Player player = (Player) src;

			if (GeneralDataInteraction.getSpawnPointMap().keySet().contains("Spawn" + spawnValue)) {

				String spawnName = "Spawn" + spawnValue;

				if (!(facing.equalsIgnoreCase("North")
						|| facing.equalsIgnoreCase("South")
						|| facing.equalsIgnoreCase("East")
						|| facing.equalsIgnoreCase("West"))) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.noDirectionToSpawn));
					facing = "North";
				}

				String worldUUIDString = player.getWorld().getUniqueId().toString();
				GeneralDataInteraction.setSpawnPoint(spawnName, x, y, z, facing, worldUUIDString);

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + updatedSpawnPoint
						.replace("%x%", Double.toString(x))
						.replace("%x%", Double.toString(y))
						.replace("%x%", Double.toString(z))
						.replace("%facing%", facing)
						.replace("%worldUUIDString", worldUUIDString)));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + spawnPointNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + playerOnlyCommand));
		}


		return CommandResult.success();
	}
}
