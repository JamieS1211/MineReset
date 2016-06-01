package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.Actions.FillMineAction;
import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 01-Jun-16.
 */
public class FillBlock implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		String mine = args.<String>getOne("name").get();
		mine = mine.toUpperCase();

		String group = null;

		if (src instanceof Player) {
			Player player = (Player) src;

			for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
				if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(mine)) {
					group = groupObject.toString();
				}
			}

			if (group == null) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + mine + " " + Messages.MineDoesNotExist));
			} else {
				String block = player.getLocation().sub(0, 1, 0).getBlock().toString();
				FillMineAction.fill(group, mine, block);
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + mine + " " + Messages.MineFilledBlock + " " + block));
			}
		} else {

		}

		return CommandResult.success();
	}
}