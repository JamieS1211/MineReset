package com.github.jamies1211.minereset.Commands;

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
public class AddAirBlock implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		if (src instanceof Player) {

			Player player = (Player) src;
			String blockString = BlockBelowPlayer.getBlockTypeStringBelowPlayer(player);

			if (!airBlocks.contains(blockString)) {

				String blockListString = config.getNode("2 - RemindSecondList").getString();

				if (!airBlocks.isEmpty()) { // Add comma if other values there.
					blockListString = blockListString + ", ";
				}

				blockListString = blockListString + blockString; // Add time to list.

				config.getNode("5 - Lists", "AirBlocks").setValue(blockListString); // Wright changes to file.
				MineReset.plugin.save();

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.AirBlockAdd1 +
						" " + blockString + " " + Messages.AirBlockAdd2));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.AirBlockExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.PlayerOnlyCommand));
		}


		return CommandResult.success();
	}
}
