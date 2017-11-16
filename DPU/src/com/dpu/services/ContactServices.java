package com.dpu.services;

import java.util.ArrayList;
import java.util.List;

import com.dpu.bean.TruckBean;


public class ContactServices {

	private static List<TruckBean> lstContacts;

	public static void addInitialContacts() {
		lstContacts = new ArrayList<TruckBean>();
		lstContacts.add(new TruckBean(1, "Gagan", "324545"));
		lstContacts.add(new TruckBean(2, "Chandan", "3453453"));
	}

	public static List<TruckBean> getAllContacts() {
		return lstContacts;
	}

	public static List<TruckBean> searchContact(String text) {
		List<TruckBean> lstSearch = new ArrayList<TruckBean>();
		for (int i = 0; i < lstContacts.size(); i++) {
			TruckBean obj = lstContacts.get(i);
			if (obj.getName().toLowerCase().contains(text.toLowerCase())) {
				lstSearch.add(obj);
			}
		}
		return lstSearch;
	}

	public static void addContact(TruckBean obj) {
		lstContacts.add(obj);
	}

	public static void editContact(TruckBean obj) {
		for (int i = 0; i < lstContacts.size(); i++) {
			TruckBean objC = lstContacts.get(i);
			if (objC.getId() == obj.getId()) {
				objC.setName(obj.getName());
				objC.setContact(obj.getContact());
			}
		}
	}

	public static void delContact(int index) {
		for (int i = 0; i < lstContacts.size(); i++) {
			TruckBean objC = lstContacts.get(i);
			if (objC.getId() == index) {
				lstContacts.remove(objC);
			}
		}
	}

}
