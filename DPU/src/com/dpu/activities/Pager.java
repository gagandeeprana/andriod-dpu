//package com.dpu.activities;
//
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//
//public class Pager extends FragmentStatePagerAdapter {
//
//	// integer to count number of tabs
//	int tabCount;
//
//	// Constructor to the class
//	public Pager(android.support.v4.app.FragmentManager fm, int tabCount) {
//		super(fm);
//		// Initializing tab count
//		this.tabCount = tabCount;
//	}
//
//	// Overriding method getItem
//	@Override
//	public android.app.Fragment getItem(int position) {
//		// Returning the current tabs
//		switch (position) {
//		case 0:
//			POActivity tab1 = new POActivity();
//			return tab1;
//		case 1:
//			Tab2 tab2 = new Tab2();
//			return tab2;
//		case 2:
//			Tab3 tab3 = new Tab3();
//			return tab3;
//		default:
//			return null;
//		}
//	}
//
//	// Overriden method getCount to get the number of tabs
//	@Override
//	public int getCount() {
//		return tabCount;
//	}
//}
