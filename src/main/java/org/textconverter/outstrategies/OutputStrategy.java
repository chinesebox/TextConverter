package org.textconverter.outstrategies;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Output strategy interface
 * 
 * @author ocean
 */
public interface OutputStrategy {

	/**
	 * Generated output
	 * 
	 * @throws FileNotFoundException
	 */
	void print(Scanner sc) throws FileNotFoundException;
}
