package com.gr.grquickrescue.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.gr.grquickrescue.models.AlertProfile;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AlertProfileServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

@ManagedBean
@SessionScoped
public class AlertProfileController extends AlertProfile{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private  AlertProfileServiceRemote alertService;
	@EJB
	private AccountServiceRemote accountService;
	private List<AlertProfile> alertsList;
	private boolean renderDiv;
	@PostConstruct
	public void init() 
	{
		renderDiv = false;
		alertService = (AlertProfileServiceRemote)ServiceManager.getInstance(AlertProfileServiceRemote.class.getName());
		accountService = (AccountServiceRemote)ServiceManager.getInstance(AccountServiceRemote.class.getName());
		//updateAlertProfilesList();
		
	}
	public void openNewAlertDiv() {
		renderDiv = true;
	}
	public void closeNewAlertDiv() {
		renderDiv = false;
		emptyDiv();
	}
	public void emptyDiv() {
		this.setName("");
		this.setCity("");
		this.setCountry("");
	}
	public String openAccountAlertProfiles(int accountId) 
	{
		this.setAccount(accountService.findAccountById(accountId));
		updateAlertProfilesList();
		return "/resources/secured/alertprofile/alertProfile";
	}
	public void updateAlertProfilesList() 
	{
		setAlertsList(alertService.findAlertProfilesByAccountId(this.getAccount().getId()));
	}
	public void addAlertProfile()
	{
		AlertProfile newAlert = new AlertProfile();
		newAlert.setName(this.getName());
		newAlert.setCity(this.getCity());
		newAlert.setCountry(this.getCountry());
		newAlert.setAccount(this.getAccount());
		alertService.saveAlertProfile(newAlert);
		closeNewAlertDiv();
		updateAlertProfilesList();
		
	}
	public String makeAlertProfileEditable(AlertProfile alert) 
	{
		alert.setEditable(true);
		return null;
	}
	public void deleteAlertProfile(AlertProfile alert) 
	{
		alertService.deleteAlertProfile(alert.getId());
		updateAlertProfilesList();
	}
	public String saveEdit(AlertProfile alert1) 
	{
		for (AlertProfile alert : alertsList) {
			alert.setEditable(false);
		}
		alertService.updateAlertProfile(alert1);
		updateAlertProfilesList();
		return null;
	}
	public void cancelEdit(AlertProfile alert) 
	{
		alert.setEditable(false);
	}
	public List<AlertProfile> getAlertsList() {
		return alertsList;
	}
	public void setAlertsList(List<AlertProfile> alertsList) {
		this.alertsList = alertsList;
	}
	public boolean isRenderDiv() {
		return renderDiv;
	}
	public void setRenderDiv(boolean renderDiv) {
		this.renderDiv = renderDiv;
	}
}
