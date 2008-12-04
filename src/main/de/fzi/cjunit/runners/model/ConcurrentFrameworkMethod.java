/*
 * This file is covered by the terms of the Common Public License v1.0.
 *
 * Copyright (c) SZEDER Gábor
 *
 * Parts of this software were developed within the JEOPARD research
 * project, which received funding from the European Union's Seventh
 * Framework Programme under grant agreement No. 216682.
 */

package de.fzi.cjunit.runners.model;

import java.lang.reflect.Method;

import org.junit.runners.model.FrameworkMethod;

public class ConcurrentFrameworkMethod extends FrameworkMethod {

	public ConcurrentFrameworkMethod(Method method) {
		super(method);
	}
}
