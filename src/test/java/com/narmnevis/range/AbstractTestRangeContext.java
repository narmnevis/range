package com.narmnevis.range;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTestRangeContext implements RangeContext {

	@Override
	public Object generate(RangeContext context) {
		return null;
	}

	@Override
	public Iterator<Datum> iterator() {
		return null;
	}

	@Override
	public void publish(RangeContext context) {
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public Integer getSize() {
		return null;
	}

	@Override
	public String getFormat() {
		return null;
	}

	@Override
	public String getLocation() {
		return null;
	}

	@Override
	public List<String> getNames() {
		return null;
	}

	@Override
	public String getSpec(String name) {
		return null;
	}

}
