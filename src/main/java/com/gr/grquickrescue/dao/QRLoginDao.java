package com.gr.grquickrescue.dao;

import com.gr.grquickrescue.models.QRLogin;

public interface QRLoginDao extends GenericDao<QRLogin, Integer> {
	public QRLogin findLoginByUsername(String username);

}