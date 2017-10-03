package io.com.gb.transactions.addons;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runner.RunWith;

import junit.framework.TestCase;
@RunWith(JUnit4.class)
public class FileHandlerTest extends TestCase {
	@Test(expected = RuntimeException.class)
	public void listingMissingDirectoryThrowsException() {
		FileHandler.listFiles(Paths.get("testfailure/"));
	}

	@Test(expected = RuntimeException.class)
	public void readingMissingFileThrowsException() {
		FileHandler.parseFile(Paths.get("test-failfile.csv"));
	}

	@Test(expected = RuntimeException.class)
	public void writingToMissingDirectoryThrowsException() {
		FileHandler.writeFile(Paths.get("/unknown/unknownfile.csv"), "");
	}

	@Test(expected = RuntimeException.class)
	public void movingMissingFileThrowsException() {
		FileHandler.moveFile(Paths.get("unknownfile.csv"), Paths.get("unknownfiles.csv"));
	}
}

