package com.narmnevis.range.provider;

import com.narmnevis.range.Generator;

/**
 * @author nobeh
 * @since 1.0
 */
public class ClassGeneratorFactory extends AbstractGeneratorFactory {

	@Override
	public Generator create(String spec) {
		int index = spec.indexOf(':');
		String className = spec.substring(index + 1);
		try {
			Class<?> clazz = Class.forName(className);
			if (!Generator.class.isAssignableFrom(clazz)) {
				logger.error("Class '{}' cannot be a generator.", className);
				return null;
			}
			Object generator = clazz.newInstance();
			if (generator instanceof Generator == false) {
				logger.error("Object {} is not an instance of {} provided by {}.", generator, Generator.class, clazz);
				return null;
			}
			logger.info("Created a generator '{}' using class '{}'.", generator, clazz);
			return (Generator) generator;
		} catch (ClassNotFoundException e) {
			logger.error("Cannot find generator class '{}' in the classpath.", e.getMessage());
		} catch (InstantiationException e) {
			logger.error("Cannot instantiate a generator object with spec '{}': {}", spec, e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error("Cannot access generator class with spec '{}': {}", spec, e.getMessage());
		}
		return null;
	}

	@Override
	public boolean supports(String spec) {
		return spec.startsWith("class:");
	}

}
