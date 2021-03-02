package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

public class PinEverythingTest {
	PrintStream existingOutputStream;

	@Before
	public void setUp() {
		existingOutputStream = System.out;
	}

	@After
	public void tearDown() {
		System.setOut(existingOutputStream);
	}

	@Test
	public void pinEverythingTest() {
		ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteArrayInputStream);
		System.setOut(printStream);

		IntStream.range(28172, 28200).forEach(seed -> GameRunner.main(new String [] {String.valueOf(seed)}));
		GameRunner.main(new String [] {"2000"});

		Approvals.verify(byteArrayInputStream.toString());
	}
}
