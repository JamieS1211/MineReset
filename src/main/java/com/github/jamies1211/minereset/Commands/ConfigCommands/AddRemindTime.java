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
public class AddRemindTime implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final int time = args.<Integer>getOne("time").get();

		if (!remindTimes.contains(Integer.toString(time))) {

			String timeListString = config.getNode("2 - RemindSecondList").getString();

			if (!remindTimes.isEmpty()) { // Add comma if other values there.
				timeListString = timeListString + ", ";
			}

			timeListString = timeListString + time; // Add time to list.

			config.getNode("2 - RemindSecondList").setValue(timeListString); // Wright changes to file.
			MineReset.plugin.save();

			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.RemindTimeAdd1 +
					" " + time + " ( " + SecondsToString.secondsToTimeString(time) + ") " + Messages.RemindTimeAdd2));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.RemindTimeExist));
		}


		return CommandResult.success();
	}
}