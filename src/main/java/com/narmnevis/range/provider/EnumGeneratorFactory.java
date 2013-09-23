package com.narmnevis.range.provider;

import java.util.Set;

import com.google.common.collect.Sets;
import com.narmnevis.range.Generator;
import com.narmnevis.range.generator.EnumGenerator;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public class EnumGeneratorFactory extends AbstractGeneratorFactory {

	@Override
	public Generator create(String spec) {
		int openning = spec.indexOf('(');
		int closing = spec.indexOf(')');
		if (openning > 0 && closing > 0) {
			String values = spec.substring(openning + 1, closing);
			String[] candidateArray = values.split(",");
			Set<String> candidates = Sets.newHashSet(candidateArray);
			if (candidates.isEmpty()) {
				logger.error("Cannot create an enum generator with no candidates: {}", spec);
				return null;
			}
			logger.info("Created an instance of enum generator with candidates: {}", candidates);
			return new EnumGenerator(candidates);
		}
		logger.error("Cannot create an enum generator with spec: {}", spec);
		return null;
	}

	@Override
	public boolean supports(String spec) {
		return spec.startsWith("enum(");
	}

}
