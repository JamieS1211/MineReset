package com.github.jamies1211.minereset.Commands.InfoCommands;

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
public class MineHelp implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e&l[Mine Help Start]"));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lHelp Command"));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Help));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + HelpExtendedDescription));

		if (src.hasPermission("minereset.time")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Time));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + TimeExtendedDescription));
		}

		if (src.hasPermission("minereset.reload")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lReload Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Reload));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + ReloadExtendedDescription));
		}

		if (src.hasPermission("minereset.save")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lSave Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Save));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + SaveExtendedDescription));
		}

		if (src.hasPermission("minereset.addspawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAddSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + AddSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + AddSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.removespawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRemoveSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + RemoveSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + RemoveSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.updatespawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdateSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + UpdateSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + UpdateSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.changespawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lChangeSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + ChangeSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + ChangeSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.clear")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lClear Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Clear));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + ClearExtendedDescription));
		}

		if (src.hasPermission("minereset.fill")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lFill Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Fill));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + FillExtendedDescription));
		}

		if (src.hasPermission("minereset.fillblock")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lFill Block Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Fillblock));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + FillblockExtendedDescription));
		}

		if (src.hasPermission("minereset.define.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + DefineGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + DefineGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.update.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + UpdateGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + UpdateGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.delete.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDelete Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + DeleteGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + DeleteGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.define.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + DefineMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + DefineMineExtendedDescription));
		}

		if (src.hasPermission("minereset.redefine.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRedefine Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + RedefineMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + RedefineMineExtendedDescription));
		}

		if (src.hasPermission("minereset.delete.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDelete Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + DeleteMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + DeleteMineExtendedDescription));
		}

		if (src.hasPermission("minereset.list")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lList Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + List));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + ListExtendedDescription));
		}

		if (src.hasPermission("minereset.details")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDetails Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Info));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + InfoExtendedDescription));
		}

		if (src.hasPermission("minereset.addore")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd Ore Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Addore));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + AddoreExtendedDescription));
		}

		if (src.hasPermission("minereset.updateore")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Ore Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Updateore));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + UpdateoreExtendedDescription));
		}

		if (src.hasPermission("minereset.updatefallback")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Fallback Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + Updatefallback));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + UpdatefallbackExtendedDescription));
		}

		if (src.hasPermission("minereset.listreminders")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lList Remind Times Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + ListReminder));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + ListReminderExtendedDescription));
		}

		if (src.hasPermission("minereset.addremindtime")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd RemindTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + AddRemindTime));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + AddRemindTimeExtendedDescription));
		}

		if (src.hasPermission("minereset.removeremindtime")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRemove RemindTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + RemoveRemindTime));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + RemoveRemindTimeExtendedDescription));
		}

		if (src.hasPermission("minereset.setup.smartfill")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lSetup SmartFill Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + MineSetupSmartFill));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + MineSetupSmartFillExtendedDescription));
		}

		if (src.hasPermission("minereset.addairblock")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd AirBlock Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + AddAirBlock));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + AddAirBlockExtendedDescription));
		}

		if (src.hasPermission("minereset.removeairblock")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRemove AirBlock Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + RemoveAirBlock));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + RemoveAirBlockExtendedDescription));
		}

		if (src.hasPermission("minereset.update.chatSettings")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate ChatSettings Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + UpdateChatSettings));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + UpdateChatSettingsExtendedDescription));
		}

		if (src.hasPermission("minereset.update.signfillpercentage")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate SignFillPercentage Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + UpdateSignFillPercentage));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + UpdateSignFillPercentageExtendedDescription));
		}

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e&l[Mine Help End]"));
		return CommandResult.success();
	}
}