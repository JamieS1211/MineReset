package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.Messages;
import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 28-May-16.
 */
public class DefineGroup implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		ConfigurationNode config = MineReset.plugin.getConfig();

		final String group = args.<String>getOne("group").get().toUpperCase();
		final int resetTime = args.<Integer>getOne("resetTime").get();
		final int initialDelay = args.<Integer>getOne("initialDelay").get();


		if (config.getNode("4 - MineGroups").getChildrenMap().containsKey(group)) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + group + " " + Messages.MineGroupAlreadyExists));
		} else {
			if (resetTime < 60) {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.ResetTimeTooShort));
			} else if (initialDelay < 0){
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.DelayTooShort));
			} else {
				config.getNode("4 - MineGroups", group, "resetTime").setValue(resetTime);
				config.getNode("4 - MineGroups", group, "initialDelay").setValue(initialDelay);
				MineReset.plugin.save();
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(Messages.MinePrefix + Messages.DefinedNewMineGroup + group));
			}
		}

		return CommandResult.success();
	}
}