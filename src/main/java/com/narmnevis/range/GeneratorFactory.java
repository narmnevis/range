package com.narmnevis.range;

public interface GeneratorFactory {

	Generator create(String spec);

	boolean supports(String spec);

}
