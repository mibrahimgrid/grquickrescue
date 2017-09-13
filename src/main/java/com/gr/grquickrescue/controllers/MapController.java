package com.gr.grquickrescue.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.MapMarker;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.HttpSessionUtility;

@ManagedBean
@RequestScoped
public class MapController{

	@EJB
	private AddressServiceRemote addressService;

	private List<Address> addresses;
	private List<MapMarker> markers;

	@PostConstruct
	public void init() {
		addressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		addresses = addressService.findAllAddresses();
		markers = addressService.findMarkersByAccountId((Integer)HttpSessionUtility.getSession().getAttribute("MapAccountId"));
	}

	public String showContactHomesOnMap(int accountId) {
		
		return NavigationController.goToMaps(true);
	}

	
	 public List<Address> getAddresses() { return addresses; } 
	 public void setAddresses(List<Address> addresses) { this.addresses = addresses; }
	 
	public List<MapMarker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<MapMarker> markers) {
		this.markers = markers;
	}

}
