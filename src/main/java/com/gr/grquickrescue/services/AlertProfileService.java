package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.gr.grquickrescue.dao.AlertProfileDao;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.AlertProfile;

@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class AlertProfileService implements AlertProfileServiceRemote {

	private static AlertProfileDao alertDao;

	public AlertProfileService() {
		alertDao = (AlertProfileDao) DaoManager.getInstance(AlertProfileDao.class.getName());
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveAlertProfile(AlertProfile entity) {
		try {
			alertDao.saveInstance(entity);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateAlertProfile(AlertProfile entity) {
		try {
			alertDao.updateInstance(entity);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public AlertProfile findAlertProfileById(int alertId) {
		// TODO Auto-generated method stub
		AlertProfile alert = alertDao.findInstanceById(alertId, AlertProfile.class);
		return alert;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAlertProfile(int alertId) {
		AlertProfile alert = alertDao.findInstanceById(alertId, AlertProfile.class);
		try {
			alertDao.deleteInstance(alert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<AlertProfile> findAllAlertProfiles() {
		List<AlertProfile> alerts = alertDao.findAllInstances(AlertProfile.class);
		return alerts;
	}

	@Override
	public List<AlertProfile> findAlertProfilesByAccountId(int id) {
		return alertDao.findAlertProfilesByAccountId(id);
	}
}