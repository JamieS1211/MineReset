package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
import com.github.jamies1211.minereset.Config.PlayerDataConfig;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;

import java.util.ArrayList;
import java.util.Set;

import static com.github.jamies1211.minereset.Messages.*;

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
				String titleMessage = minePrefix;
				String subtitleMessage = message.replace(minePrefix, "&e");
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

		Set optedOutPlayers = PlayerDataConfig.getPlayersOptedOutOfMessages();
		ArrayList<Player> playersToMessage =  new ArrayList<>();


		for (Player player : Sponge.getServer().getOnlinePlayers()) {
			if (!optedOutPlayers.contains(player.getUniqueId().toString())) {
				playersToMessage.add(player);
			}
		}

		// Title
		if (messageType >= 4) {
			messageType -= 4;
			// Send title message;
			for (Player player : playersToMessage) {
				if (message != null) {
					String titleMessage = minePrefix;
					String subtitleMessage = message.replace(minePrefix, "&e");
					sendTitleMessage(player, titleMessage, subtitleMessage);
				}
			}
		}


		// Action Bar
		if (messageType >= 2) {
			messageType -= 2;
			for (Player player : playersToMessage) {
				if (message != null) {
					// Send actionBar message;
					player.sendMessage(ChatTypes.ACTION_BAR, TextSerializers.FORMATTING_CODE.deserialize(message));
				}
			}
		}


		// Chat
		if (messageType >= 1) {
			messageType -= 1;
			for (Player player : playersToMessage) {
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

		int fadeInTicks = GeneralDataInteraction.getTitleFadeIn();
		int stayTicks = GeneralDataInteraction.getTitleStay();
		int fadeOutTicks = GeneralDataInteraction.getTitleFadeOut();

		player.sendTitle(Title.builder().title(title).subtitle(subTitle).fadeIn(fadeInTicks).stay(stayTicks).fadeOut(fadeOutTicks).build());
	}
}
