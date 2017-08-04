package com.gr.grquickrescue.models;


public class Address implements java.io.Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String streetAddress;
	private String city;
	private String province;
	private String country;

	public Address(int id,String StreetAddress, String City, String Province, String Country)
	{
		this.id = id;
		this.streetAddress = StreetAddress;
		this.city = City;
		this.province = Province;
		this.country = Country;
	}

	public Address(){}
	public void ToString()
	{
		System.out.println(id +", "+streetAddress+", "+city+", "+province+", "+country );
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
