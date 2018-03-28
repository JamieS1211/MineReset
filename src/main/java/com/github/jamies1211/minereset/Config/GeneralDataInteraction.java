package com.github.jamies1211.minereset.Config;

import com.flowpowered.math.vector.Vector3d;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;

import java.util.*;

/**
 * Created by Jamie on 03/01/2017.
 */
public class GeneralDataInteraction {

	// 1 - ConfigMode
	public static int getConfigMode () {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("1 - ConfigMode").getInt();
	}

	public static void setConfigMode (int mode) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("1 - ConfigMode").setValue(mode);
		GeneralDataConfig.getConfigFromInteraction().save();
	}




	// 2 - RemindSecondList
	public static String getRemindSecondListAsString () {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("2 - RemindSecondList").getString();
	}

	public static ArrayList<String> getRemindSecondListAsArray () {
		return new ArrayList<>(Arrays.asList(getRemindSecondListAsString().split(", ")));
	}

	public static void setRemindSecondListFromString (String list) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("2 - RemindSecondList").setValue(list);
		GeneralDataConfig.getConfigFromInteraction().save();
	}




	// 3 - Spawn
	public static Map<Object, ? extends CommentedConfigurationNode> getSpawnPointMap () {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("3 - Spawn").getChildrenMap();
	}

	public static Vector3d getSpawnLocation (String spawnName) {
		GeneralDataConfig configFile = GeneralDataConfig.getConfigFromInteraction();

		return new Vector3d(
				configFile.get().getNode("3 - Spawn", spawnName, "SpawnX").getDouble(),
				configFile.get().getNode("3 - Spawn", spawnName, "SpawnY").getDouble(),
				configFile.get().getNode("3 - Spawn", spawnName, "SpawnZ").getDouble());
	}

	public static String getSpawnDirection (String spawnName) {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("3 - Spawn", spawnName, "SpawnDirection").getString();
	}

	public static String getSpawnWorldUUIDString (String spawnName) {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("3 - Spawn", spawnName, "SpawnWorld").getString();
	}

	public static void setSpawnPoint (String spawnName, double x, double y, double z, String direction, String worldUUIDString) {
		GeneralDataConfig configFile = GeneralDataConfig.getConfigFromInteraction();

		configFile.get().getNode("3 - Spawn", spawnName, "SpawnX").setValue(x);
		configFile.get().getNode("3 - Spawn", spawnName, "SpawnY").setValue(y);
		configFile.get().getNode("3 - Spawn", spawnName, "SpawnZ").setValue(z);
		configFile.get().getNode("3 - Spawn", spawnName, "SpawnDirection").setValue(direction);
		configFile.get().getNode("3 - Spawn", spawnName, "SpawnWorld").setValue(worldUUIDString);
		configFile.save();
	}

	public static void deleteSpawnPoint (String spawnName) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("3 - Spawn").removeChild(spawnName);
		GeneralDataConfig.getConfigFromInteraction().save();
	}




	// 4 - MineGroups

	// Group methods

	public static Map<Object, ? extends CommentedConfigurationNode> getGroupMap () {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups").getChildrenMap();
	}

	public static int getGroupResetTime (String group) {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, "resetTime").getInt();
	}

	public static int getGroupInitialDelay (String group) {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, "initialDelay").getInt();
	}

	public static Map<Object, ? extends CommentedConfigurationNode> getGroupContents (String group) {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group).getChildrenMap();
	}

	public static Set<Object> getMinesInGroup (String group) {
		Set<Object> listOfMines = new TreeSet<>(getGroupContents(group).keySet());
		listOfMines.remove("resetTime");
		listOfMines.remove("initialDelay");

		return listOfMines;
	}

	public static String getMineDisplayNamesInGroup (String group) {
		ArrayList<String> list = new ArrayList<>();

		for (Object mine : getMinesInGroup(group)) {
			if (GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, mine, "DisplayName").getValue() != null) {
				list.add(GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, mine, "DisplayName").getString());
			} else {
				list.add(mine.toString());
			}
		}

		return list.toString();
	}

	public static void setGroupValues (String group, int resetTime, int initialDelay) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, "resetTime").setValue(resetTime);
		GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, "initialDelay").setValue(initialDelay);
		GeneralDataConfig.getConfigFromInteraction().save();
	}

	public static void deleteGroup (String group) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups").removeChild(group);
		GeneralDataConfig.getConfigFromInteraction().save();
	}


	// Mine methods

	public static String getMineWorldString (String group, String mine) {
		return GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, mine, "MineWorld").getString();
	}

	public static void setMineWorldString (String group, String mine, String mineWorldString) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("4 - MineGroups", group, mine, "MineWorld").setValue(mineWorldString);
		GeneralDataConfig.getConfigFromInteraction().save();
	}




	// 5 - Lists




	// 6 - ChatSettings

	public static int getFillingTextSetting () {
		GeneralDataConfig configFile = GeneralDataConfig.getConfigFromInteraction();

		if (configFile.get().getNode("6 - ChatSettings", "FillingText").getValue() == null) {
			setFillingTextSetting(1);
		}

		return configFile.get().getNode("6 - ChatSettings", "FillingText").getInt();
	}

	public static void setFillingTextSetting (int setting) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("6 - ChatSettings", "FillingText").setValue(setting);
		GeneralDataConfig.getConfigFromInteraction().save();
	}

	public static int getReminderTextSetting () {
		GeneralDataConfig configFile = GeneralDataConfig.getConfigFromInteraction();

		if (configFile.get().getNode("6 - ChatSettings", "ReminderText").getValue() == null) {
			setReminderTextSetting(2);
		}

		return configFile.get().getNode("6 - ChatSettings", "ReminderText").getInt();
	}

	public static void setReminderTextSetting (int setting) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("6 - ChatSettings", "ReminderText").setValue(setting);
		GeneralDataConfig.getConfigFromInteraction().save();
	}




	// 7 - MineFillSignPercentages

	public static double getMineFillSignPercentage () {
		GeneralDataConfig configFile = GeneralDataConfig.getConfigFromInteraction();

		if (configFile.get().getNode("7 - MineFillSignPercentage").getValue() == null) {
			setMineFillSignPercentage(80.0);
		}

		return configFile.get().getNode("7 - MineFillSignPercentage").getDouble();
	}

	public static void setMineFillSignPercentage (double percentage) {
		GeneralDataConfig.getConfigFromInteraction().get().getNode("7 - MineFillSignPercentage").setValue(percentage);
		GeneralDataConfig.getConfigFromInteraction().save();
	}
}

	/*

	get().getNode("5 - Lists", "AirBlocks").setValue(
				"minecraft:torch, minecraft:redstone_torch, minecraft:redstone_wire, minecraft:powered_repeater, minecraft:unpowered_repeater, minecraft:powered_comparator, " +
						"minecraft:unpowered_comparator, minecraft:wooden_button, minecraft:stone_button, minecraft:lever, minecraft:tripwire_hook, minecraft:stone_pressure_plate, " +
						"minecraft:wooden_pressure_plate, minecraft:light_weighted_pressure_plate, minecraft:heavy_weighted_pressure_plate, minecraft:trapdoor, minecraft:iron_trapdoor, " +
						"minecraft:fence_gate, minecraft:spruce_fence_gate, minecraft:birch_fence_gate, minecraft:jungle_fence_gate, minecraft:dark_oak_fence_gate, minecraft:acacia_fence_gate, " +
						"minecraft:wooden_door, minecraft:iron_door, minecraft:spruce_door, minecraft:birch_door, minecraft:jungle_door, minecraft:acacia_door, minecraft:dark_oak_door, " +
						"minecraft:rail, minecraft:golden_rail, minecraft:detector_rail, minecraft:activator_rail, minecraft:tallgrass, minecraft:sapling, minecraft:deadbush, " +
						"minecraft:yellow_flower, minecraft:red_flower, minecraft:brown_mushroom, minecraft:red_mushroom, minecraft:ladder, minecraft:snow_layer, minecraft:fence, " +
						"minecraft:nether_brick_fence, minecraft:iron_bars, minecraft:glass_pane, minecraft:vine, minecraft:waterlily, minecraft:cobblestone_wall, minecraft:anvil, " +
						"minecraft:stained_glass_pane, minecraft:carpet, minecraft:double_plant, minecraft:wall_sign, minecraft:standing_sign, minecraft:skull, minecraft:brewing_stand, g" +
						"minecraft:skull, minecraft:standing_banner");
`


*/
