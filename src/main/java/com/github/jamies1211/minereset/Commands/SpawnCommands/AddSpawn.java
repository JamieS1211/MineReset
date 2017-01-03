package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import ninja.leaping.configurate.ConfigurationNode;
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
public class AddSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final double x = args.<Double>getOne("x").get();
		final double y = args.<Double>getOne("y").get();
		final double z = args.<Double>getOne("z").get();
		final String facing = args.<String>getOne("facing").get();

		if (src instanceof Player) {
			Player player = (Player) src;
			int spawnPoint;

			for (spawnPoint = 1; spawnPoint <= 100; spawnPoint++) {
				if (!config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnPoint)) {
					break;
				}
			}

			if (spawnPoint < 100) {
				config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnX").setValue(x);
				config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnY").setValue(y);
				config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnZ").setValue(z);

				if (facing.equalsIgnoreCase("North") || facing.equalsIgnoreCase("South") || facing.equalsIgnoreCase("East") || facing.equalsIgnoreCase("West")) {
					config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnDirection").setValue(facing);
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(NoDirectionToSpawn));
					config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnDirection").setValue("North");
				}
				config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnWorld").setValue(player.getWorld().getUniqueId().toString());
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + AddSpawnPoint +
						" SpawnPoint: Spawn" + spawnPoint + " x:" + x + " y:" + y + " z:" + z + " facing:" + facing));
				GeneralDataConfig.getConfig().get();
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MaxSpawnCount));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + PlayerOnlyCommand));
		}


		return CommandResult.success();
	}
}
