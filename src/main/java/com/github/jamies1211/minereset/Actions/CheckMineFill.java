package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import com.github.jamies1211.minereset.Config.MineLocation;
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

		// Mine data
		String mineWorldString = GeneralDataInteraction.getMineWorldString(group, mine);
		UUID mineWorldUUID = UUID.fromString(mineWorldString);

		if (!Sponge.getServer().getWorld(mineWorldUUID).isPresent()) {

			String message = worldNotFound.replace("%mine%", mine);

			if (!SendMessages.messageToPlayer(player, 1, message)) {
				MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + invalidFillChatType));
			}

			MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(message));
			return 100.0;

		} else {
			// Check what blocks are air

			int airCount = 0;

			World world = Sponge.getServer().getWorld(mineWorldUUID).get();
			MineLocation mineLocation = new MineLocation(mine, group);

			for (int x = mineLocation.posSmall.getX(); x <= mineLocation.posLarge.getX(); x++) {
				for (int y = mineLocation.posSmall.getY(); y <= mineLocation.posLarge.getY(); y++) {
					for (int z = mineLocation.posSmall.getZ(); z <= mineLocation.posLarge.getZ(); z++) {
						if (world.getBlock(x, y, z).getName().equalsIgnoreCase(minecraftAirString)) {
							airCount++;
						}
					}
				}
			}

			int totalBlocks = (mineLocation.posLarge.getX() - mineLocation.posSmall.getX()) *
					(mineLocation.posLarge.getY() - mineLocation.posSmall.getY()) *
					(mineLocation.posLarge.getZ() - mineLocation.posSmall.getZ());

			float airPercentage = (airCount * 100) / totalBlocks;
			return (double) 100 - airPercentage;
		}
	}
}
