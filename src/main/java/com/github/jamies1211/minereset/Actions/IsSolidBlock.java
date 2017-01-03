package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.property.block.MatterProperty;

import java.util.Optional;

/**
 * Created by Jamie on 05-Jul-16.
 */
public class IsSolidBlock {

	public static boolean isSold(BlockState block) {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		Optional<MatterProperty> matterProperty = block.getProperty(MatterProperty.class);

		if (matterProperty.get().getValue() == MatterProperty.Matter.GAS) {
			return false;
		} else if (matterProperty.get().getValue() == MatterProperty.Matter.LIQUID) {
			return false;
		}

		return !MineReset.airBlocks.contains(block.getName());

	}
}
