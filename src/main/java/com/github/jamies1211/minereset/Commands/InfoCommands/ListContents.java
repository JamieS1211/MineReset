package com.github.jamies1211.minereset.Commands.InfoCommands;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Set;
import java.util.TreeSet;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 31-May-16.
 */
public class ListContents implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String type = args.<String>getOne("type").get().toUpperCase();
		Set groupMapKeySet = GeneralDataInteraction.getGroupMap().keySet();

		if (type.equalsIgnoreCase("groups")) {

			if (groupMapKeySet.size() > 0) {
				Set<Object> listOfGroups = new TreeSet<>(groupMapKeySet);
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "All Groups: " + listOfGroups.toString()));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + noMineGroups));
			}

		} else if (type.equalsIgnoreCase("mines")) {
			String message = "All Mines: ";

			for (final Object groupObject: groupMapKeySet) {
				Set<Object> listOfMines = GeneralDataInteraction.getMinesInGroup(groupObject.toString());

				if (!listOfMines.isEmpty()) {
					message = message + listOfMines.toString() + " ";
				}
			}

			if (!message.equalsIgnoreCase("All mines: ")) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + message));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + noMines));
			}

		} else if (type.equalsIgnoreCase("spawns")) {

			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "All Spawns: " + GeneralDataInteraction.getSpawnPointMap().keySet()));

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + list));
		}

		return CommandResult.success();
	}
}
