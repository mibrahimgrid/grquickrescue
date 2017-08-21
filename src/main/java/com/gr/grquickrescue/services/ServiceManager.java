package com.gr.grquickrescue.services;

import java.util.HashMap;
import java.util.Map;


public class ServiceManager {
	private static Map<String, Object> serviceMap;
	private static ServiceManager instance;

	static {
		try {
			instance = new ServiceManager();
		} catch (Exception e) {

		}
	}

	private ServiceManager() {
		serviceMap = new HashMap<String, Object>();
		serviceMap.put(ContractServiceRemote.class.getName(), new ContractService());
		serviceMap.put(AlertProfileServiceRemote.class.getName(), new AlertProfileService());
		serviceMap.put(QRLoginServiceRemote.class.getName(), new QRLoginService());
		serviceMap.put(AddressServiceRemote.class.getName(), new AddressService());
		serviceMap.put(AccountServiceRemote.class.getName(), new AccountService());
		serviceMap.put(ContactServiceRemote.class.getName(), new ContactService());
	}

	public static Object getInstance(String className) {
		if (instance == null) {
			instance = new ServiceManager();
		}

		return serviceMap.get(className);
	}
}

