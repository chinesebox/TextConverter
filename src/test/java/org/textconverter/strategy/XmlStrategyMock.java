package org.textconverter.strategy;

import java.io.IOException;
import java.io.PrintWriter;

import org.textconverter.outstrategies.XmlStrategy;

public class XmlStrategyMock extends XmlStrategy {
	
	private PrintWriter pw;

	public XmlStrategyMock(PrintWriter pw) throws IOException {
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

