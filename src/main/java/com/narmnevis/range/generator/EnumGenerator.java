package com.narmnevis.range.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.narmnevis.range.RangeContext;

/**
 * @author nobeh
 * @since 1.0
 */
public class EnumGenerator extends RandomizedGenerator {

	private final List<String> candidates;

	public EnumGenerator(Collection<String> candidates) {
		this.candidates = new ArrayList<>(candidates);
	}

	@Override
	public Object generate(RangeContext context) {
		try {
			Double s = getNextRandom(context);
			final int index;
			if (randomizer == null) {
				index = getIndex(s, candidates.size());
			} else {
				index = getRandomizedIndexWithProbability(context, randomizer, s);
			}
			String next = candidates.get(index);
			return next;
		} catch (Exception e) {
			logger.error("Failed to generate the next enum value from {}: {}", candidates, e);
		}
		return null;
	}
}
