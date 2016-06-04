package com.github.jamies1211.minereset.Actions;

import org.spongepowered.api.entity.living.player.Player;

/**
 * Created by Jamie on 04-Jun-16.
 */
public class BlockBelowPlayer {

	public static String getBlockStringBelowPlayer(Player player) {
		String block = player.getLocation().sub(0, 1, 0).getBlock().toString();

		if (block.contains("light_")) { // For light blue wool causes error.
			block = block.replace("light_", "light");
		}

		return block;
	}
 }
