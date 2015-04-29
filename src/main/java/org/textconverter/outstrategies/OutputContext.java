package org.textconverter.outstrategies;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Context for output strategies
 * 
 * @author ocean
 */
public class OutputContext {
	
	private OutputStrategy outputStrategy;
	
	public OutputContext(OutputStrategy outputStrategy) {
		this.outputStrategy = outputStrategy;
	}

	/**
	 * @param OutputStrategy to set
	 */
	public void setOutputStrategy(OutputStrategy outputStrategy) {
		this.outputStrategy = outputStrategy;
	}

	/**
	 * Generated output
	 */
	public void print(Scanner sc) throws FileNotFoundException {
		outputStrategy.print(sc);
	}
}
