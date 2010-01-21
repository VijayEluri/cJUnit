/*
 * This file is covered by the terms of the Common Public License v1.0.
 *
 * Copyright (c) SZEDER Gábor
 *
 * Parts of this software were developed within the JEOPARD research
 * project, which received funding from the European Union's Seventh
 * Framework Programme under grant agreement No. 216682.
 */

package de.fzi.cjunit.runners.statements;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import org.junit.runners.model.FrameworkMethod;

import de.fzi.cjunit.runners.model.ConcurrentFrameworkMethod;
import de.fzi.cjunit.testutils.TestException;

public class ConcurrentStatementTest {

	@Test
	public void getExceptionClassName() throws Throwable {
		assertThat(new ConcurrentStatement().getExceptionClassName(
					RuntimeException.class),
				equalTo(RuntimeException.class.getName()));
	}

	@Test
	public void getExceptionClassNameWithNullArgument() {
		assertThat(new ConcurrentStatement().getExceptionClassName(null),
				equalTo(""));
	}

	@Test
	public void createJPFArgs() throws Throwable {
		ConcurrentStatement statement = new ConcurrentStatement(
				new ConcurrentFrameworkMethod(
					String.class.getMethod("toString")),
				new String());
		statement.befores.add(new FrameworkMethod(
					String.class.getMethod("hashCode")));
		statement.befores.add(new FrameworkMethod(
					String.class.getMethod("notify")));
		statement.afters.add(new FrameworkMethod(
					String.class.getMethod("notifyAll")));
		statement.afters.add(new FrameworkMethod(
					String.class.getMethod("wait")));
		statement.expectException(TestException.class);

		String[] args = statement.createJPFArgs();

		assertThat(args, hasItemInArray(
				"de.fzi.cjunit.jpf.inside.TestWrapper"));
		assertThat(args, hasItemInArray(
				"--testclass=java.lang.String"));
		assertThat(args, hasItemInArray("--test=method=toString,exception=de.fzi.cjunit.testutils.TestException"));
		assertThat(args, hasItemInArray("--beforemethod=hashCode"));
		assertThat(args, hasItemInArray("--beforemethod=notify"));
		assertThat(args, hasItemInArray("--aftermethod=notifyAll"));
		assertThat(args, hasItemInArray("--aftermethod=wait"));
	}

	// This also covers the case when the same exception is thrown in the
	// test method as expected: invokeJPF() does not throw in that case.
	@Test
	public void testNotThrowsWhenNotExpecting() throws Throwable {
		ConcurrentStatement s = new ConcurrentStatement(null, null) {
			protected void invokeJPF() throws Throwable { }
		};
		s.evaluate();
	}

	// This test also covers other cases:
	//  * the test does not throw an exception while an exception is
	//    expected
	//  * the test throws a different exception type than expected
	// Both of these cases boil down to the test below: we expect the
	// same exception as the one thrown in invokeJPF().
	@Test(expected=TestException.class)
	public void testThrowsWhenNotExpecting() throws Throwable {
		ConcurrentStatement s = new ConcurrentStatement(null, null) {
			protected void invokeJPF() throws Throwable {
				throw new TestException();
			}
		};
		s.evaluate();
	}
}
