package com.narmnevis.range.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.narmnevis.range.Range;
import com.narmnevis.range.config.RangeConfigLoader;

/**
 * A CLI interface for {@link Range}.
 * 
 * @author nobeh
 * @since 1.0
 */
public class RangeCli {

	private static final Logger LOGGER = LoggerFactory.getLogger(RangeCli.class);

	public static void main(String[] args) {
		String configPath = null;
		if (args.length > 1) {
			LOGGER.error("There should be only one parameter to execute: the path of the configuaration file.");
			System.exit(1);
		}
		if (args.length == 1) {
			configPath = args[0];
		}
		if (configPath == null) {
			configPath = System.getProperty(RangeConfigLoader.CONFIG_KEY, null);
			if (configPath == null) {
				configPath = System.getenv(RangeConfigLoader.CONFIG_KEY);
			}
		}
		if (configPath == null) {
			LOGGER.error("Either execute with the path to the configuration file or set a system property as -D{} "
					+ "that hold the path to the configuration file.", RangeConfigLoader.CONFIG_KEY);
			System.exit(1);
		}
		new Range().withConfiguration(configPath).generate();
	}

}
