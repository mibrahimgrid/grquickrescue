package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.dao.QRLoginDao;
import com.gr.grquickrescue.models.QRLogin;

@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class QRLoginService implements QRLoginServiceRemote {

	private static QRLoginDao loginDao;

	public QRLoginService() {
		loginDao = (QRLoginDao) DaoManager.getInstance(QRLoginDao.class.getName());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveLogin(QRLogin login) {
		try {
			loginDao.saveInstance(login);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateLogin(QRLogin login) {
		try {
			loginDao.updateInstance(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public QRLogin findLoginById(int loginId) {
		// TODO Auto-generated method stub

		return loginDao.findInstanceById(loginId, QRLogin.class);
	}

	@Override
	public QRLogin findLoginByUsername(String username) {
		// TODO Auto-generated method stub
		return loginDao.findLoginByUsername(username);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteLogin(int loginId) {
		try {
			QRLogin login = loginDao.findInstanceById(loginId, QRLogin.class);
			loginDao.deleteInstance(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<QRLogin> findAllLogins() {
		return loginDao.findAllInstances(QRLogin.class);
	}

}