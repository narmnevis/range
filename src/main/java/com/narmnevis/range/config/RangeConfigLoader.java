package com.narmnevis.range.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RangeConfigLoader {

	public static final String CONFIG_KEY = "rangeConfig";

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private File configFile;
	private InputStream source;

	/**
	 * @param path
	 */
	public RangeConfigLoader(String path) {
		if (!Files.exists(Paths.get(path))) {
			throw new IllegalArgumentException("The range config file '" + path + "' does not exist.");
		}
		this.configFile = new File(path);
	}

	/**
	 * @param source
	 */
	protected RangeConfigLoader(InputStream source) {
		this.configFile = null;
		this.source = source;
	}

	public RangeConfig loadRangeConfig() {
		try {
			if (source == null) {
				source = Files.newInputStream(this.configFile.toPath());
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			mapper.configure(Feature.ALLOW_COMMENTS, true);
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			RangeConfig config = mapper.readValue(this.source, RangeConfig.class);
			logger.info("Loaded range configuration '{}': {}", this.configFile, config);
			return config;
		} catch (IOException e) {
			logger.error("Failed to load range configuration from {}: {}", this.configFile, e);
			return null;
		}
	}

}
