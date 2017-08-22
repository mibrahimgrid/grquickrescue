package com.gr.grquickrescue.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.gr.grquickrescue.dao.AlertProfileDao;
import com.gr.grquickrescue.dao.AlertProfileDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.AlertProfile;
@Stateless
public class AlertProfileService implements AlertProfileServiceRemote{

	@EJB
	private static AlertProfileDao alertDao;
	
	public AlertProfileService() 
	{
		alertDao = (AlertProfileDaoHibernateImpl)DaoManager.getInstance(AlertProfileDao.class.getName());
	}
	@Override
	public void saveAlertProfile(AlertProfile entity) {
		// TODO Auto-generated method stub
		alertDao.saveAlertProfile(entity);
	}

	@Override
	public void updateAlertProfile(AlertProfile entity) {
		// TODO Auto-generated method stub
		alertDao.updateAlertProfile(entity);
	}

	@Override
	public AlertProfile findAlertProfileById(int alertId) {
		// TODO Auto-generated method stub
		AlertProfile alert = alertDao.findAlertProfileById(alertId);
		return alert;
	}

	@Override
	public void deleteAlertProfile(int alertId) {
		// TODO Auto-generated method stub
		AlertProfile alert = alertDao.findAlertProfileById(alertId);
		alertDao.deleteAlertProfile(alert);
	}

	@Override
	public List<AlertProfile> findAllAlertProfiles() {
		// TODO Auto-generated method stub
		List<AlertProfile> alerts = alertDao.findAllAlertProfiles();
		return alerts;
	}
	@Override
	public List<AlertProfile> findAlertProfilesByAccountId(int id)
	{
		return alertDao.findAlertProfilesByAccountId(id);
	}
	@Override
	public void deleteAllAlertProfiles() {
		// TODO Auto-generated method stub
		alertDao.deleteAllAlertProfiles();
	}

}
