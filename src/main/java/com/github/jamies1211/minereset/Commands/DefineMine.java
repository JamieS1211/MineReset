package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

/**
 * Created by Jamie on 28-May-16.
 */
public class DefineMine implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String group = args.<String>getOne("group").get();
		final String mine = args.<String>getOne("name").get();
		final int x1 = args.<Integer>getOne("x1").get();
		final int y1 = args.<Integer>getOne("y1").get();
		final int z1 = args.<Integer>getOne("z1").get();
		final int x2 = args.<Integer>getOne("x2").get();
		final int y2 = args.<Integer>getOne("y2").get();
		final int z2 = args.<Integer>getOne("z2").get();


		if (src instanceof Player) {

			Player player = (Player) src;

			if (config.getNode("4 - MineGroups").getChildrenMap().containsKey(group)) {
				Boolean alreadyExists = false;

				for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
					if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(mine)) {
						alreadyExists = true;
						src.sendMessage(Text.of("A mine with this name already exists"));
					}
				}

				if (!alreadyExists) {
					config.getNode("4 - MineGroups", group, mine, "pos1", "x").setValue(x1);
					config.getNode("4 - MineGroups", group, mine, "pos1", "y").setValue(y1);
					config.getNode("4 - MineGroups", group, mine, "pos1", "z").setValue(z1);
					config.getNode("4 - MineGroups", group, mine, "pos2", "x").setValue(x2);
					config.getNode("4 - MineGroups", group, mine, "pos2", "y").setValue(y2);
					config.getNode("4 - MineGroups", group, mine, "pos2", "z").setValue(z2);
					config.getNode("4 - MineGroups", group, mine, "world").setValue(player.getWorld().toString());
					config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").setValue("minecraft:stone[variant=stone]");
					MineReset.plugin.save();
					src.sendMessage(Text.of("You have defined a new mine"));
				}

			} else {
				src.sendMessage(Text.of("The mine group you listed does not exist"));
			}
		} else {
			src.sendMessage(Text.of("This command must be run by a player"));
		}

		return CommandResult.success();
	}
}