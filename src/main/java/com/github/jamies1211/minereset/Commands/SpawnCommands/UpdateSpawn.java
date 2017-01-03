package com.github.jamies1211.minereset.Commands.SpawnCommands;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 29-May-16.
 */
public class UpdateSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String spawnPoint = args.<String>getOne("spawnPoint").get();
		final double x = args.<Double>getOne("x").get();
		final double y = args.<Double>getOne("y").get();
		final double z = args.<Double>getOne("z").get();
		final String facing = args.<String>getOne("facing").get();

		String spawnValue = spawnPoint.replace("Spawn", "").replace("d", "D");

		if (src instanceof Player) {
			Player player = (Player) src;

			if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnValue)) {

				config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnX").setValue(x);
				config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnY").setValue(y);
				config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnZ").setValue(z);

				if (facing.equalsIgnoreCase("North") || facing.equalsIgnoreCase("South") || facing.equalsIgnoreCase("East") || facing.equalsIgnoreCase("West")) {
					config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnDirection").setValue(facing);
				} else {
					src.sendMessage(Text.of("direction not entered correctly so defaulting to North"));
					config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnDirection").setValue("North");
				}
				config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnWorld").setValue(player.getWorld().getUniqueId().toString());
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.UpdatedSpawnPoint
						.replace("%x%", Double.toString(x))
						.replace("%x%", Double.toString(y))
						.replace("%x%", Double.toString(z))
						.replace("%facing%", facing)));
				GeneralDataConfig.getConfig().get();

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.SpawnPointNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.PlayerOnlyCommand));
		}


		return CommandResult.success();
	}
}
