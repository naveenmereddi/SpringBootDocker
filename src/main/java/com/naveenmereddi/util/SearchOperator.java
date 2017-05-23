package com.naveenmereddi.util;

public enum SearchOperator {
	EQUALITY, NEGATION;

	public static final String[] OPERATOR_SET = {"=","!" };

	public static SearchOperator getSimpleOperator(final char input) {
		switch(input) {
		case '=':
			return EQUALITY;
		case '!':
			return NEGATION;
		default:
			return null;
		}
	}
}
