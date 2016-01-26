package com.narmnevis.range.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.narmnevis.range.Datum;
import com.narmnevis.range.RangeContext;

/**
 * @author nobeh
 * @since 1.0
 * 
 */
public class FilePublisher extends AbstractPublisher {

	private File location;
	private PrintWriter writer;

	public FilePublisher() {
	}

	@Override
	protected void init(RangeContext context) {
		location = new File(context.getLocation());
		if (location.exists()) {
			logger.warn("Location {} already exists. The contents will be overwritten.", location);
		}
		try {
			Files.createDirectories(location.toPath().getParent());
			writer = new PrintWriter(Files.newBufferedWriter(location.toPath(), Charset.defaultCharset()));
		} catch (IOException e) {
			logger.error("Cannot prepare output location {}", location);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	protected void publishNames(RangeContext context, Iterable<String> names, String format) {
		String line = format(names, format);
		writer.println(line);
	}

	@Override
	protected void publishFormattedDatum(RangeContext context, Datum datum, String formattedDatum) {
		writer.println(formattedDatum);
		writer.flush();
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}

	@Override
	public String toString() {
		return getClass().getName() + ":" + ((location !=null ) ? location.getAbsolutePath() : "no file");
	}
}
