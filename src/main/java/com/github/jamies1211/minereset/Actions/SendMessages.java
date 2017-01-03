package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Messages;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;

/**
 * Created by Jamie on 07-Jul-16.
 */
public class SendMessages {
	public static boolean messageToPlayer (Player player, int messageType, String message) {

		// Title
		if (messageType >= 4) {
			messageType -= 4;
			// Send title message;
			if (message != null) {
				String titleMessage = Messages.MinePrefix;
				String subtitleMessage = message.replace(Messages.MinePrefix, "&e");
				sendTitleMessage(player, titleMessage, subtitleMessage);
			}
		}


		// Action Bar
		if (messageType >= 2) {
			messageType -= 2;
			if (message != null) {
				// Send actionBar message;
				player.sendMessage(ChatTypes.ACTION_BAR, TextSerializers.FORMATTING_CODE.deserialize(message));
			}
		}


		// Chat
		if (messageType >= 1) {
			messageType -= 1;
			if (message != null) {
				// Send chat message;
				player.sendMessage(ChatTypes.CHAT, TextSerializers.FORMATTING_CODE.deserialize(message));
			}
		}

		return (messageType == 0);
	}

	public static boolean messageToAllPlayers (int messageType, String message) {

		// Title
		if (messageType >= 4) {
			messageType -= 4;
			// Send title message;
			for (Player player : Sponge.getServer().getOnlinePlayers()) {
				if (message != null) {
					String titleMessage = Messages.MinePrefix;
					String subtitleMessage = message.replace(Messages.MinePrefix, "&e");
					sendTitleMessage(player, titleMessage, subtitleMessage);
				}
			}
		}


		// Action Bar
		if (messageType >= 2) {
			messageType -= 2;
			for (Player player : Sponge.getServer().getOnlinePlayers()) {
				if (message != null) {
					// Send actionBar message;
					player.sendMessage(ChatTypes.ACTION_BAR, TextSerializers.FORMATTING_CODE.deserialize(message));
				}
			}
		}


		// Chat
		if (messageType >= 1) {
			messageType -= 1;
			for (Player player : Sponge.getServer().getOnlinePlayers()) {
				if (message != null) {
					// Send chat message;
					player.sendMessage(ChatTypes.CHAT, TextSerializers.FORMATTING_CODE.deserialize(message));
				}
			}
		}

		return (messageType == 0);
	}

	public static void sendTitleMessage (Player player, String titleString, String subTitleString) {

		if (titleString == null) {
			titleString = "";
		}

		if (subTitleString == null) {
			subTitleString = "";
		}

		Text title = TextSerializers.FORMATTING_CODE.deserialize(titleString);
		Text subTitle = TextSerializers.FORMATTING_CODE.deserialize(subTitleString);

		player.sendTitle(Title.of(title, subTitle));
	}
}
