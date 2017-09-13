package com.gr.grquickrescue.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.gr.grquickrescue.models.QRLogin;

public class QRLoginDaoHibernateImpl extends GenericDaoHibernateImpl<QRLogin, Integer> implements QRLoginDao {
	@Override
	public QRLogin findLoginByUsername(String username) {
		openCurrentSession();
		Criteria cr = getCurrentSession().createCriteria(QRLogin.class).add(Restrictions.eq("username", username));
		QRLogin login = (QRLogin) cr.uniqueResult();
		return login;
	}
}