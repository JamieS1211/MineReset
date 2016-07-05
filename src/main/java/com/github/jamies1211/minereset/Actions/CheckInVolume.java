package com.github.jamies1211.minereset.Actions;

import org.spongepowered.api.entity.living.player.Player;

/**
 * Created by Jamie on 05-Jul-16.
 */
public class CheckInVolume {

	public static Boolean checkInVolume (Player player, int xSmall, int ySmall, int zSmall, int xLarge, int yLarge, int zLarge) {

		return player.getLocation().getX() >= xSmall && player.getLocation().getX() - 1 <= xLarge &&
				player.getLocation().getY() >= ySmall && player.getLocation().getY() <= yLarge &&
				player.getLocation().getZ() >= zSmall && player.getLocation().getZ() - 1 <= zLarge;
	}
}
