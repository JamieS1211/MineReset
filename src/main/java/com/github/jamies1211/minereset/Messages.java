package com.github.jamies1211.minereset;

/**
 * Created by Jamie on 31-May-16.
 */
public class Messages {
	/** General */
	public static final String MinePrefix = "&9&l[Mines]&r &e";

	/** Errors */
	public static final String TeleportRotationError = "&c&l[Mines] ERROR: Teleport function direction not valid so unused. \"North\", \"South\", \"East\" or \"West\" expected.";
	public static final String ResetTimeTooShortError = "&c&l[Mines] ERROR: Reset time was read from config at less than 60 seconds. It has been changed to 60";

	/** Command Messages */
	public static final String PlayerOnlyCommand = "This command must be run by a player";
	public static final String ConfigSaved = "The config file has been saved";
	public static final String ConfigReloaded = "The config file has been reloaded";
	public static final String MineFilled = "has been filled";
	public static final String MineCleared = "has been cleared";
	public static final String ResetTime = "will reset in:";
	public static final String SetSpawnPoint = "You have set the spawn for all mines to";
	public static final String MineDoesNotExist = "does not exist";
	public static final String MineGroupDoesNotExist = "is not a mine group";
	public static final String NoMines = "You have no mines";
	public static final String NoMineGroups = "You have no mine groups";
	public static final String MineAlreadyExists = "is the name of already existing mine";
	public static final String MineGroupAlreadyExists = "is the name of already existing mine group";
	public static final String DefinedNewMine = "You have defined a new mine with the name: ";
	public static final String DefinedNewMineGroup = "You have defined a new mine group with the name: ";
	public static final String ResetTimeTooShort = "Your reset time for a new group must be 1 minute or more";
	public static final String DelayTooShort = "Your delay can't be less than 0";
	public static final String AddedOre = "You have added: ";
	public static final String OrePrecentageError = "The percentage you have listed would increase the mine beyond 100%";
	public static final String MineFilledBlock = "has been filled with:";
	public static final String DeletedMine = "You have deleted your mine with the name:";
	public static final String MineGroupDeleted = "has been deleted";
	public static final String DeleteGroupFailContents = "can't be deleted as it has contents and this command was not forced";
	public static final String PercentageTooSmall = "the percentage value you entered is too small. Percentage must be greater than 0";
	public static final String PercentageTooSmallUpdate = "the percentage value you entered is too small. Percentage must be greater than 0. A value of 0 will remove the ore from the mine";
	public static final String OreRemoved = "has been removed as you entered 0%";
	public static final String OreAlreadyInMine = "is already in the listed mine so wasn't added. You can change the percentage of the block using the \"/mine updateore\" command";
	public static final String OreNotInMine = "is not in the listed mine so can't be edited";
	public static final String UnableToAddFallback = "is the default block so you cannot add it to the mine again. It is used to top the mine up to 100% full after all the other ores are taken into account";
	public static final String UnableToEditFallback = "is the default block so you cannot edit its percentage or remove it. It is used to top the mine up to 100% full after all the other ores are taken into account";
	public static final String OreSamePercentageError = "No changes were made because the new percentage is the same as the old percentage for";

	/** Command Usages */
	public static final String Help = "/mine help";
	public static final String HelpDescription = "Shows mine help";
	public static final String HelpExtendedDescription = "Use this command to list all the safari sub commands and how to use them";
	public static final String Reload = "/mine reload";
	public static final String ReloadDescription = "Reloads mine config fine";
	public static final String ReloadExtendedDescription = "Reloads mine config fine";
	public static final String Save = "/mine save";
	public static final String SaveDescription = "Use this command to save mine config";
	public static final String SaveExtendedDescription = "Saves the mine config being used over one in folder";
	public static final String Setspawn = "/mine setspawn";
	public static final String SetspawnDescription = "Saves the global mine spawn";
	public static final String SetspawnExtendedDescription = "Use this command to save the spot players are sent to when they are in a mine on mine reset";
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
	public static final String DeleteGroup = "/mine deletegroup [group name] [safe|force]";
	public static final String DeleteGroupDescription = "Deletes an existing mine group";
	public static final String DeleteGroupExtendedDescription = "Deletes an existing mine group. If safe then deletion will fail if mine group has contents";
	public static final String DefineMine = "/mine definemine [group] [mine name] [pos1x] [pos1y] [pos1z] [pos2x] [pos2y] [pos2z]";
	public static final String DefineMineDescription = "Create a new mine";
	public static final String DefineMineExtendedDescription = "Create a new mine in listed group with name and positions as listed";
	public static final String DeleteMine = "/mine deletemine [mine name]";
	public static final String DeleteMineDescription = "Delete a mine";
	public static final String DeleteMineExtendedDescription = "Delete the mine listed";
	public static final String List = "Usage /mine list [groups|mines]";
	public static final String ListDescription = "List all groups or mines that exist";
	public static final String ListExtendedDescription = "List all groups or mines that exist";
	public static final String Info = "Usage /mine info [group|mine] [name]";
	public static final String InfoDescription = "Get all info on group or mine";
	public static final String InfoExtendedDescription = "Get all info on group or mine";
	public static final String Addore = "Usage /mine addmore [mine] [percentage]";
	public static final String AddoreDescription = "Adds the block you are standing on to listed mine at listed percentage";
	public static final String AddoreExtendedDescription = "Adds the block you are standing on to listed mine at listed percentage";
	public static final String Updateore = "Usage /mine updatemore [mine] [percentage]";
	public static final String UpdateoreDescription = "Updates the block you are standing on in listed mine to listed percentage";
	public static final String UpdateoreExtendedDescription = "Updates the block you are standing on in listed mine to listed percentage";

	/** Player Messages */
	public static final String ResetingNowSingular = "is resetting now";
	public static final String ResetingNowPlural = "are resetting now";
	public static final String NoPlayerOnline = "mine re-filling disabled as no player is online";
	public static final String WillResetIn = "will reset in:";
	public static final String InsideFillingMine = "&9The mine you were in has just been reset so you have been teleported to spawn.";

	/** Permissions */
//	help: "minereset.help"
//	time: "minereset.check.time"
//	reload: "minereset.reload"
//	save: "minereset.save"
//	setspawn: "minereset.setspawn"
//	clear: "minereset.clear"
//	fill: "minereset.fill"
//	fill: "minereset.fillblock"
//	definegroup: "minereset.define.group"
//	deletegroup: "minereset.delete.group"
//	definemine: "minereset.define.mine"
//	deletemine: "minereset.delete.mine"
//	list: "minereset.list"
//	info: "minereset.details"
//	addore: "minereset.mine.addore"
//	updateore: "minereset.mine.updateore"
}
