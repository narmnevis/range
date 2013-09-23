package com.narmnevis.range.provider;

import com.narmnevis.range.Generator;
import com.narmnevis.range.generator.NumericRangeGenerator;

/**
 * @author nobeh
 * @since 1.0
 */
public class NumericRangeGeneratorFactory extends AbstractGeneratorFactory {

	@Override
	public Generator create(String spec) {
		int openning = spec.indexOf('(');
		int closing = spec.indexOf(')');
		if (openning > 0 && closing > 0) {
			String values = spec.substring(openning + 1, closing);
			String[] range = values.split(",");
			if (range.length != 2) {
				logger.error("Cannot create a numer range generator with spec: {}", spec);
				return null;
			}
			try {
				long from = Long.parseLong(range[0].trim());
				long to = Long.parseLong(range[1].trim());
				if (from >= to) {
					logger.error("Cannot create a numeric range generator with invalid range: {}", spec);
					return null;
				}
				logger.info("Created an instance of numeric range generator for range ({}, {})", from, to);
				return new NumericRangeGenerator(from, to);
			} catch (NumberFormatException e) {
				logger.error("Cannot create a numeric range generator with invalid numbers: {}", spec);
				return null;
			}
		}
		logger.error("Cannot create a numeric range generator with spec: {}", spec);
		return null;
	}

	@Override
	public boolean supports(String spec) {
		return spec.startsWith("range(");
	}

}
