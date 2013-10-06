package com.narmnevis.range.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.narmnevis.range.Generator;
import com.narmnevis.range.RangeContext;

public abstract class AbstractGenerator implements Generator {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractGenerator() {
	}

	protected List<Double> generateRandoms(RangeContext context) {
		Random r = new Random(System.nanoTime());
		List<Double> randoms = new ArrayList<>();
		for (int i = 1; i <= context.getSize(); ++i) {
			randoms.add(r.nextDouble());
		}
		return randoms;
	}

	/**
	 * @param context
	 * @return
	 * 
	 * @since 1.1
	 */
	protected List<Double> generateGaussianRandoms(RangeContext context) {
		// mean = 0 and std.dev = 0.33 ==> 99.7% of data is in (0, 1)
		NormalDistribution nd = new NormalDistribution(0, 1.0 / 3);
		List<Double> randoms = new ArrayList<>();
		double[] samples = nd.sample(context.getSize());
		for (double s : samples) {
			double r = 1 * (1 + s) / 2;
			// the very minority of 0.3%
			if (Math.abs(r) > 1) {
				r = Math.abs(1 - r);
			} else if (r < 0) {
				r = Math.abs(r);
			}
			assert r < 1 && r > 0 : "Sanity check for a Gaussian values between (0, 1) with (0, 0.33): " + r;
			randoms.add(r);
		}
		return Collections.synchronizedList(randoms);
	}

}