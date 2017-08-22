package com.gr.grquickrescue.servicestest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.QRLogin;
import com.gr.grquickrescue.services.QRLoginServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.PasswordUtility;

public class TestQRLoginServiceManager {

	private QRLoginServiceRemote remoteQRLoginService;
	
	@Before
	public void init() 
	{
		remoteQRLoginService = (QRLoginServiceRemote) ServiceManager.getInstance(QRLoginServiceRemote.class.getName());
	}
	@Test
	public void TestSaveQRLoginService() 
	{
		QRLogin login = new QRLogin();
		login.setUsername("khan");
		login.setPassword(PasswordUtility.generateRandomPassword());
		remoteQRLoginService.saveLogin(login);
		
		QRLogin testLogin = remoteQRLoginService.findLoginByUsername("khan");
		System.out.println("======================================================================================================================================");
		System.out.println(testLogin.getUsername() +"----"+ testLogin.getPassword());
		System.out.println("======================================================================================================================================");
	}

}
