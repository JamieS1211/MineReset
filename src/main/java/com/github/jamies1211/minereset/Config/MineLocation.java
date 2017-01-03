package com.github.jamies1211.minereset.Config;

import com.flowpowered.math.vector.Vector3i;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;

/**
 * Created by Jamie on 03/01/2017.
 */
public class MineLocation {

	public final Vector3i pos1;
	public final Vector3i pos2;
	public final Vector3i posLarge;
	public final Vector3i posSmall;

	/**
	 *
	 * @param mine The mine name as a String
	 * @param group The group name as a String
	 */
	public MineLocation (String mine, String group) {
		CommentedConfigurationNode config = GeneralDataConfig.getConfig().get();

		// Prevent any lowercase errors
		mine = mine.toUpperCase();
		group = group.toUpperCase();

		int x1 = config.getNode("4 - MineGroups", group, mine, "pos1", "x").getInt();
		int y1 = config.getNode("4 - MineGroups", group, mine, "pos1", "y").getInt();
		int z1 = config.getNode("4 - MineGroups", group, mine, "pos1", "z").getInt();
		int x2 = config.getNode("4 - MineGroups", group, mine, "pos2", "x").getInt();
		int y2 = config.getNode("4 - MineGroups", group, mine, "pos2", "y").getInt();
		int z2 = config.getNode("4 - MineGroups", group, mine, "pos2", "z").getInt();

		int xLarge = x1;
		int xSmall = x2;

		if (x2 > x1) {
			xLarge = x2;
			xSmall = x1;
		}

		int yLarge = y1;
		int ySmall = y2;

		if (y2 > y1) {
			yLarge = y2;
			ySmall = y1;
		}

		int zLarge = z1;
		int zSmall = z2;

		if (z2 > z1) {
			zLarge = z2;
			zSmall = z1;
		}

		pos1 = new Vector3i(x1, y1, z1);
		pos2 = new Vector3i(x2, y2, z2);
		posLarge = new Vector3i(xLarge, yLarge, zLarge);
		posSmall = new Vector3i(xSmall, ySmall, zSmall);
	}

	/**
	 *
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 */
	public MineLocation (int x1, int y1, int z1, int x2, int y2, int z2) {

		int xLarge = x1;
		int xSmall = x2;

		if (x2 > x1) {
			xLarge = x2;
			xSmall = x1;
		}

		int yLarge = y1;
		int ySmall = y2;

		if (y2 > y1) {
			yLarge = y2;
			ySmall = y1;
		}

		int zLarge = z1;
		int zSmall = z2;

		if (z2 > z1) {
			zLarge = z2;
			zSmall = z1;
		}

		pos1 = new Vector3i(x1, y1, z1);
		pos2 = new Vector3i(x2, y2, z2);
		posLarge = new Vector3i(xLarge, yLarge, zLarge);
		posSmall = new Vector3i(xSmall, ySmall, zSmall);
	}
}
