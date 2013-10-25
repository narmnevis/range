package com.narmnevis.range.config;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author nobeh
 * @since 1.0
 */
@JsonRootName("config")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RangeConfig {

	private Integer limit = 1000;
	private String format = "csv";
	private String location = null;
	private Map<String, String> data = new HashMap<>();
	private Map<String, String> randomizers = new HashMap<>();

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setRandomizers(Map<String, String> randomizers) {
		this.randomizers = randomizers;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public Map<String, String> getRandomizers() {
		return randomizers;
	}

	@Override
	public String toString() {
		return "Config (limit=" + limit + ", format=" + format + ", data=" + data + ", randomizers=" + randomizers
				+ ", location=" + location + ")";
	}
}
