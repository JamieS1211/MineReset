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
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Jamie on 28-May-16.
 */
public class UpdateOre implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String mine = args.<String>getOne("name").get().toUpperCase();
		final double percentage = args.<Double>getOne("percentage").get();

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

						MineReset.plugin.save();
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + block + " " + Messages.OreRemoved));
					} else if (percentage > 0) {

						/** Total up percentages */
						double currentFullPercentage = 0;

						for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) { // For all ores in a mine
							if (!groupItems.toString().equalsIgnoreCase("fallback")) { // If it is not the fallback
								currentFullPercentage += config.getNode("4 - MineGroups", group, mine, "ores", groupItems.toString(), "percentage").getDouble(); // Totals up the percentage.
							}
						}

						double oldBlockPercentage = config.getNode("4 - MineGroups", group, mine, "ores", (Integer.toString(itemIndex)), "percentage").getDouble();

						if (currentFullPercentage + percentage - oldBlockPercentage<= 100) {

							if (Double.toString(oldBlockPercentage).equalsIgnoreCase(Double.toString(percentage))) {
								src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.OreSamePercentageError + " " + block + " in " + mine));
							} else {
								config.getNode("4 - MineGroups", group, mine, "ores", itemIndex, "BlockState").setValue(block);
								config.getNode("4 - MineGroups", group, mine, "ores", itemIndex, "percentage").setValue(percentage);
								MineReset.plugin.save();
								src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + block + " in " + mine +
										" updated to " + Double.toString(percentage) + "% from " + Double.toString(oldBlockPercentage) + "%"));
							}
						} else {
							src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.OrePrecentageError));
						}


					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.PercentageTooSmallUpdate));
					}
				} else if (config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").getString().equalsIgnoreCase(block)) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + block + " " + Messages.UnableToEditFallback));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + block + " " + Messages.OreNotInMine));
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