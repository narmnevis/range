package com.narmnevis.range;

import java.util.List;

/**
 * @author nobeh
 * @since 1.3
 */
public interface Randomizer {

	/**
	 * @param context
	 * @return
	 */
	Double next(RangeContext context);

	/**
	 * @return
	 */
	List<Double> values(RangeContext context);

	/**
	 * @param context
	 * @return
	 */
	Double sum(RangeContext context);

}
