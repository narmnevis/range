package com.narmnevis.range.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.narmnevis.range.Datum;
import com.narmnevis.range.Publisher;
import com.narmnevis.range.RangeContext;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public abstract class AbstractPublisher implements Publisher {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void publish(RangeContext context) {
		Iterable<String> names = context.getNames();

		// initialize
		init(context);

		// prepare format
		String format = null;
		try {
			format = createFormat(context);
		} catch (Exception e) {
			logger.error("Failed to create a format for publishing for '{}': {}", context.getFormat(), e.getMessage());
			throw new IllegalStateException(e);
		}

		// publish header
		try {
			publishNames(context, names, format);
		} catch (Exception e) {
			logger.warn("Failed to publish names {}: {}", names, e.getMessage());
		}

		for (Datum datum : context) {
			try {
				// publish one single datum
				publishDatum(context, datum, format);
			} catch (Exception e) {
				logger.error("Could not publish a datum {}: {}", datum, e.getMessage());
			}
		}

		logger.info("Publishing of context {} complete.", context);
	}

	protected void publishDatum(RangeContext context, Datum datum, String format) {
		String formattedDatum = format(datum, format);
		publishFormattedDatum(context, datum, formattedDatum);
	}

	protected String createFormat(RangeContext context) {
		int size = context.getNames().size();
		String format = context.getFormat();
		if ("csv".equalsIgnoreCase(format)) {
			return Strings.repeat("%s,", size - 1) + "%s";
		}
		return Strings.repeat("%s ", size - 1) + "%s";
	}

	protected String format(Iterable<?> objects, String format) {
		return String.format(format, Iterables.toArray(objects, Object.class));
	}

	protected abstract void init(RangeContext context);

	protected abstract void publishNames(RangeContext context, Iterable<String> names, String format);

	protected abstract void publishFormattedDatum(RangeContext context, Datum datum, String formattedDatum);
}
