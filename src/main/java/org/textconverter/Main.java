package org.textconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	private static final String CSV_DELIMITER = ",";
	
	private static final String TMP_FILE = "output.txt";

	public static void main(String[] args) throws IOException, Exception {
		System.out.println("Enter output strategy: ");
		System.out.println("[1] XML: ");
		System.out.println("[2] CSV: ");

		Scanner strategyTypeScanner = new Scanner(System.in);
		String in = "";
		while (strategyTypeScanner.hasNext() && ((in = strategyTypeScanner.next()).equals("1") && in.equals("2"))) {
		}
		
		System.out.println("Use CTRL + Z to close input");
		System.out.println("Enter text: ");
		Scanner inputTextScanner = new Scanner(System.in);
		
		int max = write(inputTextScanner);
		
		strategyTypeScanner.close();
		inputTextScanner.close();

		switch (in) {
		case "1": {
			printXML();
			break;
		}
		case "2": {
			printCSV(max);
			break;
		}
		default:
			throw new Exception("No option valaibe");
		}
			
		System.out.println();
		System.out.println("END");

	}

	/**
	 * Initialize PrintWriter
	 * 
	 * @return
	 * @throws IOException
	 */
	private static PrintWriter getPrintWriter() throws IOException {
		File outFile = new File(TMP_FILE);
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
	private static int write(Scanner inputTextScanner) throws IOException {
		inputTextScanner.useDelimiter("\\.");
		String sentence;
		int max = 0;
		Pattern pattern = Pattern.compile("(\\W)+");
		PrintWriter pw = getPrintWriter();
		while (inputTextScanner.hasNext()) {
			sentence = inputTextScanner.next();
			String trimmedSentence = sentence.trim();
			if(trimmedSentence.isEmpty()) {
				continue;
			}
			String[] arr = pattern.split(trimmedSentence);
			max = arr.length > 0 ? arr.length : max;
			ArrayList<String> result = new ArrayList<String>(Arrays.asList(arr));
			Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
			result.stream().forEach((r) -> {
				pw.println(r);
			});
			pw.println();
		}
		pw.close();
		
		return max;
	}
	
	/**
	 * Converting tmp file to XML
	 * 
	 * @throws FileNotFoundException
	 */
	private static void printXML() throws FileNotFoundException {
		File inFile = new File(TMP_FILE);
		Scanner sc = new Scanner(inFile);
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		System.out.println("<text>");
		System.out.println("	<sentence>");
		while (sc.hasNextLine()) {
			String word = sc.nextLine();
			if("".equals(word)) {
				if(sc.hasNextLine()) {
					System.out.println("	</sentence>");
					System.out.println("	<sentence>");
				}
				continue;
			}
			System.out.println("		<word>" + word + "</word>");
		}
		System.out.println("	</sentence>");
		System.out.println("</text>");
		sc.close();
	}

	/**
	 * Converting tmp file to CSV
	 * 
	 * @param max
	 * @throws FileNotFoundException
	 */
	private static void printCSV(int max) throws FileNotFoundException {
		File inFile = new File(TMP_FILE);
		Scanner sc = new Scanner(inFile);
		System.out.println();
		for(int i = 0; i < max; i++) {
			System.out.print(CSV_DELIMITER + " Word " + (i + 1));
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
			System.out.print(CSV_DELIMITER + " " + word);
		}
		sc.close();
	}

}
