package com.narmnevis.range;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public class SimpleData implements Data {

	private final Collection<Datum> data;

	public SimpleData(Collection<Datum> data) {
		this.data = data;
	}

	@Override
	public Iterator<Datum> iterator() {
		return data.iterator();
	}

}
