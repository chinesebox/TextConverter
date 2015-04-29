package org.textconverter.outstrategies;

public enum OutputStrategyType {
	
	XML(1), CSV(2);
	
	private int value;
	
	OutputStrategyType(int value) {
		this.value = value;
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	public static OutputStrategyType getStrategyType(int value) throws Exception {
		for(OutputStrategyType outputStrategyType : values()) {
			if(outputStrategyType.getValue() == value) {
				return outputStrategyType;
			}
		}
		throw new Exception("No value " + value + " found!");
	}
}
