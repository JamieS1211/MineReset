package com.github.jamies1211.minereset.Actions;

import com.github.jamies1211.minereset.Config.GeneralDataConfig;
import ninja.leaping.configurate.ConfigurationNode;

/**
 * Created by Jamie on 28/12/2016.
 */
public class GetMineGroup {

	public static String getMineGroup (String mine) {
		ConfigurationNode config = GeneralDataConfig.getConfig().get();

		String group = null;

		for (final Object groupObject : config.getNode("4 - MineGroups").getChildrenMap().keySet()) {
			if (config.getNode("4 - MineGroups", groupObject.toString()).getChildrenMap().containsKey(mine)) {
				group = groupObject.toString();
				break;
			}
		}

		return group;
	}
}
