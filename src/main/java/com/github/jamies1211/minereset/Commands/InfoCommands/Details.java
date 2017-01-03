package com.github.jamies1211.minereset.Commands.InfoCommands;

import com.github.jamies1211.minereset.Actions.GetMineGroup;
import com.github.jamies1211.minereset.Config.GeneralDataConfig;
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

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 31-May-16.
 */
public class Details implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String type = args.<String>getOne("type").get().toUpperCase();
		final String nameOrigonal = args.<String>getOne("name").get();

		TreeMap<String, Integer> infoMap = new TreeMap<>();

		if (type.equalsIgnoreCase("group")) {

			String group = nameOrigonal.toUpperCase();

			if (config.getNode("4 - MineGroups").getChildrenMap().keySet().contains(group)) {
				List mineList = new ArrayList<>();
				for (Object detail : config.getNode("4 - MineGroups", group).getChildrenMap().keySet()) {
					String detailString = detail.toString();
					if (detailString.equalsIgnoreCase("initialDelay") || detailString.equalsIgnoreCase("resetTime")) {
						infoMap.put(detailString, config.getNode("4 - MineGroups", group, detailString).getInt());
					} else {
						mineList.add(detailString);
					}
				}

				if (!infoMap.isEmpty()) {

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + GroupInfoHeader));

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Group: " + group));

					if (mineList.size() < 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "No mines"));
					} else if (mineList.size() == 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Contains mine: " + mineList));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Contains mines: " + mineList));
					}


					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Reset time: " +
							infoMap.get("resetTime") + " ( " + SecondsToString.secondsToTimeString(infoMap.get("resetTime")) + ")"));

					if (infoMap.get("initialDelay") == 0) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Initial delay: No initial delay"));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Initial Delay: " +
								infoMap.get("initialDelay") + " ( " + SecondsToString.secondsToTimeString(infoMap.get("initialDelay")) + ")"));
					}

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InfoFooter));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + NoMineGroups));
				}

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineGroupDoesNotExist.replace("%group%", group)));
			}

		} else if (type.equalsIgnoreCase("mine")) {

			String mine = nameOrigonal.toUpperCase();
			String group = GetMineGroup.getMineGroup(mine);

			if (group != null) {


				int resetTime = config.getNode("4 - MineGroups", group, "resetTime").getInt();
				int initialDelay = config.getNode("4 - MineGroups", group, "initialDelay").getInt();


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineInfoHeader));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Mine: " + mine + "       " + "Group: " + group));

				int x1 = config.getNode("4 - MineGroups", group, mine, "pos1", "x").getInt();
				int y1 = config.getNode("4 - MineGroups", group, mine, "pos1", "y").getInt();
				int z1 = config.getNode("4 - MineGroups", group, mine, "pos1", "z").getInt();
				int x2 = config.getNode("4 - MineGroups", group, mine, "pos2", "x").getInt();
				int y2 = config.getNode("4 - MineGroups", group, mine, "pos2", "y").getInt();
				int z2 = config.getNode("4 - MineGroups", group, mine, "pos2", "z").getInt();


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Location: Pos1: " +
						x1 + ", " + y1 + ", " + z1 + "   Pos2: " + x2 + ", " + y2 + ", " + z2));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Reset time: " +
						resetTime + " ( " + SecondsToString.secondsToTimeString(resetTime) + ")"));

				if (initialDelay == 0) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Initial delay: No initial delay"));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Initial Delay: " +
							initialDelay + " ( " + SecondsToString.secondsToTimeString(initialDelay) + ")"));
				}


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Spawn point: Spawn" +
						config.getNode("4 - MineGroups", group, mine, "SpawnPoint").getString()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Smart Fill: " +
						config.getNode("4 - MineGroups", group, mine, "SmartFill").getBoolean()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Smart Radius: " +
						config.getNode("4 - MineGroups", group, mine, "SmartFillRadius").getInt()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Smart Fill Only Air: " +
						config.getNode("4 - MineGroups", group, mine, "SmartFillOnlyAir").getBoolean()));



				for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (itemName.equalsIgnoreCase("fallback")) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Default Block: " +
								config.getNode("4 - MineGroups", group, mine, "ores", itemName, "BlockState").getString()));

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Ore count: " +
								(config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet().size() - 1)));
					}
				}

				int oreCount = 1;

				for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (!itemName.equalsIgnoreCase("fallback")) {
						String blockStateString = config.getNode("4 - MineGroups", group, mine, "ores", itemName, "BlockState").getString();
						String percentage = config.getNode("4 - MineGroups", group, mine, "ores", itemName, "percentage").getString();

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Ore" + oreCount
								+ " : " + blockStateString + " at " + percentage + "%"));

						oreCount++;
					}
				}

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InfoFooter));


			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineDoesNotExist.replace("%mine%", mine)));
			}

		} else if (type.equalsIgnoreCase("spawn")) {
			String spawnValue = nameOrigonal.replace("Spawn", "");


			if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnValue)) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + SpawnInfoHeader));

				double xSpawn = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnX").getDouble();
				double ySpawn = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnY").getDouble();
				double zSpawn = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnZ").getDouble();
				String direction = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnDirection").getString();
				String spawnWorldString = config.getNode("3 - Spawn", "Spawn" + spawnValue, "SpawnWorld").getString();
				String spawnWorldName = Sponge.getServer().getWorld(UUID.fromString(spawnWorldString)).get().getName();

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Spawn name: " + "Spawn" + spawnValue ));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "World name: " + spawnWorldName));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "World UUID: " + spawnWorldString));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Location:" + " x:" + xSpawn + " y:" + ySpawn +
						" z:" + zSpawn));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Facing: " + direction));


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InfoFooter));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + "Spawn" + spawnValue + " " + SpawnPointNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + Info));
		}

		return CommandResult.success();
	}
}