package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.dao.QRLoginDao;
import com.gr.grquickrescue.models.QRLogin;

@Stateless
public class QRLoginService implements QRLoginServiceRemote{

	@EJB
	private static QRLoginDao loginDao;

	public QRLoginService()
	{
		loginDao = (QRLoginDao) DaoManager.getInstance(QRLoginDao.class.getName());
	}
	
	@Override
	public void saveLogin(QRLogin login) {
		// TODO Auto-generated method stub
		loginDao.saveLogin(login);
	}

	@Override
	public void updateLogin(QRLogin login) {
		// TODO Auto-generated method stub
		loginDao.updateLogin(login);
	}

	@Override
	public QRLogin findLoginById(int loginId) {
		// TODO Auto-generated method stub
		
		return loginDao.findLoginById(loginId);
	}

	@Override
	public QRLogin findLoginByUsername(String username) {
		// TODO Auto-generated method stub
		return loginDao.findLoginByUsername(username);
	}

	@Override
	public void deleteLogin(int loginId) {
		QRLogin login = loginDao.findLoginById(loginId);
		loginDao.deleteLogin(login);
	}

	@Override
	public List<QRLogin> findAllLogins() {
		// TODO Auto-generated method stub
		return loginDao.findAllLogins();
	}

	
}
