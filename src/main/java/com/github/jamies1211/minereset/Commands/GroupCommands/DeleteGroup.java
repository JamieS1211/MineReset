package com.github.jamies1211.minereset.Commands.GroupCommands;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 28-May-16.
 */
public class DeleteGroup implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String group = args.<String>getOne("group").get().toUpperCase();
		final String safe = args.<String>getOne("safe").get();

		if (GeneralDataInteraction.getGroupMap().containsKey(group)) {

			if (GeneralDataInteraction.getMinesInGroup(group).isEmpty() || safe.equalsIgnoreCase("force")) {
				GeneralDataInteraction.deleteGroup(group);
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineGroupDeleted.replace("%group%", group)));
			} else if (safe.equalsIgnoreCase("safe")){
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + deleteGroupFailContents.replace("%group%", group)));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + list));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineGroupDoesNotExist.replace("%group%", group)));
		}

		return CommandResult.success();
	}
}