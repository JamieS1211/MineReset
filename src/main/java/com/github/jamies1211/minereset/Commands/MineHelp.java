package com.github.jamies1211.minereset.Commands;

import com.github.jamies1211.minereset.Messages;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Created by Jamie on 28-May-16.
 */
public class MineHelp implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e&l[Mine Help Start]"));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lHelp Command"));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Help));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.HelpExtendedDescription));

		if (src.hasPermission("minereset.time")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Time));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.TimeExtendedDescription));
		}

		if (src.hasPermission("minereset.reload")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lReload Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Reload));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.ReloadExtendedDescription));
		}

		if (src.hasPermission("minereset.save")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lSave Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Save));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.SaveExtendedDescription));
		}

		if (src.hasPermission("minereset.setspawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lSetSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Setspawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.SetspawnExtendedDescription));
		}

		if (src.hasPermission("minereset.clear")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lClear Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Clear));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.ClearExtendedDescription));
		}

		if (src.hasPermission("minereset.fill")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lFill Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Fill));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.FillExtendedDescription));
		}

		if (src.hasPermission("minereset.definegroup")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.DefineGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.DefineGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.definemine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.DefineMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.DefineMineExtendedDescription));
		}

		if (src.hasPermission("minereset.list")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lList Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.List));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.ListExtendedDescription));
		}

		if (src.hasPermission("minereset.details")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDetails Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Info));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.InfoExtendedDescription));
		}

		if (src.hasPermission("minereset.addore")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd Ore Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Addore));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.AddoreExtendedDescription));
		}

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e&l[Mine Help End]"));
		return CommandResult.success();
	}
}