package com.dpu.bean;

import java.io.Serializable;

public class TruckBean implements Serializable {

	private int id;
	private String name, contact;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public TruckBean(String name, String contact) {
		super();
		this.name = name;
		this.contact = contact;
	}

	public TruckBean() {
		super();
	}

	public TruckBean(int id, String name, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

}
