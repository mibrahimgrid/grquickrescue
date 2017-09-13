package com.gr.grquickrescue.models;

public class MapMarker {
	private double lat;
	private double lng;
	private String title;

	public MapMarker(double lt, double ln, String title) {
		this.setLat(lt);
		this.setLng(ln);
		this.setTitle(title);
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
