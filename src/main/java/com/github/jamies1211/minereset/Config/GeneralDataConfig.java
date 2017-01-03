package com.github.jamies1211.minereset.Config;

import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.Sponge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static com.github.jamies1211.minereset.Messages.configLoadError;
import static com.github.jamies1211.minereset.Messages.loadedConfigFile;
import static com.github.jamies1211.minereset.Messages.newConfigFile;

/**
 * Created by Jamie on 27-Jul-16.
 */
public class GeneralDataConfig {

	private static GeneralDataConfig config = new GeneralDataConfig();

	public static GeneralDataConfig getConfig() {
		return config;
	}

	private Path configFile = Paths.get(MineReset.getPlugin().getConfigDir() + "/general.conf");
	private ConfigurationLoader<CommentedConfigurationNode> configLoader = HoconConfigurationLoader.builder().setPath(configFile).build();
	private CommentedConfigurationNode configNode;

	public void setup() {
		if (!Files.exists(configFile)) {
			Path oldConfigFile = Paths.get(MineReset.getPlugin().getConfigDir().getParent() + "/minereset.conf");
			if (Files.exists(oldConfigFile)) {
				// Try to find and import old config file
				Path oldConfigFileNewName = Paths.get(MineReset.getPlugin().getConfigDir().getParent() + "/minereset.conf.outdated");
				try {
					Files.copy(oldConfigFile, configFile);
					Files.copy(oldConfigFile, oldConfigFileNewName);
					Files.deleteIfExists(oldConfigFile);
					load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// Create new config file
				try {
					Files.createFile(configFile);
					load();
					enterData();
					save();
					MineReset.plugin.getLogger().info(newConfigFile);
				} catch (IOException e) {
					e.printStackTrace();
					MineReset.plugin.getLogger().info(configLoadError);

				}
			}
		} else {
			load();
			MineReset.plugin.getLogger().info(loadedConfigFile);
		}

		UpdateConfig.update1to2();
	}

	public void load() {
		try {
			configNode = configLoader.load();

			if (get().getNode("2 - RemindSecondList").getValue() != null) {
				MineReset.remindTimes = new ArrayList<>(Arrays.asList(get().getNode("2 - RemindSecondList").getString().split(", ")));
			}

			if (get().getNode("5 - Lists", "AirBlocks").getValue() != null) {
				MineReset.airBlocks = new ArrayList<>(Arrays.asList(get().getNode("5 - Lists", "AirBlocks").getString().split(", ")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			configLoader.save(configNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CommentedConfigurationNode get() {
		return configNode;
	}

	public void enterData() {

		get().getNode("1 - ConfigMode").setValue(2);
		get().getNode("2 - RemindSecondList").setValue("1, 5, 15, 30, 60, 120, 180, 300");
		get().getNode("3 - Spawn", "SpawnDefault", "SpawnX").setValue(0.5);
		get().getNode("3 - Spawn", "SpawnDefault", "SpawnY").setValue(85.0);
		get().getNode("3 - Spawn", "SpawnDefault", "SpawnZ").setValue(0.5);
		get().getNode("3 - Spawn", "SpawnDefault", "SpawnDirection").setValue("West");
		get().getNode("3 - Spawn", "SpawnDefault", "SpawnWorld").setValue(Sponge.getServer().getDefaultWorld().get().getUniqueId().toString());
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
		get().getNode("6 - ChatSettings", "FillingText").setValue("1");
		get().getNode("6 - ChatSettings", "ReminderText").setValue("2");
		get().getNode("7 - MineFillSignPercentage").setValue(80.0);

		// 0 = disabled
		// 1 = chat
		// 2 = action bar
		// 3 = chat & action bar

		if (get().getNode("2 - RemindSecondList").getValue() != null) {
			MineReset.remindTimes = new ArrayList<>(Arrays.asList(get().getNode("2 - RemindSecondList").getString().split(", ")));
		}

		if (get().getNode("5 - Lists", "AirBlocks").getValue() != null) {
			MineReset.airBlocks = new ArrayList<>(Arrays.asList(get().getNode("5 - Lists", "AirBlocks").getString().split(", ")));
		}
	}
}
