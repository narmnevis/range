package com.narmnevis.range.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.narmnevis.range.Generator;
import com.narmnevis.range.Randomizer;
import com.narmnevis.range.RangeContext;
import com.narmnevis.range.random.GaussianDistributionRandomizer;

public abstract class AbstractGenerator implements Generator {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final Randomizer randomizer = new GaussianDistributionRandomizer();

	public AbstractGenerator() {
	}

	/**
	 * @param context
	 * @return
	 * 
	 * @since 1.2
	 */
	protected Double getNextRandom(RangeContext context) {
		return randomizer.next(context);
	}

	protected List<Double> generateRandoms(RangeContext context) {
		Random r = new Random(System.nanoTime());
		List<Double> randoms = new ArrayList<>();
		for (int i = 1; i <= context.getSize(); ++i) {
			randoms.add(r.nextDouble());
		}
		return randoms;
	}

}