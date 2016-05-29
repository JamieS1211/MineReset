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
public class AddOre implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String mine = args.<String>getOne("name").get();
		final double percentage = args.<Double>getOne("percentage").get();

		Boolean alreadyExists = false;
		String group = null;
		if (src instanceof Player) {

			Player player = (Player) src;

			for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
				if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(mine)) {
					alreadyExists = true;
					group = groupObject.toString();
				}
			}

			if (alreadyExists) {

				String block = player.getLocation().sub(0, 1, 0).getBlock().toString();

				int currentSize = config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().size();

				config.getNode("4 - MineGroups", group, mine, "ores", currentSize, "BlockState").setValue(block);
				config.getNode("4 - MineGroups", group, mine, "ores", currentSize, "percentage").setValue(percentage);
				MineReset.plugin.save();
				src.sendMessage(Text.of("You have added " + block + " to mine: " + mine + " at " + percentage + "%"));
			} else {
				src.sendMessage(Text.of("The mine you listed does not exist: " + mine));
			}
		} else {
			src.sendMessage(Text.of("This command must be run by a player"));
		}

		return CommandResult.success();
	}
}