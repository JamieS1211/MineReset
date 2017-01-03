package com.github.jamies1211.minereset.Commands.ConfigCommands;

import com.github.jamies1211.minereset.Actions.SendMessages;
import com.github.jamies1211.minereset.Config.GeneralDataInteraction;
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

		String type = args.<String>getOne("type").get();
		int option = args.<Integer>getOne("option").get();
		int oldOption = -1;

		if (type.equalsIgnoreCase("FillingText")) {
			type = "FillingText";
			oldOption = GeneralDataInteraction.getFillingTextSetting();
		} else if (type.equalsIgnoreCase("ReminderText")) {
			type = "ReminderText";
			oldOption = GeneralDataInteraction.getReminderTextSetting();
		} else {
			type = null;
		}

		if (type != null) {
			if (option != oldOption) {
				if (SendMessages.messageToAllPlayers(option, null)) {

					if (type.equalsIgnoreCase("FillingText")) {
						GeneralDataInteraction.setFillingTextSetting(option);
					} else if (type.equalsIgnoreCase("ReminderText")) {
						GeneralDataInteraction.setReminderTextSetting(option);
					}

					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + chatSettingChanged
							.replace("%type%", type)
							.replace("%option%", Integer.toString(option))));
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + invalidChatSetting));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + chatSettingAlreadySet
						.replace("%type%", type)
						.replace("%option%", Integer.toString(option))));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + invalidChatType));
		}


		return CommandResult.success();
	}
}
