package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
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

		TreeMap<String, String> infoMap = new TreeMap<String, String>();

		if (type.equalsIgnoreCase("group")) {
			if (config.getNode("4 - MineGroups").getChildrenMap().keySet().contains(name)) {
				List mineList = new ArrayList<>();
				for (Object detail : config.getNode("4 - MineGroups", name).getChildrenMap().keySet()) {
					if (detail.toString().equalsIgnoreCase("initialDelay") || detail.toString().equalsIgnoreCase("resetTime")) {
						String value = config.getNode("4 - MineGroups", name, detail.toString()).getValue().toString();
						infoMap.put(detail.toString(), value);
					} else {
						mineList.add(detail.toString());
					}
				}

				if (!infoMap.isEmpty()) {
					if (mineList.size() > 0) {
						src.sendMessage(Text.of(infoMap.toString()));
						src.sendMessage(Text.of(infoMap + " Mines: " + mineList));
					} else {
						src.sendMessage(Text.of(infoMap + " Mines: NO MINES"));
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
				for (Object dataObject : config.getNode("4 - MineGroups", group, name, "ores").getChildrenMap().keySet()) {
					if (dataObject.toString().equalsIgnoreCase("fallback")) {
						infoMap.put("Default Block", config.getNode("4 - MineGroups", group, name, "ores", "fallback", "BlockState").getString());
					} else {
						infoMap.put(config.getNode("4 - MineGroups", group, name, "ores", dataObject.toString(), "BlockState").getString(),
								config.getNode("4 - MineGroups", group, name, "ores", dataObject.toString(), "percentage").getString() + "%");
					}
				}
				src.sendMessage(Text.of(infoMap.toString()));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + name + " " + Messages.MineDoesNotExist));
			}

		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.Info));
		}

		return CommandResult.success();
	}
}