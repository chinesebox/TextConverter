package org.textconverter.instrategies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class AbstractStrategy {
	
	int maxWordsInSentence = 0;
	
	public void execute(Scanner inputTextScanner) throws IOException {
		convertAndWrite(inputTextScanner);
	}
	
	/**
	 * Writing to text file
	 * 
	 * @param inputTextScanner
	 * @return
	 * @throws IOException
	 */
	public int convertAndWrite(Scanner inputTextScanner) {
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
			write(result);
		}
		return maxWordsInSentence;
	}
	
	public int getMaxWordsInSentence() {
		return maxWordsInSentence;
	}
	
	abstract void write(ArrayList<String> content);

}
