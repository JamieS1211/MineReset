package com.github.jamies1211.minereset.Commands.ConfigCommands;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 28/12/2016.
 */
public class UpdateMineFillSignPercentage implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final double percentage = args.<Double>getOne("percentage").get();

		if (percentage > 0 && percentage < 100) {

			GeneralDataInteraction.setMineFillSignPercentage(percentage);

			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineFillSignPercentageUpdated
					.replace("%percentage%", String.valueOf(percentage))));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineFillSignPercentageInvalid));
		}


		return CommandResult.success();
	}
}
