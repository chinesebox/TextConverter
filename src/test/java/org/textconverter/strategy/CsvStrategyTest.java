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
import org.junit.experimental.categories.Category;
import org.textconverter.instrategies.InputFactory;
import org.textconverter.instrategies.InputStrategyType;
import org.textconverter.outstrategies.OutputContext;
import org.textconverter.utils.Constants;
import org.textconverter.utils.FileUtil;

@Category(org.textconverter.strategy.CsvStrategyTest.class)
public class CsvStrategyTest {
	
	private InputFactory inputFactory = new InputFactory();
	
    private static String readEntireFile(String filename) throws IOException {
        FileReader in = new FileReader(filename);
        StringBuilder contents = new StringBuilder();
        char[] buffer = new char[4096];
        int read = 0;
        do {
            contents.append(buffer, 0, read);
            read = in.read(buffer);
        } while (read >= 0);
        in.close();
        return contents.toString();
    }
	
	@Test(expected = OutOfMemoryError.class)
	public void readingBigFileMemoryNotSafe() throws IOException {
		readEntireFile("inputTextTest.txt");
	}
	
	@Test
	public void readingBigFileMemorySafe() throws Exception {
		File file = new File("inputTextTest.txt");
		Scanner inputTextScanner = new Scanner(file);
		int max = inputFactory.getInputContext(InputStrategyType.FILE).write(inputTextScanner);
		PrintWriter pw = FileUtil.getPrintWriter("csv.out");
		OutputContext oc = new OutputContext(new CsvStrategyMock(max, pw));
		File inFile = new File(Constants.TMP_FILE);
		Scanner sc = new Scanner(inFile);
		oc.print(sc);
		pw.close();
		sc.close();

		assertTrue(true);
	}
	
	@Test
	public void csvExpectedOutput() throws Exception {
		File file = new File("inputTextTest.txt");
		Scanner inputTextScanner = new Scanner(file);
		int max = inputFactory.getInputContext(InputStrategyType.FILE).write(inputTextScanner);
		PrintWriter pw = FileUtil.getPrintWriter("csv.out");
		OutputContext oc = new OutputContext(new CsvStrategyMock(max, pw));
		File inFile = new File(Constants.TMP_FILE);
		Scanner sc = new Scanner(inFile);
		oc.print(sc);
		pw.close();
		sc.close();
		
		ArrayList<String> sb = new ArrayList<String>();
		sb.add(", Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8");
		sb.add("a, had, lamb, little, Mary");
		sb.add("Aesop, and, called, came, for, Peter, the, wolf");
		sb.add("Cinderella, likes, shoes");
		
		File csvOutFile = new File("csv.out");
		int indx = 0;
		boolean isEqual = true;
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvOutFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if(indx != 0 && indx % 4 != 0) {
		    		continue;
		    	}
				if(!line.equals(((indx % 4) != 0 ? "Sentence " + indx + ", " : "") + sb.get(indx % 4))) {
					isEqual = false;
					break;
				}
				indx++;
		    }
		}
		assertTrue(isEqual);
	}
	
	@Test
	public void xmlExpectedOutput() throws Exception {
		Scanner inputTextScanner = new Scanner("  Mary   had a little  lamb  . " +
				" " +
				" " +
				" Peter   called for the wolf   ,  and Aesop came . " +  
				" Cinderella  likes shoes.. "
				);
		
		inputFactory.getInputContext(InputStrategyType.FILE).write(inputTextScanner);
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
