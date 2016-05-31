package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 27-May-16.
 */
public class TimeUntillFill {


	private static ConfigurationNode config;

	public static int getTimeUntilFill(String groupName) {

		config = MineReset.plugin.getConfig();

		int secondsSinceStart = MineReset.plugin.secondsSinceStart;

		int resetTime = config.getNode("4 - MineGroups", groupName, "resetTime").getInt();
		int initialDelay = config.getNode("4 - MineGroups", groupName, "initialDelay").getInt();

		if (resetTime < 60) {
			resetTime = 60;
			MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(Messages.ResetTimeTooShortError));
		}

		int timeUntilNextFill = (resetTime - 1 - ((secondsSinceStart - initialDelay) % resetTime));



		return timeUntilNextFill;
	}
}
