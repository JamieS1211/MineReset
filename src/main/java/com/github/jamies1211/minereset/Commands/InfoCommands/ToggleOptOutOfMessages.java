package com.github.jamies1211.minereset.Commands.InfoCommands;

import com.github.jamies1211.minereset.Config.PlayerDataConfig;
import com.github.jamies1211.minereset.Messages;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 04/01/2017.
 */
public class ToggleOptOutOfMessages implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		if (src instanceof Player) {
			Player player = (Player) src;
			String playerUUIDString = player.getUniqueId().toString();
			PlayerDataConfig.togglePlayerOptOutOfMessages(playerUUIDString);

			if (PlayerDataConfig.getPlayersOptedOutOfMessages().contains(playerUUIDString)) {
				player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + settingSetMessageOptOut));
			} else {
				player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + settingSetMessageOptIn));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + playerOnlyCommand));
		}

		return CommandResult.success();
	}
}
