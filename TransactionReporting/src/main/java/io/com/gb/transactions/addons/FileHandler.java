/**********************************UTILY OR REUSABLE CLASS*********************************************
 * FileHandler is generic class designed to perform all file actions such as read, write and archive. 
 * Following are the features
 * 1. ListFiles => To List files at particular path or location
 * 2. ReadFile => To parse file line by line
 * 3. WriteFile => To write contents to a file
 * 4. MoveFile => To move a file from source folder to a target folder
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions.addons;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileHandler {
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	public static List<Path> listFiles(Path path) {
		log.info("Loading transaction files from directory {}", path);
		
		try (Stream<Path> paths = Files.walk(path)) {
			return paths.filter(Files::isRegularFile).collect(toList());
		}
		catch(IOException ex) {
			throw new RuntimeException("Problem while reading file " + path, ex);
		}
	}
	
	public static List<String> parseFile(Path path) {
		log.info("Parsing lines from file at {}", path);
		try (Stream<String> lines = Files.lines(path)) {
			return lines.collect(toList());
		}
		catch(IOException ex) {
			throw new RuntimeException("Problem while reading file " + path, ex);
		}
	}
	
	public static void writeFile(Path path, String input) {
		log.info("Writing to file at {}", path);
		try {
			Files.write(path, input.getBytes());
		}
		catch(IOException ex) {
			throw new RuntimeException("Problem while writing file " + path, ex);
		}
	}
	
	public static void moveFile(Path src, Path tgt) {
		log.info("Moving the file to processed folder {}", tgt);
		try {
			Files.move(src, tgt, REPLACE_EXISTING);
		}
		catch(IOException ex) {
			throw new RuntimeException("Problem while moving file to processed folder " + tgt);
		}
	}
}
