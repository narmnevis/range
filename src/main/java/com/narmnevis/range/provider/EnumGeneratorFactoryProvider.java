package com.narmnevis.range.provider;

import com.narmnevis.range.GeneratorFactory;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public class EnumGeneratorFactoryProvider extends AbstractGeneratorFactoryProvider {

	@Override
	public GeneratorFactory provideFactory() {
		return new EnumGeneratorFactory();
	}

}
