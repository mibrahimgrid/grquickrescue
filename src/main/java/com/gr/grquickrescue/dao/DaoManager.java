package com.gr.grquickrescue.dao;

import java.util.HashMap;
import java.util.Map;

public class DaoManager {
	private static Map<String, Object> daoMap;
	private static DaoManager instance;

	static {
		try {
			instance = new DaoManager();
		} catch (Exception e) {

		}
	}

	private DaoManager() {
		daoMap = new HashMap<String, Object>();
		daoMap.put(ContractDao.class.getName(), new ContractDaoHibernateImpl());
		daoMap.put(AlertProfileDao.class.getName(), new AlertProfileDaoHibernateImpl());
		daoMap.put(QRLoginDao.class.getName(), new QRLoginDaoHibernateImpl());
		daoMap.put(AddressDao.class.getName(), new AddressDaoHibernateImpl());
		daoMap.put(AccountDao.class.getName(), new AccountDaoHibernateImpl());
		daoMap.put(ContactDao.class.getName(), new ContactDaoHibernateImpl());
	}

	public static Object getInstance(String className) {
		if (instance == null) {
			instance = new DaoManager();
		}

		return daoMap.get(className);
	}
}
