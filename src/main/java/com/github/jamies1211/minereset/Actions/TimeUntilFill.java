package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 27-May-16.
 */
public class TimeUntilFill {


	private static ConfigurationNode config;

	public static int getTimeUntilFill(String groupName) {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		int secondsSinceStart = MineReset.plugin.secondsSinceStart;

		if (config.getNode("4 - MineGroups", groupName, "resetTime").getValue() == null) {
			config.getNode("4 - MineGroups", groupName, "resetTime").setValue(60);
			GeneralDataConfig.getConfig().get();
		} else if (config.getNode("4 - MineGroups", groupName, "resetTime").getInt() < 60) {
			config.getNode("4 - MineGroups", groupName, "resetTime").setValue(60);
			MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(ResetTimeTooShortError));
			GeneralDataConfig.getConfig().get();
		}

		if (config.getNode("4 - MineGroups", groupName, "initialDelay").getValue() == null) {
			config.getNode("4 - MineGroups", groupName, "initialDelay").setValue(0);
			GeneralDataConfig.getConfig().get();
		}

		int resetTime = config.getNode("4 - MineGroups", groupName, "resetTime").getInt();
		int initialDelay = config.getNode("4 - MineGroups", groupName, "initialDelay").getInt();

		return  (resetTime - 1 - ((secondsSinceStart - initialDelay) % resetTime));
	}
}
