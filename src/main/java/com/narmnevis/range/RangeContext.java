package com.narmnevis.range;

import java.util.List;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public interface RangeContext extends Generator, Data, Publisher {

	Integer getSize();

	String getFormat();
	
	String getLocation();

	List<String> getNames();

	String getSpec(String name);

}
