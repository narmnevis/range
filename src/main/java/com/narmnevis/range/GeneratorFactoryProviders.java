package com.narmnevis.range;

import java.util.Collection;
import java.util.HashSet;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorFactoryProviders implements GeneratorFactory {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Collection<GeneratorFactory> factories = new HashSet<>();

	public GeneratorFactoryProviders() {
		ServiceLoader<GeneratorFactoryProvider> loader = ServiceLoader.load(GeneratorFactoryProvider.class);
		for (GeneratorFactoryProvider p : loader) {
			try {
				GeneratorFactory factory = p.provideFactory();
				factories.add(factory);
				logger.info("Registered generator factory: {}", factory);
			} catch (Exception e) {
				logger.error("Failed to register a generator factory using provider {}: {}", p, e);
			}
		}
	}

	@Override
	public Generator create(String spec) {
		for (GeneratorFactory f : factories) {
			if (f.supports(spec)) {
				try {
					Generator g = f.create(spec);
					logger.info("Created an instance of generator {} using factory {} for specification {}.", g, f,
							spec);
					return g;
				} catch (Exception e) {
					logger.error("Failed to create an instance of generator for spec {} with factory {}: {}", spec, f,
							e.getMessage());
				}
			}
		}
		logger.error("No factory supports generation specification {}", spec);
		return null;
	}

	@Override
	public boolean supports(String spec) {
		return false;
	}

}
