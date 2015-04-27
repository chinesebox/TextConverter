package org.textconverter.strategy;

import java.io.FileNotFoundException;
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
		System.out.println();
		for(int i = 0; i < maxWordsInSentence; i++) {
			System.out.print(Constants.CSV_DELIMITER + " Word " + (i + 1));
		}
		int sentenceCount = 0;
		System.out.println();
		System.out.print("SENTENCE " + ++sentenceCount);
		while (sc.hasNextLine()) {
			String word = sc.nextLine();
			if("".equals(word)) {
				if(sc.hasNextLine()) {
					System.out.println();
					System.out.print("SENTENCE " + ++sentenceCount);
				}
				continue;
			}
			System.out.print(Constants.CSV_DELIMITER + " " + word);
		}
		sc.close();
	}
	
}
