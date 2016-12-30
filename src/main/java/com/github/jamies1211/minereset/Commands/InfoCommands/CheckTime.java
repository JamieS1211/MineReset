package com.github.jamies1211.minereset.Commands.InfoCommands;

import com.github.jamies1211.minereset.Actions.GetMineGroup;
import com.github.jamies1211.minereset.Actions.TimeUntilFill;
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

/**
 * Created by Jamie on 28-May-16.
 */
public class CheckTime implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		String mine = args.<String>getOne("name").get();
		mine = mine.toUpperCase();

		String group = GetMineGroup.getMineGroup(mine);

		if (group == null) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + mine + " " + Messages.MineDoesNotExist));
		} else {
			String time = SecondsToString.secondsToTimeString(TimeUntilFill.getTimeUntilFill(group));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix  + Messages.ResetTime
					.replace("%mine%", mine)
					.replace("%time%", time)));
		}

		return CommandResult.success();
	}
}