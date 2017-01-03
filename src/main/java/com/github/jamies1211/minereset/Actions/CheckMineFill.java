package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.world.World;

import java.util.UUID;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 28/12/2016.
 */
public class CheckMineFill {

	public static double mineFilledPercentage(String group, String mine, Player player) {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		// Mine data
		int x1 = config.getNode("4 - MineGroups", group, mine, "pos1", "x").getInt();
		int y1 = config.getNode("4 - MineGroups", group, mine, "pos1", "y").getInt();
		int z1 = config.getNode("4 - MineGroups", group, mine, "pos1", "z").getInt();
		int x2 = config.getNode("4 - MineGroups", group, mine, "pos2", "x").getInt();
		int y2 = config.getNode("4 - MineGroups", group, mine, "pos2", "y").getInt();
		int z2 = config.getNode("4 - MineGroups", group, mine, "pos2", "z").getInt();
		String mineWorldString = config.getNode("4 - MineGroups", group, mine, "MineWorld").getString();
		UUID mineWorldUUID = UUID.fromString(mineWorldString);

		if (!Sponge.getServer().getWorld(mineWorldUUID).isPresent()) {

			String message = WorldNotFound.replace("%mine%", mine);

			if (!SendMessages.messageToPlayer(player, 1, message)) {
				MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InvalidFillChatType));
			}

			MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(message));
			return 100.0;

		} else {

			// Manipulate mine co-ordinates
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

			// Check what blocks are air

			int airCount = 0;

			World world = Sponge.getServer().getWorld(mineWorldUUID).get();

			for (int x = xSmall; x <= xLarge; x++) {
				for (int y = ySmall; y <= yLarge; y++) {
					for (int z = zSmall; z <= zLarge; z++) {
						if (world.getBlock(x, y, z).getName().equalsIgnoreCase(minecraftAirString)) {
							airCount++;
						}
					}
				}
			}

			int totalBlocks = (xLarge - xSmall) * (yLarge - ySmall) * (zLarge - zSmall);
			float airPercentage = (airCount * 100) / totalBlocks;
			return (double) 100 - airPercentage;
		}
	}
}
