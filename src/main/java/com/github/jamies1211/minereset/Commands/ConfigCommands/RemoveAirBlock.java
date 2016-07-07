package com.github.jamies1211.minereset.Commands.ConfigCommands;

import com.github.jamies1211.minereset.Actions.BlockBelowPlayer;
import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import com.github.jamies1211.minereset.SecondsToString;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.MineReset.airBlocks;
import static com.github.jamies1211.minereset.MineReset.remindTimes;

/**
 * Created by Jamie on 03-Jul-16.
 */
public class RemoveAirBlock implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		if (src instanceof Player) {

			Player player = (Player) src;
			String blockString = BlockBelowPlayer.getBlockTypeStringBelowPlayer(player);

			if (airBlocks.contains(blockString)) {

				String blockListString = null;
				airBlocks.remove(blockString);

				for (int index = 1; index <= airBlocks.size(); index++) {
					if (index == 1) {
						blockListString = airBlocks.get(index - 1);
					} else {
						blockListString = blockListString + ", " + airBlocks.get(index - 1);
					}
				}

				config.getNode("5 - Lists", "AirBlocks").setValue(blockListString); // Wright changes to file.
				MineReset.plugin.save();

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.AirBlockRemove1 +
						" " + blockString + " " + Messages.AirBlockRemove2));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.AirBlockNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.PlayerOnlyCommand));
		}


		return CommandResult.success();
	}
}
