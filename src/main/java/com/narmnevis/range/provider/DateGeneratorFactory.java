package com.narmnevis.range.provider;

import com.narmnevis.range.Generator;
import com.narmnevis.range.generator.DateGenerator;

/**
 * 
 * @author nobeh
 * @since 1.0
 * 
 */
public class DateGeneratorFactory extends AbstractGeneratorFactory {

	@Override
	public Generator create(String spec) {
		DateGenerator generator = new DateGenerator();
		logger.info("Created an instance of generator {}", generator);
		return generator;
	}

	@Override
	public boolean supports(String spec) {
		return spec.startsWith("date");
	}

}
