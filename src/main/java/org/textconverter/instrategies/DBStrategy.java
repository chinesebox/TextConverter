package org.textconverter.instrategies;

import java.io.IOException;
import java.util.Scanner;

public class DBStrategy implements InputStrategy {

	@Override
	public void execute(Scanner inputTextScanner) throws IOException {
		throw new RuntimeException("Method is not implemented!");
	}

	@Override
	public int getMaxWordsInSentence() {
		throw new RuntimeException("Method is not implemented!");
	}

}
