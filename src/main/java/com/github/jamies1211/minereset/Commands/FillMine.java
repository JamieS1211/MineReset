package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.Actions.FillMineAction;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 28-May-16.
 */
public class FillMine implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		String mine = args.<String>getOne("name").get();
		mine = mine.toUpperCase();

		String group = null;

		for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
			if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(mine)) {
				group = groupObject.toString();
			}
		}

		if (group == null) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &e" + mine + " does not exist"));
		} else {

			FillMineAction.fill(group, mine, "minecraft:air");
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &e" + mine + " has been filled"));
		}

		return CommandResult.success();
	}
}