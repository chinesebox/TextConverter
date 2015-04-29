package org.textconverter.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * File operations utility
 * 
 * @author ocean
 */
public class FileUtil {
	
	/**
	 * Initialize PrintWriter
	 * 
	 * @return
	 * @throws IOException
	 */
	public static PrintWriter getPrintWriter() throws IOException {
		return getPrintWriter(Constants.TMP_FILE);
	}
	
	public static PrintWriter getPrintWriter(String filename) throws IOException {
		File outFile = new File(filename);
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter pWriter = new PrintWriter(fWriter);
		return pWriter;
	}
	
}
