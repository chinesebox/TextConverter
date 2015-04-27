package org.textconverter.strategy;

public class OutputFactory {
	
	private int maxWordsInSentence = 0;
	
	public OutputFactory(int maxWordsInSentence) {
		this.maxWordsInSentence = maxWordsInSentence;
	}
	
	public OutputContext getOutput(OutputStrategyType outputStrategyType) {
		switch(outputStrategyType) {
			case XML:
				return new OutputContext(new XmlStrategy());
			case CSV:
				return new OutputContext(new CsvStrategy(maxWordsInSentence));
			default:
				new Exception("No strategy found!");
		}
		return null;
		
	}
}
