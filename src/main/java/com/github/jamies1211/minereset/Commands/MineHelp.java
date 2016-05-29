package com.github.jamies1211.minereset.Commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 28-May-16.
 */
public class MineHelp implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &eTo check time before a mine resets do /mine time [mine]"));

		return CommandResult.success();
	}
}