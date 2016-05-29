package com.github.jamies1211.minereset;

/**
 * Created by Jamie on 27-May-16.
 */
public class SecondsToString {

	public static String secondsToTimeString(int totalSeconds) {
		final int seconds = totalSeconds % 60; // Seconds more then int minutes.
		final int mins = (totalSeconds - seconds) / 60; // Minutes rounded down.

		String secondUnit = "seconds";
		String minuteUnit = "minutes";

		if (seconds == 1) { // Removes plural if not needed.
			secondUnit = "second";
		}

		if (mins == 1) { // Removes plural if not needed.
			minuteUnit = "minute";
		}

		if (mins == 0) { // If mins are 0 don't return minutes.
			return seconds + secondUnit;
		} else {
			return mins + minuteUnit + " " + seconds + secondUnit;
		}
	}

}
