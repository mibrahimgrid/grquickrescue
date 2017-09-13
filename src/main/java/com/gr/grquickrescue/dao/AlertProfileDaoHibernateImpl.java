package com.gr.grquickrescue.dao;


import java.util.List;
import com.gr.grquickrescue.models.AlertProfile;

public class AlertProfileDaoHibernateImpl extends GenericDaoHibernateImpl<AlertProfile, Integer> implements AlertProfileDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<AlertProfile> findAlertProfilesByAccountId(Integer id)
	{
		openCurrentSession();
		List<AlertProfile> accountAlerts = (List<AlertProfile>)getCurrentSession().createQuery("from AlertProfile where accountIDFK = "+id).list(); 
		closeCurrentSession();
		return accountAlerts;
	}
}