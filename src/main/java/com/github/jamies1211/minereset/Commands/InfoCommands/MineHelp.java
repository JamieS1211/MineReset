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

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e&l[Mine help Start]"));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lHelp Command"));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + help));
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + helpExtendedDescription));

		if (src.hasPermission("minereset.time")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + time));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + timeExtendedDescription));
		}

		if (src.hasPermission("minereset.reload")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lReload Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + reload));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + reloadExtendedDescription));
		}

		if (src.hasPermission("minereset.save")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lSave Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + save));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + saveExtendedDescription));
		}

		if (src.hasPermission("minereset.addspawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAddSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + addSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + addSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.removespawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRemoveSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + removeSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + removeSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.updatespawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdateSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + updateSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + updateSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.changespawn")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lChangeSpawn Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + changeSpawn));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + changeSpawnExtendedDescription));
		}

		if (src.hasPermission("minereset.clear")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lClear Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + clear));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + clearExtendedDescription));
		}

		if (src.hasPermission("minereset.fill")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lFill Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + fill));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + fillExtendedDescription));
		}

		if (src.hasPermission("minereset.fillblock")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lFill Block Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + fillblock));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + fillblockExtendedDescription));
		}

		if (src.hasPermission("minereset.define.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + defineGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + defineGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.update.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + updateGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + updateGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.delete.group")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDelete Group Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + deleteGroup));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + deleteGroupExtendedDescription));
		}

		if (src.hasPermission("minereset.define.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDefine Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + defineMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + defineMineExtendedDescription));
		}

		if (src.hasPermission("minereset.redefine.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRedefine Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + redefineMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + redefineMineExtendedDescription));
		}

		if (src.hasPermission("minereset.delete.mine")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDelete Mine Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + deleteMine));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + deleteMineExtendedDescription));
		}

		if (src.hasPermission("minereset.list")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lList Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + list));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + listExtendedDescription));
		}

		if (src.hasPermission("minereset.details")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lDetails Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + info));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + infoExtendedDescription));
		}

		if (src.hasPermission("minereset.addore")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd Ore Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + addore));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + addoreExtendedDescription));
		}

		if (src.hasPermission("minereset.updateore")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Ore Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + updateore));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + updateoreExtendedDescription));
		}

		if (src.hasPermission("minereset.updatefallback")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate Fallback Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + updatefallback));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + updatefallbackExtendedDescription));
		}

		if (src.hasPermission("minereset.listreminders")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lList Remind Times Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + listReminder));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + listReminderExtendedDescription));
		}

		if (src.hasPermission("minereset.addremindtime")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd RemindTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + addRemindTime));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + addRemindTimeExtendedDescription));
		}

		if (src.hasPermission("minereset.removeremindtime")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRemove RemindTime Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + removeRemindTime));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + removeRemindTimeExtendedDescription));
		}

		if (src.hasPermission("minereset.setup.smartfill")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lSetup SmartFill Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + mineSetupSmartFill));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + mineSetupSmartFillExtendedDescription));
		}

		if (src.hasPermission("minereset.addairblock")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lAdd AirBlock Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + addAirBlock));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + addAirBlockExtendedDescription));
		}

		if (src.hasPermission("minereset.removeairblock")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lRemove AirBlock Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + removeAirBlock));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + removeAirBlockExtendedDescription));
		}

		if (src.hasPermission("minereset.update.chatSettings")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate ChatSettings Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + updateChatSettings));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + updateChatSettingsExtendedDescription));
		}

		if (src.hasPermission("minereset.update.signfillpercentage")) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&9&lUpdate SignFillPercentage Command"));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eUsage: &c" + updateSignFillPercentage));
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e" + updateSignFillPercentageExtendedDescription));
		}

		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&e&l[Mine help End]"));
		return CommandResult.success();
	}
}