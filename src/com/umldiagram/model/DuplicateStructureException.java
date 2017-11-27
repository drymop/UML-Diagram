package com.umldiagram.model;

public class DuplicateStructureException extends StructureException {
	/**
	 * Package-private constructor
	 * @param name The conflicted name
	 * @param message Error message
	 */
	DuplicateStructureException(String message) {
		super(message);
	}
	
	/**
	 * Package-private constructor
	 * @param name The conflicted name
	 */
	DuplicateStructureException() {
		super();
	}
}
