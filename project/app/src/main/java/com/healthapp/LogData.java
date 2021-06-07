package com.healthapp;

import java.util.*;

public class LogData implements java.io.Serializable {
	private String firstName;
	private String lastName;
	private ArrayList<DataInstance> data = new ArrayList<DataInstance>();

	public LogData(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void addDataInstance(DataInstance newInstance) {
		data.add(newInstance);
	}

	public void removeDataInstance(int i) {
		data.remove(i);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public ArrayList<DataInstance> getData() {
		return data;
	}

	public DataInstance getDataInstance(int i) {
		return data.get(i);
	}

	public DataInstance getDataInstance() {
		return data.get(data.size() - 1);
	}
}