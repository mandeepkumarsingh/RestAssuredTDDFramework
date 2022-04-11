package com.spotify.oauth2.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	
	@BeforeMethod
	public static void beforeMethods(Method method) {
		
		System.out.println("Test Starting is :-- "+ method.getName());
		System.out.println("Thread ID:-- "+ Thread.currentThread().getId());

		
		
		
	}

}
