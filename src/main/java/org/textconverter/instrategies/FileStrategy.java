package org.textconverter.instrategies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.textconverter.utils.FileUtil;

/**
 * Writing input to text file strategy
 * 
 * @author ocean
 */
public class FileStrategy extends AbstractStrategy implements InputStrategy {
	
	private PrintWriter pw;
	
	@Override
	public void execute(Scanner inputTextScanner) throws IOException {
		convertInput(inputTextScanner);
	}
	
	public void convertInput(Scanner inputTextScanner) throws IOException {
		try {
			pw = FileUtil.getPrintWriter();
			convertAndWrite(inputTextScanner);
		} finally {
			if(pw != null) {
				pw.close();
			}
		}
	}
	
	public void write(ArrayList<String> content) {
		content.stream().forEach((r) -> {
			pw.println(r);
		});
		pw.println();
	}

}
