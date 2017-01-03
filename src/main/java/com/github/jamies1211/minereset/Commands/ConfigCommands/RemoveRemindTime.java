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
public class RemoveRemindTime implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final int time = args.<Integer>getOne("time").get();

		if (remindTimes.contains(Integer.toString(time))) {

			String timeListString = null;
			remindTimes.remove(Integer.toString(time));

			for (int index = 1; index <= remindTimes.size(); index++) {
				if (index == 1) {
					timeListString = remindTimes.get(index - 1);
				} else {
					timeListString = timeListString + ", " + remindTimes.get(index - 1);
				}
			}

			GeneralDataInteraction.setRemindSecondListFromString(timeListString); // Wright changes to file.

			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + remindTimeRemove
					.replace("%time%", Integer.toString(time))
					.replace("%readableTime%", SecondsToString.secondsToTimeString(time))));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + remindTimeNotExist));
		}


		return CommandResult.success();
	}
}
