package uy.com.jatrik.services;

import uy.com.jatrik.data.DataProvider;

public class Service {

	protected DataProvider dataProvider;
	
	public Service() {
		dataProvider = DataProvider.getInstance();
		int a = 3;
	}
}
