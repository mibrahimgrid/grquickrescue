package com.gr.grquickrescue.utilstest;

import org.junit.Test;

import com.gr.grquickrescue.utils.PasswordUtility;

public class TestPasswordUtil {

	@Test
	public void TestRandomGeneration() 
	{
		
		System.out.println("========================================================================================================");

		for(int i=0; i<20; i++)
		{
			System.out.println(PasswordUtility.generateRandomPassword());
		}
		System.out.println("========================================================================================================");
	}
}
