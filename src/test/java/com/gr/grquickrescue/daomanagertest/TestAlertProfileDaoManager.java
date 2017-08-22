package com.gr.grquickrescue.daomanagertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.dao.AlertProfileDao;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.AlertProfile;

public class TestAlertProfileDaoManager {


	private AlertProfileDao alertDao;
	
	@Before
	public void init() 
	{
		alertDao = (AlertProfileDao) DaoManager.getInstance(AlertProfileDao.class.getName());
		
	}
	
	
}
