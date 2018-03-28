package com.github.jamies1211.minereset.Commands.MineSetupCommands;

import com.github.jamies1211.minereset.Actions.GetMineGroup;
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
 * Created by Jamie on 28-March-18.
 */
public class UpdateDisplayName implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String mine = args.<String>getOne("name").get().toUpperCase();
		final String displayName = args.<String>getOne("displayName").get();

		String group = GetMineGroup.getMineGroup(mine);

		if (group != null) {
			String oldDisplayName = config.getNode("4 - MineGroups", group, mine, "DisplayName").getString();
			config.getNode("4 - MineGroups", group, mine, "DisplayName").setValue(displayName);
			GeneralDataConfig.getConfig().save();
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + updatedDisplayName
					.replace("%mine%", mine).replace("%old%", oldDisplayName).replace("%new%", displayName)));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(minePrefix + mineDoesNotExist.replace("%mine%", mine)));
		}

		return CommandResult.success();
	}
}