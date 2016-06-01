package com.github.jamies1211.minereset.Commands;

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
public class MineSetSpawn implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final double x = args.<Double>getOne("x").get();
		final double y = args.<Double>getOne("y").get();
		final double z = args.<Double>getOne("z").get();
		final String facing = args.<String>getOne("facing").get();

		if (src instanceof Player) {
			Player player = (Player) src;


			config.getNode("3 - Spawn", "SpawnX").setValue(x);
			config.getNode("3 - Spawn", "SpawnY").setValue(y);
			config.getNode("3 - Spawn", "SpawnZ").setValue(z);

			if (facing.equalsIgnoreCase("North") || facing.equalsIgnoreCase("South") || facing.equalsIgnoreCase("East") || facing.equalsIgnoreCase("West")) {
				config.getNode("3 - Spawn", "SpawnDirection").setValue(facing);
			} else {
				src.sendMessage(Text.of("direction not entered correctly so defaulting to North"));
				config.getNode("3 - Spawn", "SpawnDirection").setValue("North");
			}
			config.getNode("3 - Spawn", "SpawnWorld").setValue(player.getWorld().getUniqueId().toString());
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.SetSpawnPoint +  " x:" + x + " y:" + y + " z:" + z + " facing:" + facing));

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.PlayerOnlyCommand));
		}


		return CommandResult.success();
	}
}
