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
public class AddOre implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String mine = args.<String>getOne("name").get().toUpperCase();
		final double percentage = args.<Double>getOne("percentage").get();

		if (src instanceof Player) {

			Player player = (Player) src;
			if (percentage > 0) {

				String group = GetMineGroup.getMineGroup(mine);

				if (group != null) {

					String block = BlockBelowPlayer.getBlockStringBelowPlayer(player);
					Boolean notInMine = true;

					for (final Object itemObject : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) {
						if (config.getNode("4 - MineGroups", group, mine, "ores", itemObject, "BlockState").getString().equalsIgnoreCase(block)) {
							notInMine = false;
						}
					}

					if (notInMine) {

						float currentFullPercentage = 0;

						for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) { // For all ores in a mine
							if (!groupItems.toString().equalsIgnoreCase("fallback")) { // If it is not the fallback
								currentFullPercentage += config.getNode("4 - MineGroups", group, mine, "ores", groupItems.toString(), "percentage").getFloat(); // Totals up the percentage.
							}
						}

						if (currentFullPercentage + percentage <= 100) {

							int currentSize = config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().size();

							config.getNode("4 - MineGroups", group, mine, "ores", currentSize, "BlockState").setValue(block);
							config.getNode("4 - MineGroups", group, mine, "ores", currentSize, "percentage").setValue(percentage);
							GeneralDataConfig.getConfig().save();
							src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + AddedOre
									.replace("%block%", block)
									.replace("%mine%", mine)
									.replace("%percentage%", Double.toString(percentage))));
						} else {
							src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + OrePercentageError));
						}
					} else if (config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").getString().equalsIgnoreCase(block)) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + UnableToAddFallback.replace("%block%", block)));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + OreAlreadyInMine.replace("%block%", block)));
					}
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineDoesNotExist.replace("%mine%", mine)));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + PercentageTooSmall));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + PlayerOnlyCommand));
		}

		return CommandResult.success();
	}
}