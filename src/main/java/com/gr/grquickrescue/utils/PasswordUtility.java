package com.gr.grquickrescue.utils;

import java.util.Random;

public class PasswordUtility {

	private static Random random  ;
	
	public static String generateRandomPassword() 
	{
		random = new Random();
		StringBuilder pswd = new StringBuilder();
		
		for(int i=0; i<4 ; i++) 
		{
			pswd.append( (char)getRandomInt());
		}
		for(int i=0; i<4 ; i++) 
		{
			pswd.append((char) getRandomChar());
		}
		return pswd.toString();
	}
	private static int getRandomInt() 
	{
		return random.nextInt(9)+48;
	}
	private static int getRandomChar()
	{
		return random.nextInt(25)+97;
	}
}
