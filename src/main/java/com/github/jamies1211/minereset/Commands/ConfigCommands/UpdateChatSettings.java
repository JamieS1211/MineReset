package com.github.jamies1211.minereset.Commands.ConfigCommands;

import com.github.jamies1211.minereset.Actions.SendMessages;
import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 07-Jul-16.
 */
public class UpdateChatSettings implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		String type = args.<String>getOne("type").get();
		int option = args.<Integer>getOne("option").get();

		if (type.equalsIgnoreCase("FillingText")) {
			type = "FillingText";
		} else if (type.equalsIgnoreCase("ReminderText")) {
			type = "ReminderText";
		} else {
			type = null;
		}

		int oldOption = config.getNode("6 - ChatSettings", type).getInt();

		if (type != null) {
			if (option != oldOption) {
				if (SendMessages.messageToAllPlayers(option, null)) {
					config.getNode("6 - ChatSettings", type).setValue(option); // Wright changes to file.
					GeneralDataConfig.getConfig().save();
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + ChatSettingChanged
							.replace("%type%", type)
							.replace("%option%", Integer.toString(option))));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InvalidChatSetting));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + ChatSettingAlreadySet
						.replace("%type%", type)
						.replace("%option%", Integer.toString(option))));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + InvalidChatType));
		}


		return CommandResult.success();
	}
}
