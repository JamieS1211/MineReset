package com.github.jamies1211.minereset.Commands.GroupCommands;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Set;
import java.util.TreeSet;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 28-May-16.
 */
public class DeleteGroup implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		final String group = args.<String>getOne("group").get().toUpperCase();
		final String safe = args.<String>getOne("safe").get();

		if (config.getNode("4 - MineGroups").getChildrenMap().containsKey(group)) {

			Set<Object> listOfMines = new TreeSet<>(config.getNode("4 - MineGroups", group).getChildrenMap().keySet());
			listOfMines.remove("resetTime");
			listOfMines.remove("initialDelay");

			if (listOfMines.isEmpty() || safe.equalsIgnoreCase("force")) {
				config.getNode("4 - MineGroups").removeChild(group);
				GeneralDataConfig.getConfig().save();
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineGroupDeleted.replace("%group%", group)));
			} else if (safe.equalsIgnoreCase("safe")){
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + DeleteGroupFailContents.replace("%group%", group)));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + List));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(MinePrefix + MineGroupDoesNotExist.replace("%group%", group)));
		}

		return CommandResult.success();
	}
}