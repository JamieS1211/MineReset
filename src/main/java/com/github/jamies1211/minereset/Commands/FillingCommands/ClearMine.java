package com.github.jamies1211.minereset.Commands.FillingCommands;

import com.github.jamies1211.minereset.Actions.FillMineAction;
import com.github.jamies1211.minereset.Actions.GetMineGroup;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 28-May-16.
 */
public class ClearMine implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		String mine = args.<String>getOne("name").get().toUpperCase();

		String group = GetMineGroup.getMineGroup(mine);

		if (group == null) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineDoesNotExist.replace("%mine%", mine)));
		} else {
			FillMineAction.fill(group, mine, minecraftAirString, src);
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineCleared.replace("%mine%", mine)));
		}

		return CommandResult.success();
	}
}