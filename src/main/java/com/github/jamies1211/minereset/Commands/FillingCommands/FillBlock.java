package com.github.jamies1211.minereset.Commands.FillingCommands;

import com.github.jamies1211.minereset.Actions.BlockBelowPlayer;
import com.github.jamies1211.minereset.Actions.FillMineAction;
import com.github.jamies1211.minereset.Actions.GetMineGroup;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 01-Jun-16.
 */
public class FillBlock implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		String mine = args.<String>getOne("name").get();
		mine = mine.toUpperCase();

		String group = GetMineGroup.getMineGroup(mine);

		if (src instanceof Player) {
			Player player = (Player) src;

			if (group == null) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineDoesNotExist.replace("%mine%", mine)));
			} else {
				String block = BlockBelowPlayer.getBlockStringBelowPlayer(player);
				FillMineAction.fill(group, mine, block, src);
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineFilledBlock
						.replace("%mine%", mine)
						.replace("%block%", block)));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + playerOnlyCommand));
		}

		return CommandResult.success();
	}
}