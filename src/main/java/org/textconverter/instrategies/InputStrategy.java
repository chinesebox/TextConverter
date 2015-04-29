package org.textconverter.instrategies;

import java.io.IOException;
import java.util.Scanner;

public interface InputStrategy {
	int write(Scanner inputTextScanner) throws IOException;
}
