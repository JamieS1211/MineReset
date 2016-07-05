package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.property.block.MatterProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Jamie on 05-Jul-16.
 */
public class IsSolidBlock {

	public static boolean isSold(BlockType block) {

		ConfigurationNode config = MineReset.plugin.getConfig();

		Optional<MatterProperty> matterProperty = block.getProperty(MatterProperty.class);

		if (matterProperty.get().getValue() == MatterProperty.Matter.GAS) {
			return false;
		} else if (matterProperty.get().getValue() == MatterProperty.Matter.LIQUID) {
			return false;
		}

		if (config.getNode("5 - Lists", "AirBlocks").getValue() == null) {
			config.getNode("5 - Lists", "AirBlocks").setValue("minecraft:torch, minecraft:redstone_torch, minecraft:redstone_wire, minecraft:powered_repeater, minecraft:unpowered_repeater, minecraft:powered_comparator, " +
					"minecraft:unpowered_comparator, minecraft:wooden_button, minecraft:stone_button, minecraft:lever, minecraft:tripwire_hook, minecraft:stone_pressure_plate, " +
					"minecraft:wooden_pressure_plate, minecraft:light_weighted_pressure_plate, minecraft:heavy_weighted_pressure_plate, minecraft:trapdoor, minecraft:iron_trapdoor, " +
					"minecraft:fence_gate, minecraft:spruce_fence_gate, minecraft:birch_fence_gate, minecraft:jungle_fence_gate, minecraft:dark_oak_fence_gate, minecraft:acacia_fence_gate, " +
					"minecraft:wooden_door, minecraft:iron_door, minecraft:spruce_door, minecraft:birch_door, minecraft:jungle_door, minecraft:acacia_door, minecraft:dark_oak_door, " +
					"minecraft:rail, minecraft:golden_rail, minecraft:detector_rail, minecraft:activator_rail, minecraft:tallgrass, minecraft:sapling, minecraft:deadbush, " +
					"minecraft:yellow_flower, minecraft:red_flower, minecraft:brown_mushroom, minecraft:red_mushroom, minecraft:ladder, minecraft:snow_layer, minecraft:fence, " +
					"minecraft:nether_brick_fence, minecraft:iron_bars, minecraft:glass_pane, minecraft:vine, minecraft:waterlily, minecraft:cobblestone_wall, minecraft:anvil, " +
					"minecraft:stained_glass_pane, minecraft:carpet, minecraft:double_plant, minecraft:wall_sign, minecraft:standing_sign, minecraft:skull, minecraft:brewing_stand, " +
					"minecraft:skull, minecraft:standing_banner");
			MineReset.plugin.save();
		}

		ArrayList<String> safeBlocks = new ArrayList<>(Arrays.asList(config.getNode("5 - Lists", "AirBlocks").getString().split(", ")));

		if (safeBlocks.contains(block.getName())) {
			return false;
		}

		return true;
	}
}
