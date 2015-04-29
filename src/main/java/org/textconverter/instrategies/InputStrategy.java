package org.textconverter.instrategies;

import java.io.IOException;
import java.util.Scanner;

public interface InputStrategy {
	
	void execute(Scanner inputTextScanner) throws IOException;
	
	int getMaxWordsInSentence();
}
