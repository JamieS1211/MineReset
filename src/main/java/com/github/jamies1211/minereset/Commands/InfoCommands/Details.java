package com.github.jamies1211.minereset.Commands.InfoCommands;

import com.flowpowered.math.vector.Vector3d;
import com.github.jamies1211.minereset.Actions.GetMineGroup;
import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import com.github.jamies1211.minereset.Config.MineLocation;
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

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + groupInfoHeader));

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Group: " + group));

					if (mineList.size() < 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "No mines"));
					} else if (mineList.size() == 1) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Contains mine: " + mineList));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Contains mines: " + mineList));
					}


					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Reset time: " +
							infoMap.get("resetTime") + " ( " + SecondsToString.secondsToTimeString(infoMap.get("resetTime")) + ")"));

					if (infoMap.get("initialDelay") == 0) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Initial delay: No initial delay"));
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Initial Delay: " +
								infoMap.get("initialDelay") + " ( " + SecondsToString.secondsToTimeString(infoMap.get("initialDelay")) + ")"));
					}

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + infoFooter));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + noMineGroups));
				}

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineGroupDoesNotExist.replace("%group%", group)));
			}

		} else if (type.equalsIgnoreCase("mine")) {

			String mine = nameOrigonal.toUpperCase();
			String group = GetMineGroup.getMineGroup(mine);
			String mineDisplayName = GeneralDataConfig.getConfig().get().getNode("4 - MineGroups", group, mine, "DisplayName").getString();

			if (group != null) {


				int resetTime = GeneralDataInteraction.getGroupResetTime(group);
				int initialDelay = GeneralDataInteraction.getGroupInitialDelay(group);


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineInfoHeader));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Mine: " + mine));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Group: " + group));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "DisplayName: " + mineDisplayName));

				MineLocation mineLocation = new MineLocation(mine, group);


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Location:"
						+ "   Pos1: " +	mineLocation.pos1.getX() + ", " + mineLocation.pos1.getY() + ", " + mineLocation.pos1.getZ()
						+ "   Pos2: " + mineLocation.pos1.getX() + ", " + mineLocation.pos1.getY() + ", " + mineLocation.pos1.getZ()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Reset time: " +
						resetTime + " ( " + SecondsToString.secondsToTimeString(resetTime) + ")"));

				if (initialDelay == 0) {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Initial delay: No initial delay"));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Initial Delay: " +
							initialDelay + " ( " + SecondsToString.secondsToTimeString(initialDelay) + ")"));
				}


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Spawn point: Spawn" +
						config.getNode("4 - MineGroups", group, mine, "SpawnPoint").getString()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Smart fill: " +
						config.getNode("4 - MineGroups", group, mine, "SmartFill").getBoolean()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Smart Radius: " +
						config.getNode("4 - MineGroups", group, mine, "SmartFillRadius").getInt()));

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Smart fill Only Air: " +
						config.getNode("4 - MineGroups", group, mine, "SmartFillOnlyAir").getBoolean()));



				for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (itemName.equalsIgnoreCase("fallback")) {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Default Block: " +
								config.getNode("4 - MineGroups", group, mine, "ores", itemName, "BlockState").getString()));

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Ore count: " +
								(config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet().size() - 1)));
					}
				}

				int oreCount = 1;

				for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) {
					String itemName = groupItems.toString();
					if (!itemName.equalsIgnoreCase("fallback")) {
						String blockStateString = config.getNode("4 - MineGroups", group, mine, "ores", itemName, "BlockState").getString();
						String percentage = config.getNode("4 - MineGroups", group, mine, "ores", itemName, "percentage").getString();

						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Ore" + oreCount
								+ " : " + blockStateString + " at " + percentage + "%"));

						oreCount++;
					}
				}

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + infoFooter));

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineDoesNotExist.replace("%mine%", mine)));
			}

		} else if (type.equalsIgnoreCase("spawn")) {
			String spawnValue = nameOrigonal.replace("Spawn", "");
			String spawnName = "Spawn" + spawnValue;

			if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains(spawnName)) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + spawnInfoHeader));

				Vector3d spawnLocation = GeneralDataInteraction.getSpawnLocation(spawnName);
				String direction = GeneralDataInteraction.getSpawnDirection(spawnName);
				String spawnWorldString = GeneralDataInteraction.getSpawnWorldUUIDString(spawnName);

				String spawnWorldName = Sponge.getServer().getWorld(UUID.fromString(spawnWorldString)).get().getName();

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Spawn name: " + spawnName));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "World name: " + spawnWorldName));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "World UUID: " + spawnWorldString));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Location:"
						+ " x:" + spawnLocation.getX() + " y:" + spawnLocation.getY() + " z:" + spawnLocation.getZ()));
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + "Facing: " + direction));


				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + infoFooter));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + spawnName + " " + spawnPointNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + info));
		}

		return CommandResult.success();
	}
}