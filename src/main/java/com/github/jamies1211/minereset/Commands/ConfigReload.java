package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.MineReset;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 29-May-16.
 */
public class ConfigReload implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		MineReset.plugin.reload();
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &eConfig has been reloaded"));

		return CommandResult.success();
	}
}