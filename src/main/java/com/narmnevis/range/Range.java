package com.narmnevis.range;

import java.util.Map;

import com.narmnevis.range.config.RangeConfig;
import com.narmnevis.range.config.RangeConfigLoader;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public class Range {

	private final RangeConfig config;

	public Range() {
		config = new RangeConfig();
	}

	protected Range(String configPath) {
		RangeConfigLoader loader = new RangeConfigLoader(configPath);
		config = loader.loadRangeConfig();
	}

	public Range withConfiguration(String configPath) {
		return new Range(configPath);
	}

	public Range withSize(Integer size) {
		config.setLimit(size);
		return this;
	}

	public Range withOutputFormat(String format) {
		config.setFormat(format);
		return this;
	}

	public Range withLocation(String location) {
		config.setLocation(location);
		return this;
	}

	public Range withDataSpec(String name, String spec) {
		config.getData().put(name, spec);
		return this;
	}

	public Range withDataSpecs(Map<String, String> data) {
		config.setData(data);
		return this;
	}

	public Range withRandomizerSpec(String name, String spec) {
		config.getRandomizers().put(name, spec);
		return this;
	}

	public Range withRandomizerSpecs(Map<String, String> randomizers) {
		config.setRandomizers(randomizers);
		return this;
	}

	public Data generate() {
		RangeContext context = createContext(config);
		context.generate(null);
		context.publish(null);
		return context;
	}

	protected RangeContext createContext(RangeConfig config) {
		int processors = Runtime.getRuntime().availableProcessors();
		if (processors > 2) {
			return new ConcurrentRangeContext(config);
		}
		return new SimpleRangeContext(config);
	}

}
