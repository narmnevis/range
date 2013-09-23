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

	public Range withDataSpec(String name, String spec) {
		config.getData().put(name, spec);
		return this;
	}

	public Range withDataSpecs(Map<String, String> data) {
		config.setData(data);
		return this;
	}

	public Data generate() {
		RangeContext context = new SimpleRangeContext(config);
		context.generate(null);
		context.publish(null);
		return context;
	}

}
