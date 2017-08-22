package com.gr.grquickrescue.services;

import java.util.List;
import javax.ejb.Remote;
import com.gr.grquickrescue.models.AlertProfile;
@Remote
public interface AlertProfileServiceRemote {
	
	public void saveAlertProfile(AlertProfile entity);
	public void updateAlertProfile(AlertProfile entity);
	public AlertProfile findAlertProfileById(int alertId);
	public void deleteAlertProfile(int alertId);
	public List<AlertProfile> findAllAlertProfiles();
	public List<AlertProfile> findAlertProfilesByAccountId(int id);
	public void deleteAllAlertProfiles();
}
