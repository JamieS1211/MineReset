package com.github.jamies1211.minereset;

/**
 * Created by Jamie on 27-May-16.
 */
public class SecondsToString {

	public static String secondsToTimeString(int totalSeconds) {
		final int seconds = totalSeconds % 60; // Seconds more then int minutes.
		final int minutes = ((totalSeconds - seconds) / 60) % 60; // Minutes rounded down.
		final int hours = ((((totalSeconds - seconds) / 60) - minutes) / 60); // Hours rounded down.


		String secondUnit = Messages.secondUnitPlural + " ";
		String minuteUnit = Messages.minuteUnitPlural + " ";
		String hourUnit = Messages.hourUnitPlural + " ";

		String secondsString = Integer.toString(seconds);
		String minutesString = Integer.toString(minutes);
		String hoursString = Integer.toString(hours);

		if (seconds == 1) { // Removes plural if not needed.
			secondUnit = Messages.secondUnitSingular + " ";
		} else if (seconds == 0 && (hours != 0 || minutes != 0)) {
			secondsString = "";
			secondUnit = "";
		}

		if (minutes == 1) { // Removes plural if not needed.
			minuteUnit = Messages.minuteUnitSingular + " ";
		} else if (minutes == 0) {
			minutesString = "";
			minuteUnit = "";
		}

		if (hours == 1) { // Removes plural if not needed.
			hourUnit = Messages.hourUnitSingular + " ";
		} else if (hours == 0) {
			hoursString = "";
			hourUnit = "";
		}



		return hoursString + hourUnit + minutesString + minuteUnit + secondsString + secondUnit;
	}

}
