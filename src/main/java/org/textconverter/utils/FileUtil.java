package org.textconverter.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

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
	
	/**
	 * Writing to tmp file
	 * 
	 * @param inputTextScanner
	 * @return
	 * @throws IOException
	 */
	public static int write(Scanner inputTextScanner) throws IOException {
		inputTextScanner.useDelimiter("\\.");
		String sentence;
		int maxWordsInSentence = 0;
		Pattern pattern = Pattern.compile("(\\W)+");
		PrintWriter pw = getPrintWriter();
		while (inputTextScanner.hasNext()) {
			sentence = inputTextScanner.next();
			String trimmedSentence = sentence.trim();
			if(trimmedSentence.isEmpty()) {
				continue;
			}
			String[] arr = pattern.split(trimmedSentence);
			maxWordsInSentence = arr.length > maxWordsInSentence ? arr.length : maxWordsInSentence;
			ArrayList<String> result = new ArrayList<String>(Arrays.asList(arr));
			Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
			result.stream().forEach((r) -> {
				pw.println(r);
			});
			pw.println();
		}
		pw.close();
		return maxWordsInSentence;
	}
}
