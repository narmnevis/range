package com.narmnevis.range.generator;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.narmnevis.range.Randomizer;
import com.narmnevis.range.RandomizerAware;
import com.narmnevis.range.RangeContext;

/**
 * @author nobeh
 * @since 1.3
 */
public abstract class RandomizedGenerator extends AbstractGenerator implements RandomizerAware {

	protected final AtomicInteger randomIndex = new AtomicInteger(0);
	protected Randomizer randomizer = null;

	public RandomizedGenerator() {
		super();
	}

	@Override
	public void setRandomizer(Randomizer randomizer) {
		this.randomizer = randomizer;
	}

	protected synchronized int getIndex(Double d, int size) {
		return new Double(d * size).intValue();
	}

	/**
	 * @param context
	 * @param randomizer
	 * @param p
	 *            the probability that the index should be calculated against
	 * @return an index in range (0, size(randomizer))
	 */
	protected synchronized int getRandomizedIndexWithProbability(RangeContext context, Randomizer randomizer, Double p) {
		List<Double> randoms = randomizer.values(context);
		AtomicInteger index = new AtomicInteger(0);
		Double sum = 0.;
		while (p > sum) {
			sum += randoms.get(index.get());
			if (p <= sum) {
				return index.get();
			} else if (incrementRandomIndex(index, randoms.size()) == 0) {
				// one round complete
				break;
			}
		}
		// no random index can be found using the randomizer and the probability
		return new Random().nextInt(randoms.size());
	}

	protected synchronized int incrementRandomIndex(AtomicInteger randomIndex, int size) {
		if (randomIndex.incrementAndGet() == size) {
			randomIndex.getAndSet(0);
		}
		return randomIndex.get();
	}

}