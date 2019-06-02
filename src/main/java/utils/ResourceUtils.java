package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

public class ResourceUtils {
	/**
	 * Loads a file as string
	 * 
	 * @param file
	 * @return
	 */
	public static String loadFileString(File file) {
		try {
			return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			Logger.getLogger(ResourceUtils.class.getName()).log(Level.SEVERE,
					"Cannot open file: " + file.getAbsolutePath(), ex);
		}

		return null;
	}

	/**
	 * Loads a file from resources as string
	 * 
	 * @param resourceName
	 * @return
	 */
	public static String loadResourceFileAsString(String resourceName) {
		String resource = ClassLoader.getSystemResource(resourceName).getFile();
		File file = new File(resource);
		return loadFileString(file);
	}

	public static void main(String[] args) {

		String content = loadResourceFileAsString("employeesData.csv");
		String[] lines = content.split("\n");

		int i = 0;
		for (String string : lines) {
			System.out.println(i++ + " " + string);
		}
	}
}
