package com.github.jamies1211.minereset;

/**
 * Created by Jamie on 31-May-16.
 */
public class Messages {
	// General
	public static String minePrefix = "&9&l[Mines]&r &e";
	public static String noPlayerOnline = "mine re-filling disabled as no player is online";
	public static String mineFillTimeTaken = "%mine% was filled in %totalTime% milliseconds with %asyncTime% milliseconds doing async tasks and with %syncTime% milliseconds doing synchronised tasks. Volume: %volume%. Changed blocks: %changedBlocks%";

	// Config
	public static final String newConfigFile = "Created new configuration file as none detected!";
	public static final String loadedConfigFile = "Detected configuration file and loaded";
	public static final String configLoadError = "The default configuration could not be loaded or created!";

	// Technical
	public static final String minecraftAirString = "minecraft:air";
	public static final String minecraftStoneString = "minecraft:stone[variant=stone]";

	// Errors
	public static final String teleportRotationError = "&c&l[Mines] ERROR: Teleport function direction not valid so unused. \"North\", \"South\", \"East\" or \"West\" expected.";
	public static final String resetTimeTooShortError = "&c&l[Mines] ERROR: Reset time was read from config at less than 60 seconds. It has been changed to 60";
	public static final String invalidRemindChatType = "Your config reminder chat type has is an invalid number. Please refer to the documentation";
	public static final String invalidFillChatType = "Your config fill chat type has is an invalid number. Please refer to the documentation";
	public static final String worldNotFound = "&c&l[Mines] ERROR The world that mine:%mine% exists in cannot be found";
	public static final String worldNotFoundFillError = "&c&l[Mines] ERROR Filling mine cannot occur as the world cannot be found for mine: %mine%";
	public static final String blockPlaceError = "&c&l[Mines] ERROR %errors%errors occurred when filling the mine. Check the mine config and make sure all blocks are on the server";

	// Command Messages
	public static String playerOnlyCommand = "This command must be run by a player";
	public static String configSaved = "The config file has been saved";
	public static String configReloaded = "The config file has been reloaded";
	public static String mineCleared = "%mine% has been cleared";
	public static String resetTime = "%mine% will reset in: %time%";
	public static String mineDoesNotExist = "%mine% does not exist";
	public static String mineGroupDoesNotExist = "%group% is not a mine group";
	public static String noMines = "You have no mines";
	public static String noMineGroups = "You have no mine groups";
	public static String mineAlreadyExists = "%mine% is the name of already existing mine";
	public static String mineGroupAlreadyExists = "%group% is the name of already existing mine group";
	public static String definedNewMine = "You have defined a new mine with the name: %mine%";
	public static String definedNewMineGroup = "You have defined a new mine group with the name: %group%";
	public static String updateMineGroup = "You have updated the mine group with the name: %group%";
	public static String redefinedMine = "You have redefined the mine to the defined information for mine: %mine%";
	public static String resetTimeTooShort = "Your reset time for a new group must be 1 minute or more";
	public static String delayTooShort = "Your delay can't be less than 0";
	public static String addedOre = "You have added: %block% to mine: %mine% at %percentage%%";
	public static String orePercentageError = "The percentage you have listed would increase the mine beyond 100%";
	public static String mineFilledBlock = "%mine% has been filled with: %block%";
	public static String deletedMine = "You have deleted your mine with the name: %mine%";
	public static String mineGroupDeleted = "%group% has been deleted";
	public static String deleteGroupFailContents = "%group% can't be deleted as it has contents and this command was not forced";
	public static String percentageTooSmall = "The percentage value you entered is too small. Percentage must be greater than 0";
	public static String percentageTooSmallUpdate = "The percentage value you entered is too small. Percentage must be greater than 0. A value of 0 will remove the ore from the mine";
	public static String oreRemoved = "%block% has been removed as you entered 0%";
	public static String oreAlreadyInMine = "%block% is already in the listed mine so wasn't added. You can change the percentage of the block using the \"/mine updateore\" command";
	public static String oreNotInMine = "%block% is not in the listed mine so can't be edited";
	public static String unableToAddFallback = "%block% is the default block so you cannot add it to the mine again. It is used to top the mine up to 100% full after all the other ores are taken into account";
	public static String unableToEditFallback = "%block% is the default block so you cannot edit its percentage or remove it. It is used to top the mine up to 100% full after all the other ores are taken into account";
	public static String oreSamePercentageError = "No changes were made because the new percentage is the same as the old percentage for %block% in %mine%";
	public static String changedFallback = "You have changed the fallback block to: %block%";
	public static String sameFallback = "No changes were made because this is already the fallback block";
	public static String remindList = "list of seconds before mine reset a reminder message is issued: %list%";
	public static String remindTimeExist = "That time is already on the list of times to send a reset reminder so was not added";
	public static String remindTimeNotExist = "That time is not on the list of times to send a reset reminder so was not removed";
	public static String remindTimeAdd = "You have added %time% (%readableTime%) to the list of times a message will be sent before a mine reset reminding of a mine reset";
	public static String remindTimeRemove = "You have removed %time% (%readableTime%) from the list of times a message will be sent before a mine reset reminding of a mine reset";
	public static String airBlockExist = "That time is already on the list of times to send a reset reminder so was not added";
	public static String airBlockNotExist = "That block is not on the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static String airBlockAdd = "You have added %block% to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static String airBlockRemove = "You have removed %block% from the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static String addSpawnPoint = "You have added a new spawn point for the current world. SpawnPoint: %spawnName% X:%x% Y:%y% Z:%z% Facing:%facing% WorldUUID:%worldUUIDString%";
	public static String noRemoveDefaultSpawn = "You cannot remove the default spawn point";
	public static String removeSpawnPoint = "You have removed spawn point: Spawn%spawnValue%";
	public static String updatedSpawnPoint = "You have updated the spawn point to X:%x% Y:%y% Z:%z% Facing:%facing% WorldUUID:%worldUUIDString%";
	public static String spawnPointNotExist = "The listed spawn point does not exist";
	public static String maxSpawnCount = "You have reached the maximum number of allowed spawns";
	public static String spawnPointAlreadyInUse = "%mine% already uses that spawn point";
	public static String mineSpawnChanged = "%mine% has been changed to now use Spawn%spawnValue%";
	public static String chatSettingChanged = "You have successfully changed the chat setting for %type% to %option%";
	public static String invalidChatSetting = "The chat setting you listed is invalid. Please refer to documentation";
	public static String chatSettingAlreadySet = "The chat settings for %type% is already %option%";
	public static String invalidChatType = "That is not a valid chat type. Options (FillingText|ReminderText)";
	public static String mineFillSignPercentageUpdated = "You have updated the percentage of blocks that need to exist for mine fill signs to function to %percentage%";
	public static String mineFillSignPercentageInvalid = "The value you entered was invalid. Please enter a value between 0 and 100";
	public static String noDirectionToSpawn = "direction not entered correctly so defaulting to North";
	public static String settingSetMessageOptOut = "You have successfully changed your message settings to block mine fill and remind messages";
	public static String settingSetMessageOptIn = "You have successfully changed your message settings to receive mine fill and remind messages";

	// info command strings
	public static String groupInfoHeader = "----------------  Group info  ----------------";
	public static String mineInfoHeader = "----------------  Mine info  ----------------";
	public static String spawnInfoHeader = "----------------  Spawn info  ----------------";
	public static String infoFooter = "---------------------------------------------";

	// Command Usages
	public static String help = "/mine help";
	public static String helpDescription = "Shows mine help";
	public static String helpExtendedDescription = "Use this command to list all the mine sub commands user has access to and how to use them";

	public static String reload = "/mine reload";
	public static String reloadDescription = "Reloads mine config file";
	public static String reloadExtendedDescription = "Reloads mine config file";

	public static String save = "/mine save";
	public static String saveDescription = "Use this command to save mine config";
	public static String saveExtendedDescription = "Saves the mine config being used over one in folder";

	public static String addSpawn = "Usage /mine addspawn [x] [y] [z] [facing]";
	public static String addSpawnDescription = "Adds a new spawn point with the values listed";
	public static String addSpawnExtendedDescription = "Adds a new spawn point with the values listed";

	public static String removeSpawn = "Usage /mine removespawn [spawn point]";
	public static String removeSpawnDescription = "Removes the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn";
	public static String removeSpawnExtendedDescription = "Removes the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn)";

	public static String updateSpawn = "Usage /mine updatespawn [spawn point] [x] [y] [z] [facing]";
	public static String updateSpawnDescription = "Updates the spawn information to the listed values (To alter default spawn use \"Default\" other wise use the number of spawn";
	public static String updateSpawnExtendedDescription = "Updates the spawn information to the listed values (To alter default spawn use \"Default\" other wise use the number of spawn)";

	public static String changeSpawn = "Usage /mine changespawn [mine] [spawn point]";
	public static String changeSpawnDescription = "Set the listed mine to use the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn";
	public static String changeSpawnExtendedDescription = "Set the listed mine to use the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn)";

	public static String time = "/mine time [mine]";
	public static String timeDescription = "Check time remaining before mine resets";
	public static String timeExtendedDescription = "Check time remaining before mine resets";

	public static String clear = "/mine clear [mine]";
	public static String clearDescription = "clear all blocks from a  mine";
	public static String clearExtendedDescription = "clear all block from a mine";

	public static String fill = "/mine fill [mine]";
	public static String fillDescription = "fill a mine according to its blocks and percentages";
	public static String fillExtendedDescription = "fill a mine according to its blocks and percentages";

	public static String fillblock = "/mine fillblock [mine]";
	public static String fillblockDescription = "fill a mine with the block you are currently standing on";
	public static String fillblockExtendedDescription = "fill a mine with the block you are currently standing on";

	public static String defineGroup = "/mine definegroup [group name] [reset time] [initial delay]";
	public static String defineGroupDescription = "Create a new mine group";
	public static String defineGroupExtendedDescription = "Create a new mine group with name, reset time and initial delay as listed";

	public static String updateGroup = "/mine updategroup [group name] [reset time] [initial delay]";
	public static String updateGroupDescription = "Updates an existing mine group";
	public static String updateGroupExtendedDescription = "Updates an existing mine group with name, reset time and initial delay as listed";

	public static String deleteGroup = "/mine deletegroup [group name] [safe|force]";
	public static String deleteGroupDescription = "Deletes an existing mine group";
	public static String deleteGroupExtendedDescription = "Deletes an existing mine group. If safe then deletion will fail if mine group has contents";

	public static String defineMine = "/mine definemine [group] [mine name] [pos1x] [pos1y] [pos1z] [pos2x] [pos2y] [pos2z]";
	public static String defineMineDescription = "Create a new mine";
	public static String defineMineExtendedDescription = "Create a new mine in listed group with name and positions as listed";

	public static String redefineMine = "/mine redefinemine [group] [mine name] [pos1x] [pos1y] [pos1z] [pos2x] [pos2y] [pos2z]";
	public static String redefineMineDescription = "Redefine a mine to change its settings";
	public static String redefineMineExtendedDescription = "Redefine a mine to change its settings to listed group with name and positions as listed";

	public static String deleteMine = "/mine deletemine [mine name]";
	public static String deleteMineDescription = "Delete a mine";
	public static String deleteMineExtendedDescription = "Delete the mine listed";

	public static String list = "Usage /mine list [groups|mines|spawns]";
	public static String listDescription = "list all groups or mines that exist";
	public static String listExtendedDescription = "list all groups or mines that exist";

	public static String info = "Usage /mine info [group|mine|spawn] [name]";
	public static String infoDescription = "Get all info on group or mine";
	public static String infoExtendedDescription = "Get all info on group or mine";

	public static String addore = "Usage /mine addore [mine] [percentage]";
	public static String addoreDescription = "Adds the block you are standing on to listed mine at listed percentage";
	public static String addoreExtendedDescription = "Adds the block you are standing on to listed mine at listed percentage";

	public static String updateore = "Usage /mine updateore [mine] [percentage]";
	public static String updateoreDescription = "Updates the block you are standing on in listed mine to listed percentage";
	public static String updateoreExtendedDescription = "Updates the block you are standing on in listed mine to listed percentage";

	public static String updatefallback = "Usage /mine updatefallback [mine]";
	public static String updatefallbackDescription = "Updates the mine listed to use the block you are standing on as the fallback block";
	public static String updatefallbackExtendedDescription = "Updates the mine listed to use the block you are standing on as the fallback block";

	public static String listReminder = "Usage /mine listreminders";
	public static String listReminderDescription = "list the times before a mine reset reminder messages are sent";
	public static String listReminderExtendedDescription = "list the times before a mine reset reminder messages are sent";

	public static String addRemindTime = "Usage /mine addremindtime [seconds]";
	public static String addRemindTimeDescription = "Add a time (in seconds) to send a reset reminder message before a mine reset";
	public static String addRemindTimeExtendedDescription = "Add a time (in seconds) to send a reset reminder message before a mine reset";

	public static String removeRemindTime = "Usage /mine removeremindtime [seconds]";
	public static String removeRemindTimeDescription = "Remove a time (in seconds) to send a reset reminder message before a mine reset";
	public static String removeRemindTimeExtendedDescription = "Remove a time (in seconds) to send a reset reminder message before a mine reset";

	public static String mineSetupSmartFill = "Usage /mine setupsmartfill [mine] [true|false(enabled)] [radius] [true|false(only air)]";
	public static String mineSetupSmartFillDescription = "Changes the smart fill settings for the listed mine to the listed settings";
	public static String mineSetupSmartFillExtendedDescription = "Changes the smart fill settings for the listed mine to the listed settings";

	public static String addAirBlock = "Usage /mine addairblock";
	public static String addAirBlockDescription = "Adds the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static String addAirBlockExtendedDescription = "Adds the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";

	public static String removeAirBlock = "Usage /mine removeairblock";
	public static String removeAirBlockDescription = "Removes the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static String removeAirBlockExtendedDescription = "Removes the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";

	public static String updateChatSettings = "Usage /mine updatechatsettings [FillingText|ReminderText(type)] [setting]";
	public static String updateChatSettingsDescription = "Changes the way messages of the listed type are sent to players";
	public static String updateChatSettingsExtendedDescription = "Changes the way messages of the listed type are sent to players";

	public static String updateSignFillPercentage = "Usage /mine updateSignFillPercentage [Percentage]";
	public static String updateSignFillPercentageDescription = "Changes the percentage of blocks that are not air before the mine fill signs function";
	public static String updateSignFillPercentageExtendedDescription = "Changes the percentage of blocks that are not air before the mine fill signs function";

	public static String toggleOptOutOfMessages = "Usage /mine toggleMessageSettings";
	public static String toggleOptOutOfMessagesDescription = "Toggles your settings between receiving and blocking mine fill and reminder messages";
	public static String toggleOptOutOfMessagesExtendedDescription = "Toggles your settings between receiving and blocking mine fill and reminder messages";



	// Player Messages
	public static String resettingNowSingular = "%mine% is resetting now";
	public static String resettingNowDefined = "%mine% is being filled with %block%";
	public static String resettingNowClear = "%mine% is being cleared";
	public static String resettingNowPlural ="%mines% are resetting now";
	public static String willResetIn = "%mines% will reset in: %time%";
	public static String insideFillingMine = "&9The mine you were in has just been reset so you have been teleported to spawn.";
	public static String signPercentageFillError = "&9The mine is currently too full to fill in this way. The mine is currently %percentage%% full";

	// Permissions
//	help: "minereset.help"
//	time: "minereset.check.time"
//	reload: "minereset.reload"
//	save: "minereset.save"
//  addSpawn: "minereset.addspawn"
//  removeSpawn: "minereset.removespawn"
//  updateSpawn: "minereset.updatespawn"
//  changeSpawn: "minereset.changespawn"
//	clear: "minereset.clear"
//	fill: "minereset.fill"
//	fillblock: "minereset.fillblock"
//	definegroup: "minereset.define.group"
//  updategroup: "minereset.update.group"
//	deletegroup: "minereset.delete.group"
//	definemine: "minereset.define.mine"
//	redefinemine: "minereset.redefine.mine"
//	deletemine: "minereset.delete.mine"
//	list: "minereset.list"
//	info: "minereset.details"
//	addore: "minereset.mine.addore"
//	updateore: "minereset.mine.updateore"
//  updatefallback: "minereset.mine.updatefallback"
//  listreminders: "minereset.mine.listremindtime"
//  addremindtime: "minereset.mine.addremindtime"
//  removeremindtime: "minereset.mine.removeremindtime"
//  setupsmartfill: "minereset.mine.setup.smartfill"
//  addairblock: "minereset.mine.addairblock"
//  removeairblock: "minereset.mine.removeairblock"
//  updatechatsettings: "minereset.update.chatsettings"
// updatesignfillpercentage: "minereset.update.signfillpercentage"
// toggleoptoutmessage: "minereset.player.togglemessagesettings"
}
