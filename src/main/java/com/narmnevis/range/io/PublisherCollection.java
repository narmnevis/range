package com.narmnevis.range.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.narmnevis.range.Publisher;
import com.narmnevis.range.RangeContext;

public class PublisherCollection implements Publisher, Iterable<Publisher> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Collection<Publisher> publishers = new ArrayList<>();

	public PublisherCollection() {
		addPublisher(new FilePublisher());
	}

	public void addPublisher(Publisher publisher) {
		publishers.add(publisher);
	}

	@Override
	public void publish(RangeContext context) {
		for (Publisher publisher : publishers) {
			try {
				publisher.publish(context);
				logger.info("Publisher {} published context {}", publisher, context);
			} catch (Exception e) {
				logger.error("Exception occurred: ", ExceptionUtils.getRootCause(e));
				logger.error("Publisher {} failed to publish context {}", publisher, context);
			}
		}
	}

	@Override
	public void close() {
		for (Publisher publisher : publishers) {
			try {
				publisher.close();
			} catch (Exception e) {
				logger.error("Failed to close publisher {}: {}", publisher, e.getMessage());
			}
		}
	}

	@Override
	public Iterator<Publisher> iterator() {
		return publishers.iterator();
	}

}
