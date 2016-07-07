package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;

/**
 * Created by Jamie on 06-Jul-16.
 */
public class UpdateConfig {

	public static void update1to2 () {

		ConfigurationNode config = MineReset.plugin.getConfig();

		if (config.getNode("1 - ConfigMode").getInt() == 1) {
			config.getNode("1 - ConfigMode").setValue("2");

			if (config.getNode("3 - Spawn", "SpawnX").getValue() == null) {
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnX").setValue(0.5);
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnY").setValue(85.0);
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnZ").setValue(0.5);
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnDirection").setValue("West");
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnWorld").setValue(Sponge.getServer().getDefaultWorld().get().getUniqueId().toString());
			} else {
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnX").setValue(config.getNode("3 - Spawn", "SpawnX").getDouble());
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnY").setValue(config.getNode("3 - Spawn", "SpawnY").getDouble());
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnZ").setValue(config.getNode("3 - Spawn", "SpawnS").getDouble());
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnDirection").setValue(config.getNode("3 - Spawn", "SpawnX").getDouble());
				config.getNode("3 - Spawn", "SpawnDefault", "SpawnWorld").setValue(config.getNode("3 - Spawn", "SpawnWorld").getString());


				config.getNode("3 - Spawn").removeChild("SpawnX");
				config.getNode("3 - Spawn").removeChild("SpawnY");
				config.getNode("3 - Spawn").removeChild("SpawnZ");
				config.getNode("3 - Spawn").removeChild("SpawnDirection");
				config.getNode("3 - Spawn").removeChild("SpawnWorld");
			}

			for (Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
				String group = groupObject.toString();
				for (Object objects : config.getNode("4 - MineGroups", group).getChildrenMap().keySet()) {
					if (!objects.toString().equalsIgnoreCase("InitialDelay") && !objects.toString().equalsIgnoreCase("ResetTime")) {
						String mine = objects.toString();
						if (config.getNode("4 - MineGroups", group, mine, "SpawnPoint").getValue() == null) {
							config.getNode("4 - MineGroups", group, mine, "SpawnPoint").setValue("Default");
						}
					}
				}
			}

			config.getNode("5 - Lists", "AirBlocks").setValue(
					"\"minecraft:torch\", \"minecraft:redstone_torch\", \"minecraft:redstone_wire\", \"minecraft:powered_repeater\", \"minecraft:unpowered_repeater\", \"minecraft:powered_comparator\",\n" +
							"\"minecraft:unpowered_comparator\", \"minecraft:wooden_button\", \"minecraft:stone_button\", \"minecraft:lever\", \"minecraft:tripwire_hook\", \"minecraft:stone_pressure_plate\",\n" +
							"\"minecraft:wooden_pressure_plate\", \"minecraft:light_weighted_pressure_plate\", \"minecraft:heavy_weighted_pressure_plate\", \"minecraft:trapdoor\", \"minecraft:iron_trapdoor\",\n" +
							"\"minecraft:fence_gate\", \"minecraft:spruce_fence_gate\", \"minecraft:birch_fence_gate\", \"minecraft:jungle_fence_gate\", \"minecraft:dark_oak_fence_gate\", \"minecraft:acacia_fence_gate\",\n" +
							"\"minecraft:wooden_door\", \"minecraft:iron_door\", \"minecraft:spruce_door\", \"minecraft:birch_door\", \"minecraft:jungle_door\", \"minecraft:acacia_door\", \"minecraft:dark_oak_door\", \n" +
							"\"minecraft:rail\", \"minecraft:golden_rail\", \"minecraft:detector_rail\", \"minecraft:activator_rail\", \"minecraft:tallgrass\", \"minecraft:sapling\", \"minecraft:deadbush\",\n" +
							"\"minecraft:yellow_flower\", \"minecraft:red_flower\", \"minecraft:brown_mushroom\", \"minecraft:red_mushroom\", \"minecraft:ladder\", \"minecraft:snow_layer\", \"minecraft:fence\",\n" +
							"\"minecraft:nether_brick_fence\", \"minecraft:iron_bars\", \"minecraft:glass_pane\", \"minecraft:vine\", \"minecraft:waterlily\", \"minecraft:cobblestone_wall\", \"minecraft:anvil\",\n" +
							"\"minecraft:stained_glass_pane\", \"minecraft:carpet\", \"minecraft:double_plant\", \"minecraft:wall_sign\", \"minecraft:standing_sign\", \"minecraft:skull\", \"minecraft:brewing_stand\",\n" +
							"\"minecraft:skull\", \"minecraft:standing_banner\"");

			config.getNode("6 - ChatSettings", "FillingText").setValue("1");
			config.getNode("6 - ChatSettings", "ReminderText").setValue("2");
			MineReset.plugin.save();
		}
	}

}
