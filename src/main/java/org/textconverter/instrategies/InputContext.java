package org.textconverter.instrategies;

import java.io.IOException;
import java.util.Scanner;

public class InputContext {
	
	private InputStrategy inputStrategy;
	
	public InputContext(InputStrategy inputStrategy) {
		this.inputStrategy = inputStrategy;
	}
	
	/**
	 * @param InputStrategy to set
	 */
	public void setInputStrategy(InputStrategy inputStrategy) {
		this.inputStrategy = inputStrategy;
	}

	/**
	 * Generated input
	 * @throws IOException 
	 */
	public int write(Scanner sc) throws IOException {
		return inputStrategy.write(sc);
	}
}
