package org.textconverter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.textconverter.instrategies.InputFactory;
import org.textconverter.instrategies.InputStrategyType;
import org.textconverter.outstrategies.OutputFacade;
import org.textconverter.outstrategies.OutputStrategyType;
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
		System.out.println();
		
		printOutput(OutputStrategyType.getStrategyType(in), inputTextScanner);
		
		strategyTypeScanner.close();
		inputTextScanner.close();
		
		System.out.println();
		System.out.println("END");
	}

	private static void printOutput(OutputStrategyType outputStrategyType, Scanner inputTextScanner) throws Exception {
		InputFactory inFactory = new InputFactory();
		int maxWordsInSentence = inFactory.getInputContext(InputStrategyType.FILE).write(inputTextScanner);
		OutputFacade outputFacade = new OutputFacade(maxWordsInSentence);
		File inFile = new File(Constants.TMP_FILE);
		Scanner sc = new Scanner(inFile);
		outputFacade.print(outputStrategyType, sc);
		sc.close();
	}

}
