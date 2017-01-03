package com.github.jamies1211.minereset.Commands.MineSetupCommands;

import com.github.jamies1211.minereset.Actions.BlockBelowPlayer;
import com.github.jamies1211.minereset.Actions.GetMineGroup;
import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 28-May-16.
 */
public class UpdateFallback implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String mine = args.<String>getOne("name").get().toUpperCase();

		if (src instanceof Player) {

			Player player = (Player) src;
			String block = BlockBelowPlayer.getBlockStringBelowPlayer(player);
			String group = GetMineGroup.getMineGroup(mine);

			if (group != null) {
				if (config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").getString().equalsIgnoreCase(block)) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + SameFallback));
				} else {
					config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").setValue(block);
					GeneralDataConfig.getConfig().save();
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + ChangedFallback.replace("%block%", block)));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineDoesNotExist.replace("%mine%", mine)));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + PlayerOnlyCommand));
		}

		return CommandResult.success();
	}
}