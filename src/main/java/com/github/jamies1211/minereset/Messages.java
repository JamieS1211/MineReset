package com.github.jamies1211.minereset;

/**
 * Created by Jamie on 31-May-16.
 */
public class Messages {
	/** General */
	public static final String MinePrefix = "&9&l[Mines]&r &e";
	public static final String NoPlayerOnline = "mine re-filling disabled as no player is online";
	public static final String MineFillTimeTaken = "%mine% was filled in %totalTime% milliseconds with %asyncTime% milliseconds doing async tasks and with %syncTime% milliseconds doing synchronised tasks. Volume: %volume%. Changed blocks: %changedBlocks%";

	/** Errors */
	public static final String TeleportRotationError = "&c&l[Mines] ERROR: Teleport function direction not valid so unused. \"North\", \"South\", \"East\" or \"West\" expected.";
	public static final String ResetTimeTooShortError = "&c&l[Mines] ERROR: Reset time was read from config at less than 60 seconds. It has been changed to 60";
	public static final String InvalidRemindChatType = "Your config reminder chat type has is an invalid number. Please refer to the documentation";
	public static final String InvalidFillChatType = "Your config fill chat type has is an invalid number. Please refer to the documentation";
	public static final String WorldNotFound = "&c&l[Mines] ERROR Filling mine cannot occur as the world cannot be found for mine:";
	public static final String BlockPlaceError = "&c&l[Mines] ERROR %errors%errors occurred when filling the mine. Check the mine config and make sure all blocks are on the server";

	/** Command Messages */
	public static final String PlayerOnlyCommand = "This command must be run by a player";
	public static final String ConfigSaved = "The config file has been saved";
	public static final String ConfigReloaded = "The config file has been reloaded";
	public static final String MineCleared = "%mine% has been cleared";
	public static final String ResetTime = "%mine% will reset in: %time%";
	public static final String MineDoesNotExist = "%mine% does not exist";
	public static final String MineGroupDoesNotExist = "%group% is not a mine group";
	public static final String NoMines = "You have no mines";
	public static final String NoMineGroups = "You have no mine groups";
	public static final String MineAlreadyExists = "%mine% is the name of already existing mine";
	public static final String MineGroupAlreadyExists = "%group% is the name of already existing mine group";
	public static final String DefinedNewMine = "You have defined a new mine with the name: %mine%";
	public static final String DefinedNewMineGroup = "You have defined a new mine group with the name: %group%";
	public static final String UpdateMineGroup = "You have updated the mine group with the name: %group%";
	public static final String RedefinedMine = "You have redefined the mine to the defined information for mine: %mine%";
	public static final String ResetTimeTooShort = "Your reset time for a new group must be 1 minute or more";
	public static final String DelayTooShort = "Your delay can't be less than 0";
	public static final String AddedOre = "You have added: %block% to mine: %mine% at %percentage%%";
	public static final String OrePercentageError = "The percentage you have listed would increase the mine beyond 100%";
	public static final String MineFilledBlock = "%mine% has been filled with: %block%";
	public static final String DeletedMine = "You have deleted your mine with the name: %mine%";
	public static final String MineGroupDeleted = "%group% has been deleted";
	public static final String DeleteGroupFailContents = "%group% can't be deleted as it has contents and this command was not forced";
	public static final String PercentageTooSmall = "The percentage value you entered is too small. Percentage must be greater than 0";
	public static final String PercentageTooSmallUpdate = "The percentage value you entered is too small. Percentage must be greater than 0. A value of 0 will remove the ore from the mine";
	public static final String OreRemoved = "%block% has been removed as you entered 0%";
	public static final String OreAlreadyInMine = "%block% is already in the listed mine so wasn't added. You can change the percentage of the block using the \"/mine updateore\" command";
	public static final String OreNotInMine = "%block% is not in the listed mine so can't be edited";
	public static final String UnableToAddFallback = "%block% is the default block so you cannot add it to the mine again. It is used to top the mine up to 100% full after all the other ores are taken into account";
	public static final String UnableToEditFallback = "%block% is the default block so you cannot edit its percentage or remove it. It is used to top the mine up to 100% full after all the other ores are taken into account";
	public static final String OreSamePercentageError = "No changes were made because the new percentage is the same as the old percentage for %block% in %mine%";
	public static final String ChangedFallback = "You have changed the fallback block to: %block%";
	public static final String SameFallback = "No changes were made because this is already the fallback block";
	public static final String RemindList = "List of seconds before mine reset a reminder message is issued: %list%";
	public static final String RemindTimeExist = "That time is already on the list of times to send a reset reminder so was not added";
	public static final String RemindTimeNotExist = "That time is not on the list of times to send a reset reminder so was not removed";
	public static final String RemindTimeAdd = "You have added %time% (%readableTime%) to the list of times a message will be sent before a mine reset reminding of a mine reset";
	public static final String RemindTimeRemove = "You have removed %time% (%readableTime%) from the list of times a message will be sent before a mine reset reminding of a mine reset";
	public static final String AirBlockExist = "That time is already on the list of times to send a reset reminder so was not added";
	public static final String AirBlockNotExist = "That block is not on the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static final String AirBlockAdd = "You have added %block% to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static final String AirBlockRemove = "You have removed %block% from the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static final String AddSpawnPoint = "You have added a new spawn point for the current world";
	public static final String NoRemoveDefaultSpawn = "You cannot remove the default spawn point";
	public static final String RemoveSpawnPoint = "You have removed spawn point: Spawn%spawnValue%";
	public static final String UpdatedSpawnPoint = "You have updated the spawn point to X:%x% Y:%y% Z:%z% Facing:%facing%";
	public static final String SpawnPointNotExist = "The listed spawn point does not exist";
	public static final String MaxSpawnCount = "You have reached the maximum number of allowed spawns";
	public static final String SpawnPointAlreadyInUse = "%mine% already uses that spawn point";
	public static final String MineSpawnChanged = "%mine% has been changed to now use Spawn%spawnValue%";
	public static final String ChatSettingChanged = "You have successfully changed the chat setting for %type% to %option%";
	public static final String InvalidChatSetting = "The chat setting you listed is invalid. Please refer to documentation";
	public static final String ChatSettingAlreadySet = "The chat settings for %type% is already %option%";
	public static final String InvalidChatType = "That is not a valid chat type. Options (FillingText|ReminderText)";

	/** Command Usages */
	public static final String Help = "/mine help";
	public static final String HelpDescription = "Shows mine help";
	public static final String HelpExtendedDescription = "Use this command to list all the mine sub commands user has access to and how to use them";
	public static final String Reload = "/mine reload";
	public static final String ReloadDescription = "Reloads mine config file";
	public static final String ReloadExtendedDescription = "Reloads mine config file";
	public static final String Save = "/mine save";
	public static final String SaveDescription = "Use this command to save mine config";
	public static final String SaveExtendedDescription = "Saves the mine config being used over one in folder";
	public static final String AddSpawn = "Usage /mine addspawn [x] [y] [z] [facing]";
	public static final String AddSpawnDescription = "Adds a new spawn point with the values listed";
	public static final String AddSpawnExtendedDescription = "Adds a new spawn point with the values listed";
	public static final String RemoveSpawn = "Usage /mine removespawn [spawn point]";
	public static final String RemoveSpawnDescription = "Removes the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn";
	public static final String RemoveSpawnExtendedDescription = "Removes the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn)";
	public static final String UpdateSpawn = "Usage /mine updatespawn [spawn point] [x] [y] [z] [facing]";
	public static final String UpdateSpawnDescription = "Updates the spawn information to the listed values (To alter default spawn use \"Default\" other wise use the number of spawn";
	public static final String UpdateSpawnExtendedDescription = "Updates the spawn information to the listed values (To alter default spawn use \"Default\" other wise use the number of spawn)";
	public static final String ChangeSpawn = "Usage /mine changespawn [mine] [spawn point]";
	public static final String ChangeSpawnDescription = "Set the listed mine to use the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn";
	public static final String ChangeSpawnExtendedDescription = "Set the listed mine to use the listed spawn point (To alter default spawn use \"Default\" other wise use the number of spawn)";
	public static final String Time = "/mine time [mine]";
	public static final String TimeDescription = "Check time remaining before mine resets";
	public static final String TimeExtendedDescription = "Check time remaining before mine resets";
	public static final String Clear = "/mine clear [mine]";
	public static final String ClearDescription = "Clear all blocks from a  mine";
	public static final String ClearExtendedDescription = "Clear all block from a mine";
	public static final String Fill = "/mine fill [mine]";
	public static final String FillDescription = "Fill a mine according to its blocks and percentages";
	public static final String FillExtendedDescription = "Fill a mine according to its blocks and percentages";
	public static final String Fillblock = "/mine fillblock [mine]";
	public static final String FillblockDescription = "Fill a mine with the block you are currently standing on";
	public static final String FillblockExtendedDescription = "Fill a mine with the block you are currently standing on";
	public static final String DefineGroup = "/mine definegroup [group name] [reset time] [initial delay]";
	public static final String DefineGroupDescription = "Create a new mine group";
	public static final String DefineGroupExtendedDescription = "Create a new mine group with name, reset time and initial delay as listed";
	public static final String UpdateGroup = "/mine updategroup [group name] [reset time] [initial delay]";
	public static final String UpdateGroupDescription = "Updates an existing mine group";
	public static final String UpdateGroupExtendedDescription = "Updates an existing mine group with name, reset time and initial delay as listed";
	public static final String DeleteGroup = "/mine deletegroup [group name] [safe|force]";
	public static final String DeleteGroupDescription = "Deletes an existing mine group";
	public static final String DeleteGroupExtendedDescription = "Deletes an existing mine group. If safe then deletion will fail if mine group has contents";
	public static final String DefineMine = "/mine definemine [group] [mine name] [pos1x] [pos1y] [pos1z] [pos2x] [pos2y] [pos2z]";
	public static final String DefineMineDescription = "Create a new mine";
	public static final String DefineMineExtendedDescription = "Create a new mine in listed group with name and positions as listed";
	public static final String RedefineMine = "/mine redefinemine [group] [mine name] [pos1x] [pos1y] [pos1z] [pos2x] [pos2y] [pos2z]";
	public static final String RedefineMineDescription = "Redefine a mine to change its settings";
	public static final String RedefineMineExtendedDescription = "Redefine a mine to change its settings to listed group with name and positions as listed";
	public static final String DeleteMine = "/mine deletemine [mine name]";
	public static final String DeleteMineDescription = "Delete a mine";
	public static final String DeleteMineExtendedDescription = "Delete the mine listed";
	public static final String List = "Usage /mine list [groups|mines|spawns]";
	public static final String ListDescription = "List all groups or mines that exist";
	public static final String ListExtendedDescription = "List all groups or mines that exist";
	public static final String Info = "Usage /mine info [group|mine|spawn] [name]";
	public static final String InfoDescription = "Get all info on group or mine";
	public static final String InfoExtendedDescription = "Get all info on group or mine";
	public static final String Addore = "Usage /mine addore [mine] [percentage]";
	public static final String AddoreDescription = "Adds the block you are standing on to listed mine at listed percentage";
	public static final String AddoreExtendedDescription = "Adds the block you are standing on to listed mine at listed percentage";
	public static final String Updateore = "Usage /mine updateore [mine] [percentage]";
	public static final String UpdateoreDescription = "Updates the block you are standing on in listed mine to listed percentage";
	public static final String UpdateoreExtendedDescription = "Updates the block you are standing on in listed mine to listed percentage";
	public static final String Updatefallback = "Usage /mine updatefallback [mine]";
	public static final String UpdatefallbackDescription = "Updates the mine listed to use the block you are standing on as the fallback block";
	public static final String UpdatefallbackExtendedDescription = "Updates the mine listed to use the block you are standing on as the fallback block";
	public static final String ListReminder = "Usage /mine listreminders";
	public static final String ListReminderDescription = "List the times before a mine reset reminder messages are sent";
	public static final String ListReminderExtendedDescription = "List the times before a mine reset reminder messages are sent";
	public static final String AddRemindTime = "Usage /mine addremindtime [seconds]";
	public static final String AddRemindTimeDescription = "Add a time (in seconds) to send a reset reminder message before a mine reset";
	public static final String AddRemindTimeExtendedDescription = "Add a time (in seconds) to send a reset reminder message before a mine reset";
	public static final String RemoveRemindTime = "Usage /mine removeremindtime [seconds]";
	public static final String RemoveRemindTimeDescription = "Remove a time (in seconds) to send a reset reminder message before a mine reset";
	public static final String RemoveRemindTimeExtendedDescription = "Remove a time (in seconds) to send a reset reminder message before a mine reset";
	public static final String MineSetupSmartFill = "Usage /mine setupsmartfill [mine] [true|false(enabled)] [radius] [true|false(only air)]";
	public static final String MineSetupSmartFillDescription = "Changes the smart fill settings for the listed mine to the listed settings";
	public static final String MineSetupSmartFillExtendedDescription = "Changes the smart fill settings for the listed mine to the listed settings";
	public static final String AddAirBlock = "Usage /mine addairblock";
	public static final String AddAirBlockDescription = "Adds the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static final String AddAirBlockExtendedDescription = "Adds the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static final String RemoveAirBlock = "Usage /mine removeairblock";
	public static final String RemoveAirBlockDescription = "Removes the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";
	public static final String RemoveAirBlockExtendedDescription = "Removes the block the player is standing on to the list of blocks that will not be modified when using SmartFill and SmartFillOnlyAir settings";

	public static final String UpdateChatSettings = "Usage /mine updatechatsettings [FillingText|ReminderText(type)] [setting]";
	public static final String UpdateChatSettingsDescription = "Changes the way messages of the listed type are sent to players";
	public static final String UpdateChatSettingsExtendedDescription = "Changes the way messages of the listed type are sent to players";

	/** Player Messages */
	public static final String ResettingNowSingular = "is resetting now";
	public static final String ResettingNowDefined = "is being filled with %block%";
	public static final String ResettingNowClear = "is being cleared";
	public static final String ResettingNowPlural = "are resetting now";
	public static final String WillResetIn = "will reset in: %time%";
	public static final String InsideFillingMine = "&9The mine you were in has just been reset so you have been teleported to spawn.";

	/** Permissions */
//	help: "minereset.help"
//	time: "minereset.check.time"
//	reload: "minereset.reload"
//	save: "minereset.save"
//  AddSpawn: "minereset.addspawn"
//  RemoveSpawn: "minereset.removespawn"
//  UpdateSpawn: "minereset.updatespawn"
//  ChangeSpawn: "minereset.changespawn"
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
}
