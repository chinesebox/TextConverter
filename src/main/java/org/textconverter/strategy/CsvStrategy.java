package org.textconverter.strategy;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.textconverter.utils.Constants;

/**
 * Converting tmp file to CSV
 * 
 * @author ocean
 */
public class CsvStrategy implements OutputStrategy {
	
	private int maxWordsInSentence = 0;
	
	public CsvStrategy(int maxWordsInSentence) {
		this.maxWordsInSentence = maxWordsInSentence;
	}

	/**
	 * Converting tmp file to CSV
	 * 
	 * @throws FileNotFoundException
	 */
	public void print(Scanner sc) throws FileNotFoundException {
		for(int i = 0; i < maxWordsInSentence; i++) {
			print(Constants.CSV_DELIMITER + " Word " + (i + 1));
		}
		int sentenceCount = 0;
		println();
		print("Sentence " + ++sentenceCount);
		while (sc.hasNextLine()) {
			String word = sc.nextLine();
			if("".equals(word)) {
				if(sc.hasNextLine()) {
					println();
					print("Sentence " + ++sentenceCount);
				}
				continue;
			}
			print(Constants.CSV_DELIMITER + " " + word);
		}
	}
	
	protected void println() {
		System.out.println();
	}
	
	protected void print(String string) {
		System.out.print(string);
	}
	
	protected void println(String string) {
		System.out.println(string);
	}
	
}
