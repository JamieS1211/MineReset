package com.github.jamies1211.minereset.Commands.GroupCommands;

import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Jamie on 28-May-16.
 */
public class DeleteGroup implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String group = args.<String>getOne("group").get().toUpperCase();
		final String safe = args.<String>getOne("safe").get();

		if (config.getNode("4 - MineGroups").getChildrenMap().containsKey(group)) {

			Set<Object> listOfMines = new TreeSet<>(config.getNode("4 - MineGroups", group).getChildrenMap().keySet());
			listOfMines.remove("resetTime");
			listOfMines.remove("initialDelay");

			if (listOfMines.isEmpty() || safe.equalsIgnoreCase("force")) {
				config.getNode("4 - MineGroups").removeChild(group);
				MineReset.plugin.save();
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + group + " " + Messages.MineGroupDeleted));
			} else if (safe.equalsIgnoreCase("safe")){
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + group + " " + Messages.DeleteGroupFailContents));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.List));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + group + " " + Messages.MineGroupDoesNotExist));
		}

		return CommandResult.success();
	}
}