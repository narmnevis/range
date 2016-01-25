package com.narmnevis.range.provider;

import com.narmnevis.range.GeneratorFactory;

public class GenerexGeneratorFactoryProvider extends AbstractGeneratorFactoryProvider {

	@Override
	public GeneratorFactory provideFactory() {
		return new GenerexGeneratorFactory();
	}

}
