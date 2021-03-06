/*
 * This file is covered by the terms of the Common Public License v1.0.
 *
 * Copyright (c) SZEDER Gábor
 *
 * Parts of this software were developed within the JEOPARD research
 * project, which received funding from the European Union's Seventh
 * Framework Programme under grant agreement No. 216682.
 */

package de.fzi.cjunit.testutils;

public class TestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TestException() { }

	public TestException(String msg) {
		super(msg);
	}

	public TestException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TestException(Throwable t) {
		super(t);
	}
}
