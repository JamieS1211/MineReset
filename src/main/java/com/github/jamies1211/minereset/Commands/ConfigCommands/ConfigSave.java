package com.github.jamies1211.minereset.Commands.ConfigCommands;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 29-May-16.
 */
public class ConfigSave implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		GeneralDataConfig.getConfigFromInteraction().save();
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + configSaved));

		return CommandResult.success();
	}
}