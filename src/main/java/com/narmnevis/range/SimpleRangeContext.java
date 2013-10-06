package com.narmnevis.range;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.narmnevis.range.config.RangeConfig;
import com.narmnevis.range.io.PublisherCollection;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public class SimpleRangeContext implements RangeContext {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final RangeConfig config;
	private final List<String> names = new ArrayList<>();

	private Data data;
	private Publisher publisher = new PublisherCollection();

	public SimpleRangeContext(RangeConfig config) {
		this.config = config;
		this.names.addAll(config.getData().keySet());
	}

	@Override
	public String getFormat() {
		return config.getFormat();
	}

	@Override
	public String getLocation() {
		return config.getLocation();
	}

	@Override
	public Integer getSize() {
		return config.getLimit();
	}

	@Override
	public List<String> getNames() {
		return names;
	}

	@Override
	public String getSpec(String name) {
		return this.config.getData().get(name);
	}

	@Override
	public Iterator<Datum> iterator() {
		if (data == null) {
			throw new IllegalStateException("Generation has not been performed yet.");
		}
		return data.iterator();
	}

	@Override
	public Object generate(RangeContext context) {
		// create generators
		Map<String, Generator> generators = createGenerators(names);

		// generate data
		Collection<Datum> datums = generate(names, generators);
		data = new SimpleData(datums);
		logger.info("Generated data with size {}.", datums.size());

		return data;
	}

	@Override
	public void publish(RangeContext context) {
		publisher.publish(this);
		logger.info("Generated data with size {} published to {}", this.getSize(), this.getLocation());
	}

	@Override
	public void close() throws IOException {
		publisher.close();
	}

	protected Collection<Datum> generate(List<String> names, Map<String, Generator> generators) {
		Collection<Datum> data = new ArrayList<>();
		for (int i = 1; i <= getSize(); ++i) {
			Datum datum = generateDatum(names, generators);
			data.add(datum);
			logger.debug("Generation completed at iteration {}", i);
		}
		return data;
	}

	/**
	 * @param names
	 * @param generators
	 * @return
	 */
	protected Datum generateDatum(List<String> names, Map<String, Generator> generators) {
		final Collection<Object> values = new ArrayList<Object>();
		for (String name : names) {
			Generator generator = generators.get(name);
			Object value = null;
			if (generator != null) {
				value = generator.generate(this);
			}
			if (value == null) {
				value = NO_VALUE;
			}
			values.add(value);
		}
		return new Datum() {
			@Override
			public Iterator<Object> iterator() {
				return values.iterator();
			}
		};
	}

	protected Map<String, Generator> createGenerators(List<String> names) {
		GeneratorFactoryProviders providers = new GeneratorFactoryProviders();
		Map<String, Generator> generators = new HashMap<>();
		for (String name : names) {
			String spec = getSpec(name);
			Generator generator = providers.create(spec);
			if (generator != null) {
				generators.put(name, generator);
			}
		}
		return generators;
	}

}
