package com.github.jamies1211.minereset.Commands.InfoCommands;

/**
 * Created by Jamie on 31-May-16.
 */

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
 * Created by Jamie on 31-May-16.
 */
public class ListContents implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String type = args.<String>getOne("type").get().toUpperCase();

		if (type.equalsIgnoreCase("groups")) {

			if (config.getNode("4 - MineGroups").getChildrenMap().keySet().size() > 0) {
				Set<Object> listOfGroups = new TreeSet<>(config.getNode("4 - MineGroups").getChildrenMap().keySet());
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "All Groups: " + listOfGroups.toString()));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.NoMineGroups));
			}

		} else if (type.equalsIgnoreCase("mines")) {
			String message = "All Mines: ";

			Set<Object> listOfGroups = new TreeSet<>(config.getNode("4 - MineGroups").getChildrenMap().keySet());
			for (final Object groupObject: listOfGroups) {
				Set<Object> listOfMines = new TreeSet<>(config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().keySet());
				listOfMines.remove("resetTime");
				listOfMines.remove("initialDelay");

				if (!listOfMines.isEmpty()) {
					message = message + listOfMines.toString() + " ";
				}
			}

			if (!message.equalsIgnoreCase("All mines: ")) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + message));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.NoMines));
			}

		} else if (type.equalsIgnoreCase("spawns")) {

			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "All Spawns: " + config.getNode("3 - Spawn").getChildrenMap().keySet()));

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.List));
		}

		return CommandResult.success();
	}
}
