package com.gr.grquickrescue.dao;
import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.QRLogin;

@Remote
public interface QRLoginDao
{
	public void saveLogin(QRLogin login);

	public void updateLogin(QRLogin login);

	public QRLogin findLoginById(int id);

	public QRLogin findLoginByUsername(String username);
	
	public void deleteLogin(QRLogin login);

	public List<QRLogin> findAllLogins();
}