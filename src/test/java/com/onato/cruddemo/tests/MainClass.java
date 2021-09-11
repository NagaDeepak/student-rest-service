package com.onato.cruddemo.tests;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainClass {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(StudentServiceTest.class);

		List<Failure> failures = result.getFailures();
		for (Failure failure : failures) {
			System.out.println(failure.getTrace());
		}
		if(result.wasSuccessful())
			System.out.println(true);
	}
}