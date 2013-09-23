package com.narmnevis.range;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public interface Generator {

	String NO_VALUE = "null";

	Object generate(RangeContext context);

}
