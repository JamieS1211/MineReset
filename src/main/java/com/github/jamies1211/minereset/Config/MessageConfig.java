package com.github.jamies1211.minereset.Config;

import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 27-Jul-16.
 */
public class MessageConfig {

	private static MessageConfig config = new MessageConfig();

	public static MessageConfig getConfig() {
		return config;
	}

	public static MessageConfig getConfigFromInteraction() {
		return config;
	}

	private Path configFile = Paths.get(MineReset.getPlugin().getConfigDir() + "/messages.conf");
	private ConfigurationLoader<CommentedConfigurationNode> configLoader = HoconConfigurationLoader.builder().setPath(configFile).build();
	private CommentedConfigurationNode configNode;

	public void setup() {
		if (!Files.exists(configFile)) {
			// Create new config file
			try {
				Files.createFile(configFile);
				load();
				enterData();
				save();
				refreshData();
				MineReset.plugin.getLogger().info(newConfigFile);
			} catch (IOException e) {
				e.printStackTrace();
				MineReset.plugin.getLogger().info(configLoadError);

			}
		} else {
			load();
			refreshData();
			MineReset.plugin.getLogger().info(loadedConfigFile);
		}
	}

	public void load() {
		try {
			configNode = configLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			configLoader.save(configNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CommentedConfigurationNode get() {
		return configNode;
	}

	public void enterData() {


		// General
		getConfig().get().getNode("1 - General" , "minePrefix").setValue("&9&l[Mines]&r &e");
		getConfig().get().getNode("1 - General" , "noPlayerOnline").setValue("mine re-filling disabled as no player is online");
		getConfig().get().getNode("1 - General" , "mineFillTimeTaken").setValue("%mine% was filled in %totalTime% milliseconds with %asyncTime% milliseconds doing async tasks and with %syncTime% milliseconds doing synchronised tasks. Volume: %volume%. Changed blocks: %changedBlocks%");


		// Command Messages
		getConfig().get().getNode("2 - Command Messages", "playerOnlyCommand").setValue("This command must be run by a player");
		getConfig().get().getNode("2 - Command Messages", "configSaved").setValue("The config file has been saved");
		getConfig().get().getNode("2 - Command Messages", "configReloaded").setValue("The config file has been reloaded");
		getConfig().get().getNode("2 - Command Messages", "mineCleared").setValue("%mine% has been cleared");
		getConfig().get().getNode("2 - Command Messages", "resetTime").setValue("%mine% will reset in: %time%");
		getConfig().get().getNode("2 - Command Messages", "mineDoesNotExist").setValue("%mine% does not exist");
		getConfig().get().getNode("2 - Command Messages", "mineGroupDoesNotExist").setValue("%group% is not a mine group");
		getConfig().get().getNode("2 - Command Messages", "noMines").setValue("You have no mines");
		getConfig().get().getNode("2 - Command Messages", "noMineGroups").setValue("You have no mine groups");
		getConfig().get().getNode("2 - Command Messages", "mineAlreadyExists").setValue("%mine% is the name of already existing mine");
		getConfig().get().getNode("2 - Command Messages", "mineGroupAlreadyExists").setValue("%group% is the name of already existing mine group");
		getConfig().get().getNode("2 - Command Messages", "definedNewMine").setValue("You have defined a new mine with the name: %mine%");
		getConfig().get().getNode("2 - Command Messages", "definedNewMineGroup").setValue("You have defined a new mine group with the name: %group%");
		getConfig().get().getNode("2 - Command Messages", "updateMineGroup").setValue("You have updated the mine group with the name: %group%");
		getConfig().get().getNode("2 - Command Messages", "redefinedMine").setValue("You have redefined the mine to the defined information for mine: %mine%");
		getConfig().get().getNode("2 - Command Messages", "resetTimeTooShort").setValue("Your reset time for a new group must be 1 minute or more");
		getConfig().get().getNode("2 - Command Messages", "delayTooShort").setValue("Your delay can't be less than 0");
		getConfig().get().getNode("2 - Command Messages", "addedOre").setValue("You have added: %block% to mine: %mine% at %percentage%%");
		getConfig().get().getNode("2 - Command Messages", "orePercentageError").setValue("The percentage you have listed would increase the mine beyond 100%");
		getConfig().get().getNode("2 - Command Messages", "mineFilledBlock").setValue("%mine% has been filled with: %block%");
		getConfig().get().getNode("2 - Command Messages", "deletedMine").setValue("You have deleted your mine with the name: %mine%");
		getConfig().get().getNode("2 - Command Messages", "mineGroupDeleted").setValue("%group% has been deleted");
		getConfig().get().getNode("2 - Command Messages", "deleteGroupFailContents").setValue("%group% can't be deleted as it has contents and this command was not forced");
		getConfig().get().getNode("2 - Command Messages", "percentageTooSmall").setValue("The percentage value you entered is too small. Percentage must be greater than 0");
		getConfig().get().getNode("2 - Command Messages", "percentageTooSmallUpdate").setValue("The percentage value you entered is too small. Percentage must be greater than 0. A value of 0 will remove the ore from the mine");
		getConfig().get().getNode("2 - Command Messages", "oreRemoved").setValue("%block% has been removed as you entered 0%");
		getConfig().get().getNode("2 - Command Messages", "oreAlreadyInMine").setValue("%block% is already in the listed mine so wasn't added. You can change the percentage of the block using the \"/mine updateore\" command");
		getConfig().get().getNode("2 - Command Messages", "oreNotInMine").setValue("%block% is not in the listed mine so can't be edited");
		getConfig().get().getNode("2 - Command Messages", "unableToAddFallback").setValue("%block% is the default block so you cannot add it to the mine again. It is used to top the mine up to 100% full after all the other ores are taken into account");
		getConfig().get().getNode("2 - Command Messages", "unableToEditFallback").setValue("%block% is the default block so you cannot edit its percentage or remove it. It is used to top the mine up to 100% full after all the other ores are taken into account");
		getConfig().get().getNode("2 - Command Messages", "oreSamePercentageError").setValue("No changes were made because the new percentage is the same as the old percentage for %block% in %mine%");
		getConfig().get().getNode("2 - Command Messages", "changedFallback").setValue("You have changed the fallback block to: %block%");
		getConfig().get().getNode("2 - Command Messages", "sameFallback").setValue("No changes were made because this is already the fallback block");
		getConfig().get().getNode("2 - Command Messages", "remindList").setValue("list of seconds before mine reset a reminder message is issued: %list%");
		getConfig().get().getNode("2 - Command Messages", "remindTimeExist").setValue("That time is already on the list of times to send a reset reminder so was not added");
		getConfig().get().getNode("2 - Command Messages", "remindTimeNotExist").setValue("That time is not on the list of times to send a reset reminder so was not removed");
		getConfig().get().getNode("2 - Command Messages", "remindTimeAdd").setValue("You have added %time% (%readableTime%) to the list of times a message will be sent before a mine reset reminding of a mine reset");
		getConfig().get().getNode("2 - Command Messages", "remindTimeRemove").setValue("You have removed %time% (%readableTime%) from the list of times a message will be sent before a mine reset reminding of a mine reset");
		getConfig().get().getNode("2 - Command Messages", "airBlockExist").setValue("That time is already on the list of times to send a reset reminder so was not added");
		getConfig().get().getNode("2 - Command Messages", "airBlockNotExist").setValue("That block is not on the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings");
		getConfig().get().getNode("2 - Command Messages", "airBlockAdd").setValue("You have added %block% to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings");
		getConfig().get().getNode("2 - Command Messages", "airBlockRemove").setValue("You have removed %block% from the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings");
		getConfig().get().getNode("2 - Command Messages", "addSpawnPoint").setValue("You have added a new spawn point for the current world. SpawnPoint: %spawnName% X:%x% Y:%y% Z:%z% Facing:%facing% WorldUUID:%worldUUIDString%");
		getConfig().get().getNode("2 - Command Messages", "noRemoveDefaultSpawn").setValue("You cannot remove the default spawn point");
		getConfig().get().getNode("2 - Command Messages", "removeSpawnPoint").setValue("You have removed spawn point: Spawn%spawnValue%");
		getConfig().get().getNode("2 - Command Messages", "updatedSpawnPoint").setValue("You have updated the spawn point to X:%x% Y:%y% Z:%z% Facing:%facing% WorldUUID:%worldUUIDString%");
		getConfig().get().getNode("2 - Command Messages", "spawnPointNotExist").setValue("The listed spawn point does not exist");
		getConfig().get().getNode("2 - Command Messages", "maxSpawnCount").setValue("You have reached the maximum number of allowed spawns");
		getConfig().get().getNode("2 - Command Messages", "spawnPointAlreadyInUse").setValue("%mine% already uses that spawn point");
		getConfig().get().getNode("2 - Command Messages", "mineSpawnChanged").setValue("%mine% has been changed to now use Spawn%spawnValue%");
		getConfig().get().getNode("2 - Command Messages", "chatSettingChanged").setValue("You have successfully changed the chat setting for %type% to %option%");
		getConfig().get().getNode("2 - Command Messages", "invalidChatSetting").setValue("The chat setting you listed is invalid. Please refer to documentation");
		getConfig().get().getNode("2 - Command Messages", "chatSettingAlreadySet").setValue("The chat settings for %type% is already %option%");
		getConfig().get().getNode("2 - Command Messages", "invalidChatType").setValue("That is not a valid chat type. Options (FillingText|ReminderText)");
		getConfig().get().getNode("2 - Command Messages", "mineFillSignPercentageUpdated").setValue("You have updated the percentage of blocks that need to exist for mine fill signs to function to %percentage%");
		getConfig().get().getNode("2 - Command Messages", "mineFillSignPercentageInvalid").setValue("The value you entered was invalid. Please enter a value between 0 and 100");
		getConfig().get().getNode("2 - Command Messages", "noDirectionToSpawn").setValue("direction not entered correctly so defaulting to North");
		getConfig().get().getNode("2 - Command Messages", "settingSetMessageOptOut").setValue("You have successfully changed your message settings to block mine fill and remind messages");
		getConfig().get().getNode("2 - Command Messages", "settingSetMessageOptIn").setValue("You have successfully changed your message settings to receive mine fill and remind messages");

		// info command strings
		getConfig().get().getNode("3 - Info Command Strings", "groupInfoHeader").setValue("----------------  Group info  ----------------");
		getConfig().get().getNode("3 - Info Command Strings", "mineInfoHeader").setValue("----------------  Mine info  ----------------");
		getConfig().get().getNode("3 - Info Command Strings", "spawnInfoHeader").setValue("----------------  Spawn info  ----------------");
		getConfig().get().getNode("3 - Info Command Strings", "infoFooter").setValue("---------------------------------------------");

		// Command Usages
		getConfig().get().getNode("4 - Command Usages", "help").setValue("/mine help");
		getConfig().get().getNode("4 - Command Usages", "helpDescription").setValue("Shows mine help");
		getConfig().get().getNode("4 - Command Usages", "helpExtendedDescription").setValue("Use this command to list all the mine sub commands user has access to and how to use them");

		getConfig().get().getNode("4 - Command Usages", "reload").setValue("/mine reload");
		getConfig().get().getNode("4 - Command Usages", "reloadDescription").setValue("Reloads mine config file");
		getConfig().get().getNode("4 - Command Usages", "reloadExtendedDescription").setValue("Reloads mine config file");

		getConfig().get().getNode("4 - Command Usages", "save").setValue("/mine save");
		getConfig().get().getNode("4 - Command Usages", "saveDescription").setValue("Use this command to save mine config");
		getConfig().get().getNode("4 - Command Usages", "saveExtendedDescription").setValue("Saves the mine config being used over one in folder");

		getConfig().get().getNode("4 - Command Usages", "addSpawn").setValue("Usage /mine addspawn [x] [y] [z] [facing]");
		getConfig().get().getNode("4 - Command Usages", "addSpawnDescription").setValue("Adds a new spawn point with the values listed");
		getConfig().get().getNode("4 - Command Usages", "addSpawnExtendedDescription").setValue("Adds a new spawn point with the values listed");

		getConfig().get().getNode("4 - Command Usages", "removeSpawn").setValue("Usage /mine removespawn [spawn point]");
		getConfig().get().getNode("4 - Command Usages", "removeSpawnDescription").setValue("Removes the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn");
		getConfig().get().getNode("4 - Command Usages", "removeSpawnExtendedDescription").setValue("Removes the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn)");

		getConfig().get().getNode("4 - Command Usages", "updateSpawn").setValue("Usage /mine updatespawn [spawn point] [x] [y] [z] [facing]");
		getConfig().get().getNode("4 - Command Usages", "updateSpawnDescription").setValue("Updates the spawn information to the listed values (To alter default spawn use \"Default\" other wise use the number of spawn");
		getConfig().get().getNode("4 - Command Usages", "updateSpawnExtendedDescription").setValue("Updates the spawn information to the listed values (To alter default spawn use \"Default\" other wise use the number of spawn)");

		getConfig().get().getNode("4 - Command Usages", "changeSpawn").setValue("Usage /mine changespawn [mine] [spawn point]");
		getConfig().get().getNode("4 - Command Usages", "changeSpawnDescription").setValue("Set the listed mine to use the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn");
		getConfig().get().getNode("4 - Command Usages", "changeSpawnExtendedDescription").setValue("Set the listed mine to use the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn)");

		getConfig().get().getNode("4 - Command Usages", "time").setValue("/mine time [mine]");
		getConfig().get().getNode("4 - Command Usages", "timeDescription").setValue("Check time remaining before mine resets");
		getConfig().get().getNode("4 - Command Usages", "timeExtendedDescription").setValue("Check time remaining before mine resets");

		getConfig().get().getNode("4 - Command Usages", "clear").setValue("/mine clear [mine]");
		getConfig().get().getNode("4 - Command Usages", "clearDescription").setValue("clear all blocks from a  mine");
		getConfig().get().getNode("4 - Command Usages", "clearExtendedDescription").setValue("clear all block from a mine");

		getConfig().get().getNode("4 - Command Usages", "fill").setValue("/mine fill [mine]");
		getConfig().get().getNode("4 - Command Usages", "fillDescription").setValue("fill a mine according to its blocks and percentages");
		getConfig().get().getNode("4 - Command Usages", "fillExtendedDescription").setValue("fill a mine according to its blocks and percentages");

		getConfig().get().getNode("4 - Command Usages", "fillblock").setValue("/mine fillblock [mine]");
		getConfig().get().getNode("4 - Command Usages", "fillblockDescription").setValue("fill a mine with the block you are currently standing on");
		getConfig().get().getNode("4 - Command Usages", "fillblockExtendedDescription").setValue("fill a mine with the block you are currently standing on");

		getConfig().get().getNode("4 - Command Usages", "defineGroup").setValue("/mine definegroup [group name] [reset time] [initial delay]");
		getConfig().get().getNode("4 - Command Usages", "defineGroupDescription").setValue("Create a new mine group");
		getConfig().get().getNode("4 - Command Usages", "defineGroupExtendedDescription").setValue("Create a new mine group with name, reset time and initial delay as listed");

		getConfig().get().getNode("4 - Command Usages", "updateGroup").setValue("/mine updategroup [group name] [reset time] [initial delay]");
		getConfig().get().getNode("4 - Command Usages", "updateGroupDescription").setValue("Updates an existing mine group");
		getConfig().get().getNode("4 - Command Usages", "updateGroupExtendedDescription").setValue("Updates an existing mine group with name, reset time and initial delay as listed");

		getConfig().get().getNode("4 - Command Usages", "deleteGroup").setValue("/mine deletegroup [group name] [safe|force]");
		getConfig().get().getNode("4 - Command Usages", "deleteGroupDescription").setValue("Deletes an existing mine group");
		getConfig().get().getNode("4 - Command Usages", "deleteGroupExtendedDescription").setValue("Deletes an existing mine group. If safe then deletion will fail if mine group has contents");

		getConfig().get().getNode("4 - Command Usages", "defineMine").setValue("/mine definemine [group] [mine name] [pos1x] [pos1y] [pos1z] [pos2x] [pos2y] [pos2z]");
		getConfig().get().getNode("4 - Command Usages", "defineMineDescription").setValue("Create a new mine");
		getConfig().get().getNode("4 - Command Usages", "defineMineExtendedDescription").setValue("Create a new mine in listed group with name and positions as listed");

		getConfig().get().getNode("4 - Command Usages", "redefineMine").setValue("/mine redefinemine [group] [mine name] [pos1x] [pos1y] [pos1z] [pos2x] [pos2y] [pos2z]");
		getConfig().get().getNode("4 - Command Usages", "redefineMineDescription").setValue("Redefine a mine to change its settings");
		getConfig().get().getNode("4 - Command Usages", "redefineMineExtendedDescription").setValue("Redefine a mine to change its settings to listed group with name and positions as listed");

		getConfig().get().getNode("4 - Command Usages", "deleteMine").setValue("/mine deletemine [mine name]");
		getConfig().get().getNode("4 - Command Usages", "deleteMineDescription").setValue("Delete a mine");
		getConfig().get().getNode("4 - Command Usages", "deleteMineExtendedDescription").setValue("Delete the mine listed");

		getConfig().get().getNode("4 - Command Usages", "list").setValue("Usage /mine list [groups|mines|spawns]");
		getConfig().get().getNode("4 - Command Usages", "listDescription").setValue("list all groups or mines that exist");
		getConfig().get().getNode("4 - Command Usages", "listExtendedDescription").setValue("list all groups or mines that exist");

		getConfig().get().getNode("4 - Command Usages", "info").setValue("Usage /mine info [group|mine|spawn] [name]");
		getConfig().get().getNode("4 - Command Usages", "infoDescription").setValue("Get all info on group or mine");
		getConfig().get().getNode("4 - Command Usages", "infoExtendedDescription").setValue("Get all info on group or mine");

		getConfig().get().getNode("4 - Command Usages", "addore").setValue("Usage /mine addore [mine] [percentage]");
		getConfig().get().getNode("4 - Command Usages", "addoreDescription").setValue("Adds the block you are standing on to listed mine at listed percentage");
		getConfig().get().getNode("4 - Command Usages", "addoreExtendedDescription").setValue("Adds the block you are standing on to listed mine at listed percentage");

		getConfig().get().getNode("4 - Command Usages", "updateore").setValue("Usage /mine updateore [mine] [percentage]");
		getConfig().get().getNode("4 - Command Usages", "updateoreDescription").setValue("Updates the block you are standing on in listed mine to listed percentage");
		getConfig().get().getNode("4 - Command Usages", "updateoreExtendedDescription").setValue("Updates the block you are standing on in listed mine to listed percentage");

		getConfig().get().getNode("4 - Command Usages", "updatefallback").setValue("Usage /mine updatefallback [mine]");
		getConfig().get().getNode("4 - Command Usages", "updatefallbackDescription").setValue("Updates the mine listed to use the block you are standing on as the fallback block");
		getConfig().get().getNode("4 - Command Usages", "updatefallbackExtendedDescription").setValue("Updates the mine listed to use the block you are standing on as the fallback block");

		getConfig().get().getNode("4 - Command Usages", "listReminder").setValue("Usage /mine listreminders");
		getConfig().get().getNode("4 - Command Usages", "listReminderDescription").setValue("list the times before a mine reset reminder messages are sent");
		getConfig().get().getNode("4 - Command Usages", "listReminderExtendedDescription").setValue("list the times before a mine reset reminder messages are sent");

		getConfig().get().getNode("4 - Command Usages", "addRemindTime").setValue("Usage /mine addremindtime [seconds]");
		getConfig().get().getNode("4 - Command Usages", "addRemindTimeDescription").setValue("Add a time (in seconds) to send a reset reminder message before a mine reset");
		getConfig().get().getNode("4 - Command Usages", "addRemindTimeExtendedDescription").setValue("Add a time (in seconds) to send a reset reminder message before a mine reset");

		getConfig().get().getNode("4 - Command Usages", "removeRemindTime").setValue("Usage /mine removeremindtime [seconds]");
		getConfig().get().getNode("4 - Command Usages", "removeRemindTimeDescription").setValue("Remove a time (in seconds) to send a reset reminder message before a mine reset");
		getConfig().get().getNode("4 - Command Usages", "removeRemindTimeExtendedDescription").setValue("Remove a time (in seconds) to send a reset reminder message before a mine reset");

		getConfig().get().getNode("4 - Command Usages", "mineSetupSmartFill").setValue("Usage /mine setupsmartfill [mine] [true|false(enabled)] [radius] [true|false(only air)]");
		getConfig().get().getNode("4 - Command Usages", "mineSetupSmartFillDescription").setValue("Changes the smart fill settings for the listed mine to the listed settings");
		getConfig().get().getNode("4 - Command Usages", "mineSetupSmartFillExtendedDescription").setValue("Changes the smart fill settings for the listed mine to the listed settings");

		getConfig().get().getNode("4 - Command Usages", "addAirBlock").setValue("Usage /mine addairblock");
		getConfig().get().getNode("4 - Command Usages", "addAirBlockDescription").setValue("Adds the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings");
		getConfig().get().getNode("4 - Command Usages", "addAirBlockExtendedDescription").setValue("Adds the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings");

		getConfig().get().getNode("4 - Command Usages", "removeAirBlock").setValue("Usage /mine removeairblock");
		getConfig().get().getNode("4 - Command Usages", "removeAirBlockDescription").setValue("Removes the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings");
		getConfig().get().getNode("4 - Command Usages", "removeAirBlockExtendedDescription").setValue("Removes the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings");

		getConfig().get().getNode("4 - Command Usages", "updateChatSettings").setValue("Usage /mine updatechatsettings [FillingText|ReminderText(type)] [setting]");
		getConfig().get().getNode("4 - Command Usages", "updateChatSettingsDescription").setValue("Changes the way messages of the listed type are sent to players");
		getConfig().get().getNode("4 - Command Usages", "updateChatSettingsExtendedDescription").setValue("Changes the way messages of the listed type are sent to players");

		getConfig().get().getNode("4 - Command Usages", "updateSignFillPercentage").setValue("Usage /mine updateSignFillPercentage [Percentage]");
		getConfig().get().getNode("4 - Command Usages", "updateSignFillPercentageDescription").setValue("Changes the percentage of blocks that are not air before the mine fill signs function");
		getConfig().get().getNode("4 - Command Usages", "updateSignFillPercentageExtendedDescription").setValue("Changes the percentage of blocks that are not air before the mine fill signs function");

		getConfig().get().getNode("4 - Command Usages", "toggleOptOutOfMessages").setValue("Usage /mine toggleMessageSettings");
		getConfig().get().getNode("4 - Command Usages", "toggleOptOutOfMessagesDescription").setValue("Toggles your settings between receiving and blocking mine fill and reminder messages");
		getConfig().get().getNode("4 - Command Usages", "toggleOptOutOfMessagesExtendedDescription").setValue("Toggles your settings between receiving and blocking mine fill and reminder messages");


		// Player Messages
		getConfig().get().getNode("5 - Player Messages", "resettingNowSingular").setValue("%mine% is resetting now");
		getConfig().get().getNode("5 - Player Messages", "resettingNowDefined").setValue("%mine% is being filled with %block%");
		getConfig().get().getNode("5 - Player Messages", "resettingNowClear").setValue("%mine% is being cleared");
		getConfig().get().getNode("5 - Player Messages", "resettingNowPlural").setValue("%mines% are resetting now");
		getConfig().get().getNode("5 - Player Messages", "willResetIn").setValue("%mines% will reset in: %time%");
		getConfig().get().getNode("5 - Player Messages", "insideFillingMine").setValue("&9The mine you were in has just been reset so you have been teleported to spawn.");
		getConfig().get().getNode("5 - Player Messages", "signPercentageFillError").setValue("&9The mine is currently too full to fill in this way. The mine is currently %percentage%% full");


		// Time units
		getConfig().get().getNode("6 - Time Units", "second").setValue("second");
		getConfig().get().getNode("6 - Time Units", "seconds").setValue("seconds");
		getConfig().get().getNode("6 - Time Units", "minute").setValue("minute");
		getConfig().get().getNode("6 - Time Units", "minutes").setValue("minutes");
		getConfig().get().getNode("6 - Time Units", "hour").setValue("hour");
		getConfig().get().getNode("6 - Time Units", "hours").setValue("hours");
	}



	public void refreshData() {
		// General
		minePrefix = getConfig().get().getNode("1 - General" , "minePrefix").getString();
		noPlayerOnline = getConfig().get().getNode("1 - General" , "noPlayerOnline").getString();
		mineFillTimeTaken = getConfig().get().getNode("1 - General" , "mineFillTimeTaken").getString();


		// Command Messages
		// Command Messages
		playerOnlyCommand = getConfig().get().getNode("2 - Command Messages", "playerOnlyCommand").getString();
		configSaved = getConfig().get().getNode("2 - Command Messages", "configSaved").getString();
		configReloaded = getConfig().get().getNode("2 - Command Messages", "configReloaded").getString();
		mineCleared = getConfig().get().getNode("2 - Command Messages", "mineCleared").getString();
		resetTime = getConfig().get().getNode("2 - Command Messages", "resetTime").getString();
		mineDoesNotExist = getConfig().get().getNode("2 - Command Messages", "mineDoesNotExist").getString();
		mineGroupDoesNotExist = getConfig().get().getNode("2 - Command Messages", "mineGroupDoesNotExist").getString();
		noMines = getConfig().get().getNode("2 - Command Messages", "noMines").getString();
		noMineGroups = getConfig().get().getNode("2 - Command Messages", "noMineGroups").getString();
		mineAlreadyExists = getConfig().get().getNode("2 - Command Messages", "mineAlreadyExists").getString();
		mineGroupAlreadyExists = getConfig().get().getNode("2 - Command Messages", "mineGroupAlreadyExists").getString();
		definedNewMine = getConfig().get().getNode("2 - Command Messages", "definedNewMine").getString();
		definedNewMineGroup = getConfig().get().getNode("2 - Command Messages", "definedNewMineGroup").getString();
		updateMineGroup = getConfig().get().getNode("2 - Command Messages", "updateMineGroup").getString();
		redefinedMine = getConfig().get().getNode("2 - Command Messages", "redefinedMine").getString();
		resetTimeTooShort = getConfig().get().getNode("2 - Command Messages", "resetTimeTooShort").getString();
		delayTooShort = getConfig().get().getNode("2 - Command Messages", "delayTooShort").getString();
		addedOre = getConfig().get().getNode("2 - Command Messages", "addedOre").getString();
		orePercentageError = getConfig().get().getNode("2 - Command Messages", "orePercentageError").getString();
		mineFilledBlock = getConfig().get().getNode("2 - Command Messages", "mineFilledBlock").getString();
		deletedMine = getConfig().get().getNode("2 - Command Messages", "deletedMine").getString();
		mineGroupDeleted = getConfig().get().getNode("2 - Command Messages", "mineGroupDeleted").getString();
		deleteGroupFailContents = getConfig().get().getNode("2 - Command Messages", "deleteGroupFailContents").getString();
		percentageTooSmall = getConfig().get().getNode("2 - Command Messages", "percentageTooSmall").getString();
		percentageTooSmallUpdate = getConfig().get().getNode("2 - Command Messages", "percentageTooSmallUpdate").getString();
		oreRemoved = getConfig().get().getNode("2 - Command Messages", "oreRemoved").getString();
		oreAlreadyInMine = getConfig().get().getNode("2 - Command Messages", "oreAlreadyInMine").getString();
		oreNotInMine = getConfig().get().getNode("2 - Command Messages", "oreNotInMine").getString();
		unableToAddFallback = getConfig().get().getNode("2 - Command Messages", "unableToAddFallback").getString();
		unableToEditFallback = getConfig().get().getNode("2 - Command Messages", "unableToEditFallback").getString();
		oreSamePercentageError = getConfig().get().getNode("2 - Command Messages", "oreSamePercentageError").getString();
		changedFallback = getConfig().get().getNode("2 - Command Messages", "changedFallback").getString();
		sameFallback = getConfig().get().getNode("2 - Command Messages", "sameFallback").getString();
		remindList = getConfig().get().getNode("2 - Command Messages", "remindList").getString();
		remindTimeExist = getConfig().get().getNode("2 - Command Messages", "remindTimeExist").getString();
		remindTimeNotExist = getConfig().get().getNode("2 - Command Messages", "remindTimeNotExist").getString();
		remindTimeAdd = getConfig().get().getNode("2 - Command Messages", "remindTimeAdd").getString();
		remindTimeRemove = getConfig().get().getNode("2 - Command Messages", "remindTimeRemove").getString();
		airBlockExist = getConfig().get().getNode("2 - Command Messages", "airBlockExist").getString();
		airBlockNotExist = getConfig().get().getNode("2 - Command Messages", "airBlockNotExist").getString();
		airBlockAdd = getConfig().get().getNode("2 - Command Messages", "airBlockAdd").getString();
		airBlockRemove = getConfig().get().getNode("2 - Command Messages", "airBlockRemove").getString();
		addSpawnPoint = getConfig().get().getNode("2 - Command Messages", "addSpawnPoint").getString();
		noRemoveDefaultSpawn = getConfig().get().getNode("2 - Command Messages", "noRemoveDefaultSpawn").getString();
		removeSpawnPoint = getConfig().get().getNode("2 - Command Messages", "removeSpawnPoint").getString();
		updatedSpawnPoint = getConfig().get().getNode("2 - Command Messages", "updatedSpawnPoint").getString();
		spawnPointNotExist = getConfig().get().getNode("2 - Command Messages", "spawnPointNotExist").getString();
		maxSpawnCount = getConfig().get().getNode("2 - Command Messages", "maxSpawnCount").getString();
		spawnPointAlreadyInUse = getConfig().get().getNode("2 - Command Messages", "spawnPointAlreadyInUse").getString();
		mineSpawnChanged = getConfig().get().getNode("2 - Command Messages", "mineSpawnChanged").getString();
		chatSettingChanged = getConfig().get().getNode("2 - Command Messages", "chatSettingChanged").getString();
		invalidChatSetting = getConfig().get().getNode("2 - Command Messages", "invalidChatSetting").getString();
		chatSettingAlreadySet = getConfig().get().getNode("2 - Command Messages", "chatSettingAlreadySet").getString();
		invalidChatType = getConfig().get().getNode("2 - Command Messages", "invalidChatType").getString();
		mineFillSignPercentageUpdated = getConfig().get().getNode("2 - Command Messages", "mineFillSignPercentageUpdated").getString();
		mineFillSignPercentageInvalid = getConfig().get().getNode("2 - Command Messages", "mineFillSignPercentageInvalid").getString();
		noDirectionToSpawn = getConfig().get().getNode("2 - Command Messages", "noDirectionToSpawn").getString();
		settingSetMessageOptOut = getConfig().get().getNode("2 - Command Messages", "settingSetMessageOptOut").getString();
		settingSetMessageOptIn = getConfig().get().getNode("2 - Command Messages", "settingSetMessageOptIn").getString();


		// info command strings
		groupInfoHeader = getConfig().get().getNode("3 - Info Command Strings", "groupInfoHeader").getString();
		mineInfoHeader = getConfig().get().getNode("3 - Info Command Strings", "mineInfoHeader").getString();
		spawnInfoHeader = getConfig().get().getNode("3 - Info Command Strings", "spawnInfoHeader").getString();
		infoFooter = getConfig().get().getNode("3 - Info Command Strings", "infoFooter").getString();


		// Command Usages
		help = getConfig().get().getNode("4 - Command Usages", "help").getString();
		helpDescription = getConfig().get().getNode("4 - Command Usages", "helpDescription").getString();
		helpExtendedDescription = getConfig().get().getNode("4 - Command Usages", "helpExtendedDescription").getString();

		reload = getConfig().get().getNode("4 - Command Usages", "reload").getString();
		reloadDescription = getConfig().get().getNode("4 - Command Usages", "reloadDescription").getString();
		reloadExtendedDescription = getConfig().get().getNode("4 - Command Usages", "reloadExtendedDescription").getString();

		save = getConfig().get().getNode("4 - Command Usages", "save").getString();
		saveDescription = getConfig().get().getNode("4 - Command Usages", "saveDescription").getString();
		saveExtendedDescription = getConfig().get().getNode("4 - Command Usages", "saveExtendedDescription").getString();

		addSpawn = getConfig().get().getNode("4 - Command Usages", "addSpawn").getString();
		addSpawnDescription = getConfig().get().getNode("4 - Command Usages", "addSpawnDescription").getString();
		addSpawnExtendedDescription = getConfig().get().getNode("4 - Command Usages", "addSpawnExtendedDescription").getString();

		removeSpawn = getConfig().get().getNode("4 - Command Usages", "removeSpawn").getString();
		removeSpawnDescription = getConfig().get().getNode("4 - Command Usages", "removeSpawnDescription").getString();
		removeSpawnExtendedDescription = getConfig().get().getNode("4 - Command Usages", "removeSpawnExtendedDescription").getString();

		updateSpawn = getConfig().get().getNode("4 - Command Usages", "updateSpawn").getString();
		updateSpawnDescription = getConfig().get().getNode("4 - Command Usages", "updateSpawnDescription").getString();
		updateSpawnExtendedDescription = getConfig().get().getNode("4 - Command Usages", "updateSpawnExtendedDescription").getString();

		changeSpawn = getConfig().get().getNode("4 - Command Usages", "changeSpawn").getString();
		changeSpawnDescription = getConfig().get().getNode("4 - Command Usages", "changeSpawnDescription").getString();
		changeSpawnExtendedDescription = getConfig().get().getNode("4 - Command Usages", "changeSpawnExtendedDescription").getString();

		time = getConfig().get().getNode("4 - Command Usages", "time").getString();
		timeDescription = getConfig().get().getNode("4 - Command Usages", "timeDescription").getString();
		timeExtendedDescription = getConfig().get().getNode("4 - Command Usages", "timeExtendedDescription").getString();

		clear = getConfig().get().getNode("4 - Command Usages", "clear").getString();
		clearDescription = getConfig().get().getNode("4 - Command Usages", "clearDescription").getString();
		clearExtendedDescription = getConfig().get().getNode("4 - Command Usages", "clearExtendedDescription").getString();

		fill = getConfig().get().getNode("4 - Command Usages", "fill").getString();
		fillDescription = getConfig().get().getNode("4 - Command Usages", "fillDescription").getString();
		fillExtendedDescription = getConfig().get().getNode("4 - Command Usages", "fillExtendedDescription").getString();

		fillblock = getConfig().get().getNode("4 - Command Usages", "fillblock").getString();
		fillblockDescription = getConfig().get().getNode("4 - Command Usages", "fillblockDescription").getString();
		fillblockExtendedDescription = getConfig().get().getNode("4 - Command Usages", "fillblockExtendedDescription").getString();

		defineGroup = getConfig().get().getNode("4 - Command Usages", "defineGroup").getString();
		defineGroupDescription = getConfig().get().getNode("4 - Command Usages", "defineGroupDescription").getString();
		defineGroupExtendedDescription = getConfig().get().getNode("4 - Command Usages", "defineGroupExtendedDescription").getString();

		updateGroup = getConfig().get().getNode("4 - Command Usages", "updateGroup").getString();
		updateGroupDescription = getConfig().get().getNode("4 - Command Usages", "updateGroupDescription").getString();
		updateGroupExtendedDescription = getConfig().get().getNode("4 - Command Usages", "updateGroupExtendedDescription").getString();

		deleteGroup = getConfig().get().getNode("4 - Command Usages", "deleteGroup").getString();
		deleteGroupDescription = getConfig().get().getNode("4 - Command Usages", "deleteGroupDescription").getString();
		deleteGroupExtendedDescription = getConfig().get().getNode("4 - Command Usages", "deleteGroupExtendedDescription").getString();

		defineMine = getConfig().get().getNode("4 - Command Usages", "defineMine").getString();
		defineMineDescription = getConfig().get().getNode("4 - Command Usages", "defineMineDescription").getString();
		defineMineExtendedDescription = getConfig().get().getNode("4 - Command Usages", "defineMineExtendedDescription").getString();

		redefineMine = getConfig().get().getNode("4 - Command Usages", "redefineMine").getString();
		redefineMineDescription = getConfig().get().getNode("4 - Command Usages", "redefineMineDescription").getString();
		redefineMineExtendedDescription = getConfig().get().getNode("4 - Command Usages", "redefineMineExtendedDescription").getString();

		deleteMine = getConfig().get().getNode("4 - Command Usages", "deleteMine").getString();
		deleteMineDescription = getConfig().get().getNode("4 - Command Usages", "deleteMineDescription").getString();
		deleteMineExtendedDescription = getConfig().get().getNode("4 - Command Usages", "deleteMineExtendedDescription").getString();

		list = getConfig().get().getNode("4 - Command Usages", "list").getString();
		listDescription = getConfig().get().getNode("4 - Command Usages", "listDescription").getString();
		listExtendedDescription = getConfig().get().getNode("4 - Command Usages", "listExtendedDescription").getString();

		info = getConfig().get().getNode("4 - Command Usages", "info").getString();
		infoDescription = getConfig().get().getNode("4 - Command Usages", "infoDescription").getString();
		infoExtendedDescription = getConfig().get().getNode("4 - Command Usages", "infoExtendedDescription").getString();

		addore = getConfig().get().getNode("4 - Command Usages", "addore").getString();
		addoreDescription = getConfig().get().getNode("4 - Command Usages", "addoreDescription").getString();
		addoreExtendedDescription = getConfig().get().getNode("4 - Command Usages", "addoreExtendedDescription").getString();

		updateore = getConfig().get().getNode("4 - Command Usages", "updateore").getString();
		updateoreDescription = getConfig().get().getNode("4 - Command Usages", "updateoreDescription").getString();
		updateoreExtendedDescription = getConfig().get().getNode("4 - Command Usages", "updateoreExtendedDescription").getString();

		updatefallback = getConfig().get().getNode("4 - Command Usages", "updatefallback").getString();
		updatefallbackDescription = getConfig().get().getNode("4 - Command Usages", "updatefallbackDescription").getString();
		updatefallbackExtendedDescription = getConfig().get().getNode("4 - Command Usages", "updatefallbackExtendedDescription").getString();

		listReminder = getConfig().get().getNode("4 - Command Usages", "listReminder").getString();
		listReminderDescription = getConfig().get().getNode("4 - Command Usages", "listReminderDescription").getString();
		listReminderExtendedDescription = getConfig().get().getNode("4 - Command Usages", "listReminderExtendedDescription").getString();

		addRemindTime = getConfig().get().getNode("4 - Command Usages", "addRemindTime").getString();
		addRemindTimeDescription = getConfig().get().getNode("4 - Command Usages", "addRemindTimeDescription").getString();
		addRemindTimeExtendedDescription = getConfig().get().getNode("4 - Command Usages", "addRemindTimeExtendedDescription").getString();

		removeRemindTime = getConfig().get().getNode("4 - Command Usages", "removeRemindTime").getString();
		removeRemindTimeDescription = getConfig().get().getNode("4 - Command Usages", "removeRemindTimeDescription").getString();
		removeRemindTimeExtendedDescription = getConfig().get().getNode("4 - Command Usages", "removeRemindTimeExtendedDescription").getString();

		mineSetupSmartFill = getConfig().get().getNode("4 - Command Usages", "mineSetupSmartFill").getString();
		mineSetupSmartFillDescription = getConfig().get().getNode("4 - Command Usages", "mineSetupSmartFillDescription").getString();
		mineSetupSmartFillExtendedDescription = getConfig().get().getNode("4 - Command Usages", "mineSetupSmartFillExtendedDescription").getString();

		addAirBlock = getConfig().get().getNode("4 - Command Usages", "addAirBlock").getString();
		addAirBlockDescription = getConfig().get().getNode("4 - Command Usages", "addAirBlockDescription").getString();
		addAirBlockExtendedDescription = getConfig().get().getNode("4 - Command Usages", "addAirBlockExtendedDescription").getString();

		removeAirBlock = getConfig().get().getNode("4 - Command Usages", "removeAirBlock").getString();
		removeAirBlockDescription = getConfig().get().getNode("4 - Command Usages", "removeAirBlockDescription").getString();
		removeAirBlockExtendedDescription = getConfig().get().getNode("4 - Command Usages", "removeAirBlockExtendedDescription").getString();

		updateChatSettings = getConfig().get().getNode("4 - Command Usages", "updateChatSettings").getString();
		updateChatSettingsDescription = getConfig().get().getNode("4 - Command Usages", "updateChatSettingsDescription").getString();
		updateChatSettingsExtendedDescription = getConfig().get().getNode("4 - Command Usages", "updateChatSettingsExtendedDescription").getString();

		updateSignFillPercentage = getConfig().get().getNode("4 - Command Usages", "updateSignFillPercentage").getString();
		updateSignFillPercentageDescription = getConfig().get().getNode("4 - Command Usages", "updateSignFillPercentageDescription").getString();
		updateSignFillPercentageExtendedDescription = getConfig().get().getNode("4 - Command Usages", "updateSignFillPercentageExtendedDescription").getString();

		toggleOptOutOfMessages = getConfig().get().getNode("4 - Command Usages", "toggleOptOutOfMessages").getString();
		toggleOptOutOfMessagesDescription = getConfig().get().getNode("4 - Command Usages", "toggleOptOutOfMessagesDescription").getString();
		toggleOptOutOfMessagesExtendedDescription = getConfig().get().getNode("4 - Command Usages", "toggleOptOutOfMessagesExtendedDescription").getString();


		// Player Messages
		resettingNowSingular = getConfig().get().getNode("5 - Player Messages", "resettingNowSingular").getString();
		resettingNowDefined = getConfig().get().getNode("5 - Player Messages", "resettingNowDefined").getString();
		resettingNowClear = getConfig().get().getNode("5 - Player Messages", "resettingNowClear").getString();
		resettingNowPlural = getConfig().get().getNode("5 - Player Messages", "resettingNowPlural").getString();
		willResetIn = getConfig().get().getNode("5 - Player Messages", "willResetIn").getString();
		insideFillingMine = getConfig().get().getNode("5 - Player Messages", "insideFillingMine").getString();
		signPercentageFillError = getConfig().get().getNode("5 - Player Messages", "signPercentageFillError").getString();


		// Time units

		// When loading check config isn't an old version that dosn't have time units (and add time units if required)
		if (getConfig().get().getNode("6 - Time Units").getChildrenMap().isEmpty()) {
			getConfig().get().getNode("6 - Time Units", "second").setValue("second");
			getConfig().get().getNode("6 - Time Units", "seconds").setValue("seconds");
			getConfig().get().getNode("6 - Time Units", "minute").setValue("minute");
			getConfig().get().getNode("6 - Time Units", "minutes").setValue("minutes");
			getConfig().get().getNode("6 - Time Units", "hour").setValue("hour");
			getConfig().get().getNode("6 - Time Units", "hours").setValue("hours");
			getConfig().save();
		}

		secondUnitSingular = getConfig().get().getNode("6 - Time Units", "second").getString();
		secondUnitPlural = getConfig().get().getNode("6 - Time Units", "seconds").getString();
		minuteUnitSingular = getConfig().get().getNode("6 - Time Units", "minute").getString();
		minuteUnitPlural = getConfig().get().getNode("6 - Time Units", "minutes").getString();
		hourUnitSingular = getConfig().get().getNode("6 - Time Units", "hour").getString();
		hourUnitPlural = getConfig().get().getNode("6 - Time Units", "hours").getString();
	}
}
