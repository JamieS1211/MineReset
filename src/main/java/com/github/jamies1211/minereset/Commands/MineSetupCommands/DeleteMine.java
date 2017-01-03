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
 * Created by Jamie on 28-May-16.
 */
public class DeleteMine implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String mine = args.<String>getOne("name").get().toUpperCase();

		String group = GetMineGroup.getMineGroup(mine);

		if (group != null) {
			config.getNode("4 - MineGroups", group).removeChild(mine);
			GeneralDataConfig.getConfig().save();
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + DeletedMine.replace("%mine%", mine)));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineDoesNotExist.replace("%mine%", mine)));
		}

		return CommandResult.success();
	}
}