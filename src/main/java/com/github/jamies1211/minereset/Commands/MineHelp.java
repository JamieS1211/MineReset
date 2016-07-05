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

		if (src.hasPermission("minereset.fillblock")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lFill Block Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Fillblock));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.FillblockExtendedDescription));
		}

		if (src.hasPermission("minereset.define.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.DefineGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.DefineGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.update.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.UpdateGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.UpdateGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.delete.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDelete Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.DeleteGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.DeleteGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.define.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.DefineMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.DefineMineExtendedDescription));
		}

		if (src.hasPermission("minereset.redefine.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRedefine Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.RedefineMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.RedefineMineExtendedDescription));
		}

		if (src.hasPermission("minereset.delete.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDelete Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.DeleteMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.DeleteMineExtendedDescription));
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

		if (src.hasPermission("minereset.updateore")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Ore Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Updateore));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.UpdateoreExtendedDescription));
		}

		if (src.hasPermission("minereset.updatefallback")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Fallback Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.Updatefallback));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.UpdatefallbackExtendedDescription));
		}

		if (src.hasPermission("minereset.listreminders")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lList Remind Times Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.ListReminder));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.ListReminderExtendedDescription));
		}

		if (src.hasPermission("minereset.addremindtime")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd RemindTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.AddRemindTime));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.AddRemindTimeExtendedDescription));
		}

		if (src.hasPermission("minereset.removeremindtime")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRemove RemindTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Messages.RemoveRemindTime));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + Messages.RemoveRemindTime));
		}

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e&l[Mine Help End]"));
		return CommandResult.success();
	}
}