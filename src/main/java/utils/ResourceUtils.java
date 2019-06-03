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
	 * @return the file content as string
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
	 * @return the file content as string
	 */
	public static String loadResourceFileAsString(String resourceName) {
		String resource = ClassLoader.getSystemResource(resourceName).getFile();
		File file = new File(resource);
		return loadFileString(file);
	}
	
	/**
	 * Returns the default resource directory
	 * @return the resource dir as File
	 */
	public static File getDefaultResourceDirectory() {
	    String resource =  ClassLoader.getSystemResource("\\").getFile();
	    return new File(resource).getParentFile();
	}
}
