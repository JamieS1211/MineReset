package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.Actions.BlockBelowPlayer;
import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 28-May-16.
 */
public class UpdateFallback implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String mine = args.<String>getOne("name").get().toUpperCase();

		String group = null;
		if (src instanceof Player) {

			Player player = (Player) src;

			String block = BlockBelowPlayer.getBlockStringBelowPlayer(player);

			for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
				if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(mine)) {
					group = groupObject.toString();
				}
			}

			if (group != null) {
				if (config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").getString().equalsIgnoreCase(block)) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + block + " " + Messages.SameFallback));
				} else {
					config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").setValue(block);
					MineReset.plugin.save();
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + block + " " + Messages.ChangedFallback + " " + block));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + mine + " " + Messages.MineDoesNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.PlayerOnlyCommand));
		}

		return CommandResult.success();
	}
}