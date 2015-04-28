package org.textconverter.strategy;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Converting tmp file to XML
 * 
 * @author ocean
 */
public class XmlStrategy implements OutputStrategy {
	
	/**
	 * Converting tmp file to XML
	 * 
	 * @throws FileNotFoundException
	 */
	public void print(Scanner sc) throws FileNotFoundException {
		println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		println("<text>");
		println("\t<sentence>");
		while (sc.hasNextLine()) {
			String word = sc.nextLine();
			if("".equals(word)) {
				if(sc.hasNextLine()) {
					println("\t</sentence>");
					println("\t<sentence>");
				}
				continue;
			}
			println("\t\t<word>" + word + "</word>");
		}
		println("\t</sentence>");
		println("</text>");
	}
	
	protected void print(String string) {
		System.out.print(string);
	}
	
	protected void println() {
		System.out.println();
	}
	
	protected void println(String string) {
		System.out.println(string);
	}
}
