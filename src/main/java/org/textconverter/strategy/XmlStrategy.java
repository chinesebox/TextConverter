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
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		System.out.println("<text>");
		System.out.println("	<sentence>");
		while (sc.hasNextLine()) {
			String word = sc.nextLine();
			if("".equals(word)) {
				if(sc.hasNextLine()) {
					System.out.println("	</sentence>");
					System.out.println("	<sentence>");
				}
				continue;
			}
			System.out.println("		<word>" + word + "</word>");
		}
		System.out.println("	</sentence>");
		System.out.println("</text>");
		sc.close();
	}
}
