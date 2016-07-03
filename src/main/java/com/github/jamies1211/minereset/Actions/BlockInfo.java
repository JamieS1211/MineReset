package com.github.jamies1211.minereset.Actions;

/**
 * Created by Jamie on 03-Jul-16.
 */
public class BlockInfo {

	public final String blockStateString;
	public double percentage;

	public BlockInfo(String blockStateString, double percentage) {
		this.blockStateString = blockStateString;
		this.percentage = percentage;
	}

	public BlockInfo(String blockStateString) {
		this.blockStateString = blockStateString;
		this.percentage = 100f;
	}

}
