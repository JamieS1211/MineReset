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
public class UpdateGroup implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String group = args.<String>getOne("group").get().toUpperCase();
		final int resetTime = args.<Integer>getOne("resetTime").get();
		final int initialDelay = args.<Integer>getOne("initialDelay").get();

		if (GeneralDataInteraction.getGroupMap().containsKey(group)) {
			if (resetTime < 60) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + resetTimeTooShort));
			} else if (initialDelay < 0){
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + delayTooShort));
			} else {
				GeneralDataInteraction.setGroupValues(group, resetTime, initialDelay);
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + updateMineGroup.replace("%group%", group)));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineGroupDoesNotExist.replace("%group%", group)));
		}

		return CommandResult.success();
	}
}