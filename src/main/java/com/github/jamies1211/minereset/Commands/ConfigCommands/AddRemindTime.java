package com.github.jamies1211.minereset.Commands.ConfigCommands;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import com.github.jamies1211.minereset.SecondsToString;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.MineReset.remindTimes;
import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 03-Jul-16.
 */
public class AddRemindTime implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final int time = args.<Integer>getOne("time").get();

		if (!remindTimes.contains(Integer.toString(time))) {

			String timeListString = GeneralDataInteraction.getRemindSecondListAsString();

			if (!remindTimes.isEmpty()) { // Add comma if other values there.
				timeListString = timeListString + ", ";
			}

			timeListString = timeListString + time; // Add time to list.

			GeneralDataInteraction.setRemindSecondListFromString(timeListString); // Wright changes to file.

			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + remindTimeAdd
					.replace("%time%", Integer.toString(time))
					.replace("%readableTime%", SecondsToString.secondsToTimeString(time))));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + remindTimeExist));
		}


		return CommandResult.success();
	}
}
