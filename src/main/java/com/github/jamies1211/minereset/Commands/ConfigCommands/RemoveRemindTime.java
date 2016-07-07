package com.github.jamies1211.minereset.Commands.ConfigCommands;

import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import com.github.jamies1211.minereset.SecondsToString;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.MineReset.remindTimes;

/**
 * Created by Jamie on 03-Jul-16.
 */
public class RemoveRemindTime implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

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

			config.getNode("2 - RemindSecondList").setValue(timeListString); // Wright changes to file.
			MineReset.plugin.save();

			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.RemindTimeRemove1 +
					" " + time + " ( " + SecondsToString.secondsToTimeString(time) + ") " + Messages.RemindTimeRemove2));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.RemindTimeNotExist));
		}


		return CommandResult.success();
	}
}
