package com.github.jamies1211.minereset.Commands.InfoCommands;

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
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.*;

/**
 * Created by Jamie on 31-May-16.
 */
public class Details implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String type = args.<String>getOne("type").get().toUpperCase();
		final String nameAltered = args.<String>getOne("name").get().toUpperCase();
		final String nameOrigonal = args.<String>getOne("name").get();

		TreeMap<String, Integer> infoMap = new TreeMap<>();

		if (type.equalsIgnoreCase("group")) {
			if (config.getNode("4 - MineGroups").getChildrenMap().keySet().contains(nameAltered)) {
				List mineList = new ArrayList<>();
				for (Object detail : config.getNode("4 - MineGroups", nameAltered).getChildrenMap().keySet()) {
					if (detail.toString().equalsIgnoreCase("initialDelay") || detail.toString().equalsIgnoreCase("resetTime")) {
						infoMap.put(detail.toString(), config.getNode("4 - MineGroups", nameAltered, detail.toString()).getInt());
					} else {
						mineList.add(detail.toString());
					}
				}

				if (!infoMap.isEmpty()) {

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "----------------  Group info  ----------------"));

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Group: " + nameAltered));

					if (mineList.size() < 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "No mines"));
					} else if (mineList.size() == 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Contains mine: " + mineList));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Contains mines: " + mineList));
					}


					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Reset time: " +
							infoMap.get("resetTime") + " ( " + SecondsToString.secondsToTimeString(infoMap.get("resetTime")) + ")"));

					if (infoMap.get("initialDelay") == 0) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Initial delay: No initial delay"));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Initial Delay: " +
								infoMap.get("initialDelay") + " ( " + SecondsToString.secondsToTimeString(infoMap.get("initialDelay")) + ")"));
					}

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "---------------------------------------------"));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.NoMineGroups));
				}

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + nameAltered + " " + Messages.MineGroupDoesNotExist));
			}

		} else if (type.equalsIgnoreCase("mine")) {
			String group = null;

			for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
				if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(nameAltered)) {
					group = groupObject.toString();
				}
			}

			if (group != null) {


				int resetTime = config.getNode("4 - MineGroups", group, "resetTime").getInt();
				int initialDelay = config.getNode("4 - MineGroups", group, "initialDelay").getInt();


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "----------------  Mine info  ----------------"));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Mine: " + nameAltered + "       " + "Group: " + group));

				int x1 = config.getNode("4 - MineGroups", group, nameAltered, "pos1", "x").getInt();
				int y1 = config.getNode("4 - MineGroups", group, nameAltered, "pos1", "y").getInt();
				int z1 = config.getNode("4 - MineGroups", group, nameAltered, "pos1", "z").getInt();
				int x2 = config.getNode("4 - MineGroups", group, nameAltered, "pos2", "x").getInt();
				int y2 = config.getNode("4 - MineGroups", group, nameAltered, "pos2", "y").getInt();
				int z2 = config.getNode("4 - MineGroups", group, nameAltered, "pos2", "z").getInt();


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Location: Pos1: " +
						x1 + ", " + y1 + ", " + z1 + "   Pos2: " + x2 + ", " + y2 + ", " + z2));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Reset time: " +
						resetTime + " ( " + SecondsToString.secondsToTimeString(resetTime) + ")"));

				if (initialDelay == 0) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Initial delay: No initial delay"));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Initial Delay: " +
							initialDelay + " ( " + SecondsToString.secondsToTimeString(initialDelay) + ")"));
				}


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Spawn point: Spawn" +
						config.getNode("4 - MineGroups", group, nameAltered, "SpawnPoint").getString()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Smart Fill: " +
						config.getNode("4 - MineGroups", group, nameAltered, "SmartFill").getBoolean()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Smart Radius: " +
						config.getNode("4 - MineGroups", group, nameAltered, "SmartFillRadius").getInt()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Smart Fill Only Air: " +
						config.getNode("4 - MineGroups", group, nameAltered, "SmartFillOnlyAir").getBoolean()));



				for (final Object groupItems : config.getNode("4 - MineGroups", group, nameAltered, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (itemName.equalsIgnoreCase("fallback")) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Default Block: " +
								config.getNode("4 - MineGroups", group, nameAltered, "ores", itemName, "BlockState").getString()));

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Ore count: " +
								(config.getNode("4 - MineGroups", group, nameAltered, "ores").getChildrenMap().keySet().size() - 1)));
					}
				}

				int oreCount = 1;

				for (final Object groupItems : config.getNode("4 - MineGroups", group, nameAltered, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (!itemName.equalsIgnoreCase("fallback")) {
						String blockStateString = config.getNode("4 - MineGroups", group, nameAltered, "ores", itemName, "BlockState").getString();
						String percentage = config.getNode("4 - MineGroups", group, nameAltered, "ores", itemName, "percentage").getString();

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Ore" + oreCount
								+ " : " + blockStateString + " at " + percentage + "%"));

						oreCount++;
					}
				}

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "---------------------------------------------"));


			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + nameAltered + " " + Messages.MineDoesNotExist));
			}

		} else if (type.equalsIgnoreCase("spawn")) {
			String spawnValue = nameOrigonal.replace("Spawn", "");


			if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnValue)) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "----------------  Spawn info  ----------------"));

				double xSpawn = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnX").getDouble();
				double ySpawn = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnY").getDouble();
				double zSpawn = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnZ").getDouble();
				String direction = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnDirection").getString();
				String spawnWorldString = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnWorld").getString();
				String spawnWorldName = Sponge.getServer().getWorld(UUID.fromString(spawnWorldString)).get().getName();

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Spawn name: " + "Spawn" + spawnValue ));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "World name: " + spawnWorldName));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "World UUID: " + spawnWorldString));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Location:" + " x:" + xSpawn + " y:" + ySpawn +
						" z:" + zSpawn));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Facing: " + direction));


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "---------------------------------------------"));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + "Spawn" + spawnValue + " " + Messages.SpawnPointNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.Info));
		}

		return CommandResult.success();
	}
}