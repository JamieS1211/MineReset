package com.github.jamies1211.minereset.Actions;

import com.flowpowered.math.vector.Vector3d;
import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.*;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 27-May-16.
 */
public class FillMineAction {

	public static void fill(String group, String mine, String definedBlock, CommandSource src) {

		Long startTime = System.currentTimeMillis();

		ConfigurationNode config = MineReset.plugin.getConfig();

		DataContainer dataContainer = new MemoryDataContainer();
		DataContainer cont = null;

		if (definedBlock != null) {
			cont = dataContainer.set(DataQuery.of("BlockState"), definedBlock); // If mine being cleared
		}

		/** Mine data */
		int x1 = config.getNode("4 - MineGroups", group, mine, "pos1", "x").getInt();
		int y1 = config.getNode("4 - MineGroups", group, mine, "pos1", "y").getInt();
		int z1 = config.getNode("4 - MineGroups", group, mine, "pos1", "z").getInt();
		int x2 = config.getNode("4 - MineGroups", group, mine, "pos2", "x").getInt();
		int y2 = config.getNode("4 - MineGroups", group, mine, "pos2", "y").getInt();
		int z2 = config.getNode("4 - MineGroups", group, mine, "pos2", "z").getInt();
		String mineWorldString = config.getNode("4 - MineGroups", group, mine, "MineWorld").getString();
		UUID mineWorldUUID = UUID.fromString(mineWorldString);

		if (!Sponge.getServer().getWorld(mineWorldUUID).isPresent()) {

			if (src instanceof Player) {
				Player srcPlayer = (Player) src;

				if (!SendMessages.messageToPlayer(srcPlayer, 1, WorldNotFound + mine)) {
					MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InvalidFillChatType));
				}
			}

			MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(WorldNotFound + mine));
		} else {

			if (src != null) {
				int fillingChatType = config.getNode("6 - ChatSettings", "FillingText").getInt();

				String sentMessage = ResettingNowSingular;

				if (definedBlock != null) {
					if (definedBlock.equalsIgnoreCase("minecraft:air")) {
						sentMessage = ResettingNowClear;
					} else {
						BlockState state = BlockState.builder().build(cont).get();
						sentMessage = ResettingNowDefined.replace("%block%", state.getType().getName().split(":", 2)[1]);
					}
				}

				if (!SendMessages.messageToAllPlayers(fillingChatType, MinePrefix + "[" + mine + "]" + " " + sentMessage)) {
					MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InvalidFillChatType));
				}

				MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix +
						"[" + mine + "]" + " " + sentMessage));
			}

			/** General data */
			if (config.getNode("4 - MineGroups", group, mine, "SmartFill").getValue() == null) {
				config.getNode("4 - MineGroups", group, mine, "SmartFill").setValue("false");
				MineReset.plugin.save();
			}

			if (config.getNode("4 - MineGroups", group, mine, "SmartFillRadius").getValue() == null) {
				config.getNode("4 - MineGroups", group, mine, "SmartFillRadius").setValue("3");
				MineReset.plugin.save();
			}

			if (config.getNode("4 - MineGroups", group, mine, "SmartFillOnlyAir").getValue() == null) {
				config.getNode("4 - MineGroups", group, mine, "SmartFillOnlyAir").setValue("false");
				MineReset.plugin.save();
			}

			if (config.getNode("4 - MineGroups", group, mine, "SpawnPoint").getValue() == null) {
				config.getNode("4 - MineGroups", group, mine, "SpawnPoint").setValue("Default");
				MineReset.plugin.save();
			}


			Boolean useSmartFill = config.getNode("4 - MineGroups", group, mine, "SmartFill").getBoolean();
			int safeRadius = config.getNode("4 - MineGroups", group, mine, "SmartFillRadius").getInt();
			Boolean smartFillOnlyAir = config.getNode("4 - MineGroups", group, mine, "SmartFillOnlyAir").getBoolean();
			String spawnPoint = "Default";
			if (config.getNode("3 - Spawn").getChildrenMap().keySet().contains("Spawn" + spawnPoint)) {
				spawnPoint = config.getNode("4 - MineGroups", group, mine, "SpawnPoint").getString();
			}

			/** Spawn data */
			double xSpawn = config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnX").getDouble();
			double ySpawn = config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnY").getDouble();
			double zSpawn = config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnZ").getDouble();
			String direction = config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnDirection").getString();
			String spawnWorldString = config.getNode("3 - Spawn", "Spawn" + spawnPoint, "SpawnWorld").getString();
			UUID spawnWorldUUID = UUID.fromString(spawnWorldString);

			/** Manipulate mine co-ordinates */
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

			/** Handle teleportation and location if player in mine */
			ArrayList<Vector3d> locationList = new ArrayList<>();
			for (Player player : Sponge.getServer().getOnlinePlayers()) {
				if (player.getWorld().getUniqueId().toString().equalsIgnoreCase(mineWorldString)) { // If player in world.
					if (CheckInVolume.checkInVolume(player, xSmall, ySmall, zSmall, xLarge, yLarge, zLarge)) { // If player in mine.
						if (definedBlock == null || !definedBlock.equalsIgnoreCase("minecraft:air")) { // If end block is not air

							if (useSmartFill) { // If using smart fill.

								locationList.add(player.getLocation().getPosition());

							} else { // If not using smart fill.

								player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InsideFillingMine));
								double targetYaw = 180, targetRoll = 180;

								if (direction.equalsIgnoreCase("North")) {
									targetYaw = 180;
									targetRoll = 180;
								} else if (direction.equalsIgnoreCase("South")) {
									targetYaw = 0;
									targetRoll = 0;
								} else if (direction.equalsIgnoreCase("East")) {
									targetYaw = 270;
									targetRoll = 270;
								} else if (direction.equalsIgnoreCase("West")) {
									targetYaw = 90;
									targetRoll = 90;
								} else {
									MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(TeleportRotationError));
								}

								Vector3d spawn = new Vector3d(xSpawn, ySpawn, zSpawn);
								Vector3d spawnRotation = new Vector3d(0, targetYaw, targetRoll);

								player.setLocation(Sponge.getServer().getWorld(spawnWorldUUID).get().getLocation(spawn));
								player.setRotation(spawnRotation);
							}
						}
					}
				}
			}

			/** Generate all the mine data maps */
			HashMap<Integer, BlockInfo> blockMap = new HashMap<>();
			String FallbackBlock = config.getNode("4 - MineGroups", group, mine, "ores", "fallback", "BlockState").getString();

			blockMap.put(1, new BlockInfo("minecraft:stone[variant=stone]"));
			int currentItemCount = 1;

			for (final Object groupItems : config.getNode("4 - MineGroups", group, mine, "ores").getChildrenMap().keySet()) { // For all ores in a mine
				if (!groupItems.toString().equalsIgnoreCase("fallback")) { // If it is not the fallback
					currentItemCount++;

					String itemName = groupItems.toString();

					String blockStateString = config.getNode("4 - MineGroups", group, mine, "ores", itemName, "BlockState").getString();
					double percentage = config.getNode("4 - MineGroups", group, mine, "ores", groupItems.toString(), "percentage").getFloat();

					double fallBackPercentage = blockMap.get(1).percentage;

					blockMap.get(1).percentage = fallBackPercentage - percentage; // Updates the fall back percentage.

					blockMap.put(currentItemCount, new BlockInfo(blockStateString, percentage)); // Places the new block and percentage.
				}
			}

			/** Place the correct block for each block in the mine */
			int placeError = 0;

			for (int x = xSmall; x <= xLarge; x++) {
				for (int y = ySmall; y <= yLarge; y++) {
					for (int z = zSmall; z <= zLarge; z++) {

						Boolean placement = true;

						if (useSmartFill) { // If using smart fill.
							if (definedBlock == null || !definedBlock.equalsIgnoreCase("minecraft:air")) { // If end block is not air
								for (Vector3d playerLocation : locationList) {
									if (playerLocation.distance(x, y, z) <= safeRadius) { // If block in safety zone.
										if (!smartFillOnlyAir) { // If not using only air cancel block place.
											placement = false;
											break;
										} else { // If using only air make sure start block is solid.
											if (!IsSolidBlock.isSold(Sponge.getServer().getWorld(mineWorldUUID).get().getBlock(x, y, z).getType())) {
												placement = false;
												break;
											}
										}
									}
								}
							}
						}

						if (placement) {

							if (definedBlock == null) {
								cont = dataContainer.set(DataQuery.of("BlockState"), getRandomBlock(blockMap, blockMap.size(), FallbackBlock)); // If mine being normally filled
							}

							if (BlockState.builder().build(cont).isPresent()) {
								BlockState state = BlockState.builder().build(cont).get();
								NamedCause cause = NamedCause.source(Sponge.getPluginManager().getPlugin("minereset").get());

								if (!Sponge.getServer().getWorld(mineWorldUUID).get().getBlock(x, y, z).getType().equals(state.getType())) {
									Sponge.getServer().getWorld(mineWorldUUID).get().setBlock(x, y, z, state, Cause.of(cause));
								}

							} else {
								placeError++;
							}
						}
					}
				}
			}

			if (placeError > 0) {
				if (src instanceof Player) {
					Player player = (Player) src;
					player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(BlockPlaceError.replace("%errors%", Integer.toString(placeError))));
				}

				MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(BlockPlaceError.replace("%errors%", Integer.toString(placeError))));
			}

			Long endTime = System.currentTimeMillis();
			Long timeTaken = endTime - startTime;

			MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + Messages.MineFillTimeTaken
					.replace("%mine%", mine)
					.replace("%time%", Long.toString(timeTaken))));
		}
	}

	public static String getRandomBlock (HashMap<Integer, BlockInfo> blockMap, int numberOfItemsToIterate, String FallbackBlock) {

		double RandomlyGeneratedChanceValue = (new Random().nextDouble() * 100);
		double currentTotalPercentage = 0;

		for (int currentItemIteration = 1; currentItemIteration <= numberOfItemsToIterate; currentItemIteration++) {
			double nextTotalPercentage = 100;

			currentTotalPercentage = currentTotalPercentage + blockMap.get(currentItemIteration).percentage; // Updates the working percentage.

			if ((currentItemIteration + 1) < numberOfItemsToIterate) { // If last item round to 100.
				nextTotalPercentage = currentTotalPercentage + blockMap.get(currentItemIteration + 1).percentage;
			}

			if ((currentTotalPercentage <= RandomlyGeneratedChanceValue) && (nextTotalPercentage >= RandomlyGeneratedChanceValue)) { // Random between this and next ore use this ore.
				return blockMap.get(currentItemIteration + 1).blockStateString;
			}
		}

		return FallbackBlock;
	}
}
