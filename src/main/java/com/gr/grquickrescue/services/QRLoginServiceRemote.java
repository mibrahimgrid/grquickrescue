package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.QRLogin;
@Remote
public interface QRLoginServiceRemote {

	public void saveLogin(QRLogin login);
	public void updateLogin(QRLogin login);
	public QRLogin findLoginById(int loginId);
	public QRLogin findLoginByUsername(String username);
	public void deleteLogin(int loginId);
	public List<QRLogin> findAllLogins();
}
