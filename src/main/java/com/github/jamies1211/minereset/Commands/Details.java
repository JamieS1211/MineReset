package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import com.github.jamies1211.minereset.SecondsToString;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Jamie on 31-May-16.
 */
public class Details implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String type = args.<String>getOne("type").get().toUpperCase();
		final String name = args.<String>getOne("name").get().toUpperCase();

		TreeMap<String, Integer> infoMap = new TreeMap<String, Integer>();

		if (type.equalsIgnoreCase("group")) {
			if (config.getNode("4 - MineGroups").getChildrenMap().keySet().contains(name)) {
				List mineList = new ArrayList<>();
				for (Object detail : config.getNode("4 - MineGroups", name).getChildrenMap().keySet()) {
					if (detail.toString().equalsIgnoreCase("initialDelay") || detail.toString().equalsIgnoreCase("resetTime")) {
						infoMap.put(detail.toString(), config.getNode("4 - MineGroups", name, detail.toString()).getInt());
					} else {
						mineList.add(detail.toString());
					}
				}

				if (!infoMap.isEmpty()) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Group " + name + " reset time: " + SecondsToString.secondsToTimeString(infoMap.get("resetTime"))));
					if (infoMap.get("initialDelay") == 0) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Group " + name + " initial delay: No initial delay"));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Group " + name + " initial delay: " + SecondsToString.secondsToTimeString(infoMap.get("initialDelay"))));
					}
					if (mineList.size() < 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Group " + name + " has no mines"));
					} else if (mineList.size() == 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Group " + name + " contains mine: " + mineList));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Group " + name + " contains mines: " + mineList));
					}
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.NoMineGroups));
				}

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + name + " " + Messages.MineGroupDoesNotExist));
			}

		} else if (type.equalsIgnoreCase("mine")) {
			String group = null;

			for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
				if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(name)) {
					group = groupObject.toString();
				}
			}

			if (group != null) {

				for (final Object groupItems : config.getNode("4 - MineGroups", group, name, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (itemName.equalsIgnoreCase("fallback")) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Default Block: " +
								config.getNode("4 - MineGroups", group, name, "ores", itemName, "BlockState").getString()));

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Ore count: " +
								(config.getNode("4 - MineGroups", group, name, "ores").getChildrenMap().keySet().size() - 1)));
					}
				}

				int oreCount = 1;

				for (final Object groupItems : config.getNode("4 - MineGroups", group, name, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (!itemName.equalsIgnoreCase("fallback")) {
						String blockStateString = config.getNode("4 - MineGroups", group, name, "ores", itemName, "BlockState").getString();
						String percentage = config.getNode("4 - MineGroups", group, name, "ores", itemName, "percentage").getString();

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Ore" + oreCount
								+ " : " + blockStateString + " at " + percentage + "%"));

						oreCount++;
					}
				}

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + name + " " + Messages.MineDoesNotExist));
			}

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.Info));
		}

		return CommandResult.success();
	}
}