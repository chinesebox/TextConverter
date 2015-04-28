package org.textconverter.strategy;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.textconverter.utils.FileUtil;

public class CsvStrategyMock extends CsvStrategy {
	
	private PrintWriter pw;

	public CsvStrategyMock(int maxWordsInSentence, PrintWriter pw) throws IOException {
		super(maxWordsInSentence);
		this.pw = pw;
	}
	
	@Override
	protected void println() {
		pw.println();
	}
	
	@Override
	protected void print(String string) {
		pw.print(string);
	}
	
	@Override
	protected void println(String string) {
		pw.println(string);
	}

}
