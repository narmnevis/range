package com.narmnevis.range;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.narmnevis.range.random.GaussianDistributionRandomizer;
import com.narmnevis.range.random.ListRandomizer;

/**
 * @author nobeh
 * @since 1.3
 * 
 */
public class RandomizerFactories {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public Randomizer create(String spec) {
		if (spec.startsWith("P(")) {
			return createProbabilityListRandomizer(spec);
		} else if (spec.equalsIgnoreCase("Guassian")) {
			return new GaussianDistributionRandomizer();
		}
		return null;
	}

	/**
	 * @param spec
	 * @return
	 */
	protected Randomizer createProbabilityListRandomizer(String spec) {
		int opening = spec.indexOf('(');
		int closing = spec.lastIndexOf(')');
		if (opening > 0 && closing > 0 && opening < closing) {
			String pString = spec.substring(opening + 1, closing);
			String[] probs = pString.split(",");
			List<Double> list = new ArrayList<>();
			for (String p : probs) {
				try {
					double d = Double.parseDouble(p);
					if (d < -1.0 || d > 1.0) {
						logger.error(
								"Probability list randomizer specification should include floating point numbers in range (-1, 1): {}",
								spec);
						return null;
					}
					list.add(d);
				} catch (NumberFormatException e) {
					logger.error("Probability list randomizer includes invalid floating numbers: {}", spec);
					return null;
				}
			}
			return new ListRandomizer(list);
		} else {
			logger.error("Invalid probability list randomizer specification: {}", spec);
			return null;
		}
	}

}
