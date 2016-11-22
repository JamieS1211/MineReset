package com.github.jamies1211.minereset.Commands.InfoCommands;

import com.github.jamies1211.minereset.Messages;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.MineReset.remindTimes;

/**
 * Created by Jamie on 28-May-16.
 */
public class ListReminder implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.RemindList.replace("%list%", remindTimes.toString())));

		return CommandResult.success();
	}
}