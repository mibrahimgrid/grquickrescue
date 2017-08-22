package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.AlertProfile;
@Remote
public interface AlertProfileDao {

	public void saveAlertProfile(AlertProfile Entity);

	public void updateAlertProfile(AlertProfile Entity);
	
	public AlertProfile findAlertProfileById(int id);

	public void deleteAlertProfile(AlertProfile Entity);

	public List<AlertProfile> findAllAlertProfiles();
	
	public List<AlertProfile> findAlertProfilesByAccountId(int id);

	public void deleteAllAlertProfiles();
}
