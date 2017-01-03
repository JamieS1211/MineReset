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
public class UpdateOre implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String mine = args.<String>getOne("name").get().toUpperCase();
		final double percentage = args.<Double>getOne("percentage").get();

		if (src instanceof Player) {

			Player player = (Player) src;
			String block = BlockBelowPlayer.getBlockStringBelowPlayer(player);
			String group = GetMineGroup.getMineGroup(mine);

			if (group != null) {

				int itemIndex = -1;

				for (final Object itemObject : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) {
					if (!itemObject.toString().equalsIgnoreCase("fallback")) {
						if (config.getNode("4 - MineGroups", group, mine, "ores", itemObject, "BlockState").getString().equalsIgnoreCase(block)) {
							itemIndex = Integer.parseInt(itemObject.toString());
						}
					}
				}

				if (itemIndex >= 0) {
					if (percentage == 0) {

						config.getNode("4 - MineGroups", group, mine, "ores").removeChild(Integer.toString(itemIndex));

						int currentTargetIndex = itemIndex + 1;

						while (config.getNode("4 - MineGroups", group, mine, "ores", (Integer.toString(currentTargetIndex))).getValue() != null) {

							String currentBlockState = config.getNode("4 - MineGroups", group, mine, "ores", (Integer.toString(currentTargetIndex)), "BlockState").getString();
							String currentPercentage = config.getNode("4 - MineGroups", group, mine, "ores", (Integer.toString(currentTargetIndex)), "percentage").getString();

							config.getNode("4 - MineGroups", group, mine, "ores", (Integer.toString(currentTargetIndex - 1)), "BlockState").setValue(currentBlockState);
							config.getNode("4 - MineGroups", group, mine, "ores", (Integer.toString(currentTargetIndex - 1)), "percentage").setValue(currentPercentage);

							config.getNode("4 - MineGroups", group, mine, "ores").removeChild(Integer.toString(currentTargetIndex));
							currentTargetIndex++;
						}

						GeneralDataConfig.getConfig().save();
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + OreRemoved.replace("%block%", block)));
					} else if (percentage > 0) {

						// Total up percentages
						double currentFullPercentage = 0;

						for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) { // For all ores in a mine
							if (!groupItems.toString().equalsIgnoreCase("fallback")) { // If it is not the fallback
								currentFullPercentage += config.getNode("4 - MineGroups", group, mine, "ores", groupItems.toString(), "percentage").getDouble(); // Totals up the percentage.
							}
						}

						double oldBlockPercentage = config.getNode("4 - MineGroups", group, mine, "ores", (Integer.toString(itemIndex)), "percentage").getDouble();

						if (currentFullPercentage + percentage - oldBlockPercentage<= 100) {

							if (Double.toString(oldBlockPercentage).equalsIgnoreCase(Double.toString(percentage))) {
								src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + OreSamePercentageError
										.replace("%block%", block)
										.replace("%mine%", mine)));
							} else {
								config.getNode("4 - MineGroups", group, mine, "ores", itemIndex, "BlockState").setValue(block);
								config.getNode("4 - MineGroups", group, mine, "ores", itemIndex, "percentage").setValue(percentage);
								GeneralDataConfig.getConfig().save();
								src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + block + " in " + mine +
										" updated to " + Double.toString(percentage) + "% from " + Double.toString(oldBlockPercentage) + "%"));
							}
						} else {
							src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + OrePercentageError));
						}


					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + PercentageTooSmallUpdate));
					}
				} else if (config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").getString().equalsIgnoreCase(block)) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + UnableToEditFallback.replace("%block%", block)));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + OreNotInMine.replace("%block%", block)));
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