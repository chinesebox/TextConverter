package org.textconverter.instrategies;


public class InputFactory {

	public InputContext getInputContext(InputStrategyType type) throws Exception {
		switch(type) {
		case FILE:
			return new InputContext(new FileStrategy());
		case DB:
			return new InputContext(new DBStrategy());
		default:
			throw new Exception("No option valaibe");
		}
	}

}
