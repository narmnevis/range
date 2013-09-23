package com.narmnevis.range;

import java.io.Closeable;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public interface Publisher extends Closeable {

	void publish(RangeContext context);

}
