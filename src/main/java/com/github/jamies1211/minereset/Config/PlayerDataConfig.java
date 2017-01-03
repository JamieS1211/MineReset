package com.github.jamies1211.minereset.Config;

import com.github.jamies1211.minereset.MineReset;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.jamies1211.minereset.Messages.*;

/**
 * Created by Jamie on 27-Jul-16.
 */
public class PlayerDataConfig {

	private static PlayerDataConfig config = new PlayerDataConfig();

	public static PlayerDataConfig getConfig() {
		return config;
	}

	private Path configFile = Paths.get(MineReset.getPlugin().getConfigDir() + "/playerData.conf");
	private ConfigurationLoader<CommentedConfigurationNode> configLoader = HoconConfigurationLoader.builder().setPath(configFile).build();
	private CommentedConfigurationNode configNode;

	public void setup() {
		if (!Files.exists(configFile)) {
			try {
				Files.createFile(configFile);
				load();
				save();
				MineReset.plugin.getLogger().info(newConfigFile);
			} catch (IOException e) {
				e.printStackTrace();
				MineReset.plugin.getLogger().info(configLoadError);

			}
		} else {
			load();
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
}
