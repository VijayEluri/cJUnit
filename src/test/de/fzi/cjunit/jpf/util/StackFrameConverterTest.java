/*
 * This file is covered by the terms of the Common Public License v1.0.
 *
 * Copyright (c) SZEDER Gábor
 *
 * Parts of this software were developed within the JEOPARD research
 * project, which received funding from the European Union's Seventh
 * Framework Programme under grant agreement No. 216682.
 */

package de.fzi.cjunit.jpf.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


import static de.fzi.cjunit.jpf.util.StackFrameConverter.*;

public class StackFrameConverterTest {

	@Test
	public void sourceFileBasenameStripDirs() {
		String filename = "Object.java";
		assertThat(sourceFileBasename("java/lang/" + filename),
				equalTo(filename));
	}

	@Test
	public void sourceFileBasenameNothingToStrip() {
		String magicFilename = "<direct call>";
		assertThat(sourceFileBasename(magicFilename),
				equalTo(magicFilename));
	}
}
