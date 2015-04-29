package org.textconverter.outstrategies;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class OutputFacade {
	
	private OutputContext outputContext;
	
	private int maxWordsInSentence;
	
	public OutputFacade(int maxWordsInSentence) {
		this.maxWordsInSentence = maxWordsInSentence;
	}
	
	public void printXML(Scanner sc) throws FileNotFoundException {
		outputContext = new OutputContext(new XmlStrategy());
		outputContext.print(sc);
	}
	
	public void printCSV(Scanner sc) throws FileNotFoundException {
		outputContext = new OutputContext(new CsvStrategy(maxWordsInSentence));
		outputContext.print(sc);
	}
	
	public void print(OutputStrategyType type, Scanner sc) throws Exception {
		switch(type) {
		case XML:
			printXML(sc);
			break;
		case CSV:
			printCSV(sc);
			break;
		default:
			throw new Exception("No option valaibe");
		}
	}
}
