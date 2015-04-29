package org.textconverter.instrategies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.textconverter.utils.FileUtil;

/**
 * Writing input to text file strategy
 * 
 * @author ocean
 */
public class FileStrategy implements InputStrategy {
	
	private int maxWordsInSentence = 0;

	@Override
	public int write(Scanner inputTextScanner) throws IOException {
		return convertInput(inputTextScanner);
	}
	
	public int convertInput(Scanner inputTextScanner) throws IOException {
		PrintWriter pw = null;
		int maxWordsInSentence = 0;
		try {
			pw = FileUtil.getPrintWriter();
			maxWordsInSentence = convertAndWrite(inputTextScanner, pw);
		} finally {
			if(pw != null) {
				pw.close();
			}
		}
		return maxWordsInSentence;
	}
	
	/**
	 * Writing to text file
	 * 
	 * @param inputTextScanner
	 * @return
	 * @throws IOException
	 */
	public int convertAndWrite(Scanner inputTextScanner, PrintWriter pw) {
		inputTextScanner.useDelimiter("\\.");
		String sentence;
		Pattern pattern = Pattern.compile("#");
		
		while (inputTextScanner.hasNext()) {
			sentence = inputTextScanner.next();
			String trimmedSentence = sentence.replaceFirst("(\\W)+", "").replaceAll("(\\W)+", "#");
			if(trimmedSentence.isEmpty()) {
				continue;
			}
			String[] arr = pattern.split(trimmedSentence);
			maxWordsInSentence = arr.length > maxWordsInSentence ? arr.length : maxWordsInSentence;
			ArrayList<String> result = new ArrayList<String>(Arrays.asList(arr));
			Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
			writeToFile(result, pw);
		}
		return maxWordsInSentence;
	}
	
	private void writeToFile(ArrayList<String> content, PrintWriter pw) {
		content.stream().forEach((r) -> {
			pw.println(r);
		});
		pw.println();
	}

}
