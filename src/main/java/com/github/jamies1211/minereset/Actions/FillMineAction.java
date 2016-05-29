package com.github.jamies1211.minereset.Actions;

import com.flowpowered.math.vector.Vector3d;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.world.World;

import java.util.*;

/**
 * Created by Jamie on 27-May-16.
 */
public class FillMineAction {
	public static void fill(String group, String mine, String definedBlock) {

		ConfigurationNode config = MineReset.plugin.getConfig();

		/** Mine data */
		int x1 = config.getNode("4 - MineGroups", group, mine, "pos1", "x").getInt();
		int y1 = config.getNode("4 - MineGroups", group, mine, "pos1", "y").getInt();
		int z1 = config.getNode("4 - MineGroups", group, mine, "pos1", "z").getInt();
		int x2 = config.getNode("4 - MineGroups", group, mine, "pos2", "x").getInt();
		int y2 = config.getNode("4 - MineGroups", group, mine, "pos2", "y").getInt();
		int z2 = config.getNode("4 - MineGroups", group, mine, "pos2", "z").getInt();
		String mineWorldString = config.getNode("4 - MineGroups", group, mine, "MineWorld").getString();


		/** Spawn data */
		double xSpawn = config.getNode("3 - Spawn", "SpawnX").getDouble();
		double ySpawn = config.getNode("3 - Spawn", "SpawnY").getDouble();
		double zSpawn = config.getNode("3 - Spawn", "SpawnZ").getDouble();
		String direction = config.getNode("3 - Spawn", "SpawnDirection").getString();
		String spawnWorldString = config.getNode("3 - Spawn", "SpawnWorld").getString();
		UUID spawnWorldUUID = UUID.fromString(spawnWorldString);

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

		for (Player player : Sponge.getServer().getOnlinePlayers()) {

			if (player.getWorld().getUniqueId().toString().equalsIgnoreCase(mineWorldString)) {

				if (player.getLocation().getX() >= xSmall && player.getLocation().getX() - 1 <= xLarge) {
					if (player.getLocation().getY() >= ySmall && player.getLocation().getY() <= yLarge) {
						if (player.getLocation().getZ() >= zSmall && player.getLocation().getZ() - 1 <= zLarge) {

							player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&l[Mines]&r &9The mine you were in has just been reset so you have been teleported to spawn."));

							double targetPitch = player.getRotation().getX();
							double targetYaw = player.getRotation().getY();
							double targetRoll = player.getRotation().getZ();

							if (direction.equalsIgnoreCase("North")) {
								targetPitch = 0;
								targetYaw = 180;
								targetRoll = 180;
							} else if (direction.equalsIgnoreCase("South")) {
								targetPitch = 0;
								targetYaw = 0;
								targetRoll = 0;
							} else if (direction.equalsIgnoreCase("East")) {
								targetPitch = 0;
								targetYaw = 270;
								targetRoll = 270;
							} else if (direction.equalsIgnoreCase("West")) {
								targetPitch = 0;
								targetYaw = 90;
								targetRoll = 90;
							} else {
								System.out.println("Teleport function direction not valid so unused");
							}

							Vector3d spawn = new Vector3d(xSpawn, ySpawn, zSpawn);
							Vector3d spawnRotation = new Vector3d(targetPitch, targetYaw, targetRoll);

							player.transferToWorld(spawnWorldUUID, spawn);
							player.setRotation(spawnRotation);
						}
					}
				}
			}
		}

		for (int x = xSmall; x <= xLarge; x++) {
			for (int y = ySmall; y <= yLarge; y++) {
				for (int z = zSmall; z <= zLarge; z++) {

					DataContainer dataContainer = new MemoryDataContainer();

					DataContainer cont;

					if (definedBlock == null) {
						cont = dataContainer.set(DataQuery.of("BlockState"), getRandomBlock(group, mine)); // If mine being normally filled
					} else {
						cont = dataContainer.set(DataQuery.of("BlockState"), definedBlock); // If mine being cleared
					}

					BlockState state = BlockState.builder().build(cont).get();

					for (World world : Sponge.getServer().getWorlds()) {
						if (world.getUniqueId().toString().equalsIgnoreCase(mineWorldString)) {
							world.getLocation(x, y, z).setBlock(state);
						}
					}

				}
			}
		}
	}

	public static String getRandomBlock (String group, String mine) {

		ConfigurationNode config = MineReset.plugin.getConfig();

		HashMap<Integer, String> blockMap = new HashMap<Integer, String>();
		HashMap<Integer, Float> blockPercentages = new HashMap<Integer, Float>();
		String FallbackBlock = config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").getString();

		blockMap.put(1, "minecraft:stone[variant=stone]");
		blockPercentages.put(1, 100f);
		int currentItemCount = 1;

		for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) { // For all ores in a mine
			if (!groupItems.toString().equalsIgnoreCase("fallback")) { // If it is not the fallback
				currentItemCount++;

				String itemName = groupItems.toString();

				String blockStateString = config.getNode("4 - MineGroups", group, mine, "ores", itemName, "BlockState").getString();
				float percentage = config.getNode("4 - MineGroups", group, mine, "ores", groupItems.toString(), "percentage").getFloat();

				float fallBackPercentage = blockPercentages.get(1);
				float updatedFallBackPercentage = fallBackPercentage - percentage;

				blockPercentages.put(1, updatedFallBackPercentage); // Updates the fall back percentage.

				blockMap.put(currentItemCount, blockStateString); // Places the new block.
				blockPercentages.put(currentItemCount, percentage); // Adds percentage for the new block.
			}
		}


		float RandomlyGeneratedChanceValue = (new Random().nextFloat() * 100);
		float currentTotalPercentate = 0;
		int numberOfItemsToIterate = blockMap.size();
		String Block = null;

		for (int currentItemIteration = 1; currentItemIteration <= numberOfItemsToIterate; currentItemIteration++) {
			currentTotalPercentate = currentTotalPercentate + blockPercentages.get(currentItemIteration); // Updates the working percentage.]
			float nextTotalPercentate = 100;
			if ((currentItemIteration + 1) < numberOfItemsToIterate) {
				nextTotalPercentate = currentTotalPercentate + blockPercentages.get(currentItemIteration + 1);
			}

			if ((currentTotalPercentate <= RandomlyGeneratedChanceValue) && (nextTotalPercentate >= RandomlyGeneratedChanceValue)) {
				Block = blockMap.get(currentItemIteration + 1);
			}
		}

		if (Block == null) {
			return FallbackBlock;
		}
		return Block;
	}
}
