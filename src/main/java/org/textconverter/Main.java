package org.textconverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.textconverter.strategy.OutputFacade;
import org.textconverter.strategy.OutputStrategyType;
import org.textconverter.utils.Constants;

public class Main {



	public static void main(String[] args) throws IOException, Exception {
		System.out.println("Enter output strategy: ");
		System.out.println("[1] XML");
		System.out.println("[2] CSV");

		Scanner strategyTypeScanner = new Scanner(System.in);
		int in = 0;
		while (strategyTypeScanner.hasNext() && ((in = strategyTypeScanner.nextInt()) == 1) && in == 2) {
		}
		
		System.out.println("Use CTRL + Z to close input stream");
		System.out.println("Enter text: ");
		Scanner inputTextScanner = new Scanner(System.in);
		
		int max = write(inputTextScanner);
		
		strategyTypeScanner.close();
		inputTextScanner.close();
		
		OutputFacade outputFacade = new OutputFacade(max);
		File inFile = new File(Constants.TMP_FILE);
		Scanner sc = new Scanner(inFile);
		outputFacade.print(OutputStrategyType.getStrategyType(in), sc);
			
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
		File outFile = new File(Constants.TMP_FILE);
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

}
