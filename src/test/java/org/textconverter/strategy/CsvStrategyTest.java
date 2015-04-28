package org.textconverter.strategy;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.textconverter.utils.Constants;
import org.textconverter.utils.FileUtil;

public class CsvStrategyTest {
	
	@Test
	public void test1() throws IOException {
		Scanner inputTextScanner = new Scanner("  Mary   had a little  lamb  . " +
				" " +
				" " +
				" Peter   called for the wolf   ,  and Aesop came . " +  
				" Cinderella  likes shoes.. "
				);
		
		int max = FileUtil.write(inputTextScanner);
		PrintWriter pw = FileUtil.getPrintWriter("csv.out");
		OutputContext oc = new OutputContext(new CsvStrategyMock(max, pw));
		File inFile = new File(Constants.TMP_FILE);
		Scanner sc = new Scanner(inFile);
		oc.print(sc);
		pw.close();
		sc.close();
		
		ArrayList<String> sb = new ArrayList<String>();
		sb.add(", Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8");
		sb.add("Sentence 1, a, had, lamb, little, Mary");
		sb.add("Sentence 2, Aesop, and, called, came, for, Peter, the, wolf");
		sb.add("Sentence 3, Cinderella, likes, shoes");
		
		File csvOutFile = new File("csv.out");
		int indx = 0;
		boolean isEqual = true;
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvOutFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				if(!line.equals(sb.get(indx))) {
					isEqual = false;
					break;
				}
				indx++;
		    }
		}

		assertTrue(isEqual);
	}
	
	@Test
	public void test3() throws IOException {
		Scanner inputTextScanner = new Scanner("  Mary   had a little  lamb  . " +
				" " +
				" " +
				" Peter   called for the wolf   ,  and Aesop came . " +  
				" Cinderella  likes shoes.. "
				);
		
		int max = FileUtil.write(inputTextScanner);
		PrintWriter pw = FileUtil.getPrintWriter("xml.out");
		OutputContext oc = new OutputContext(new XmlStrategyMock(pw));
		File inFile = new File(Constants.TMP_FILE);
		Scanner sc = new Scanner(inFile);
		oc.print(sc);
		pw.close();
		sc.close();
		
		ArrayList<String> sb = new ArrayList<String>();
		sb.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		sb.add("<text>");
		sb.add("\t<sentence>");
		sb.add("\t\t<word>a</word>");
		sb.add("\t\t<word>had</word>");
		sb.add("\t\t<word>lamb</word>");
		sb.add("\t\t<word>little</word>");
		sb.add("\t\t<word>Mary</word>");
		sb.add("\t</sentence>");
		sb.add("\t<sentence>");
		sb.add("\t\t<word>Aesop</word>");
		sb.add("\t\t<word>and</word>");
		sb.add("\t\t<word>called</word>");
		sb.add("\t\t<word>came</word>");
		sb.add("\t\t<word>for</word>");
		sb.add("\t\t<word>Peter</word>");
		sb.add("\t\t<word>the</word>");
		sb.add("\t\t<word>wolf</word>");
		sb.add("\t</sentence>");
		sb.add("\t<sentence>");
		sb.add("\t\t<word>Cinderella</word>");
		sb.add("\t\t<word>likes</word>");
		sb.add("\t\t<word>shoes</word>");
		sb.add("\t</sentence>");
		sb.add("</text>");
		
		File xmlOutFile = new File("xml.out");
		int indx = 0;
		boolean isEqual = true;
		
		try (BufferedReader br = new BufferedReader(new FileReader(xmlOutFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				if(!line.equals(sb.get(indx))) {
					isEqual = false;
					break;
				}
				indx++;
		    }
		}

		assertTrue(isEqual);
	}
}
