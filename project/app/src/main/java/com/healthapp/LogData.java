package com.healthapp;

import java.util.*;

public class LogData implements java.io.Serializable {

	private static ArrayList<DataInstance> data = new ArrayList<DataInstance>();

	public LogData() {

	}

	public void addDataInstance(DataInstance newInstance) {
		data.add(newInstance);
	}

	public void removeDataInstance(int i) {
		data.remove(i);
	}

	public ArrayList<DataInstance> getData() {
		return data;
	}

	public int getDataSize() { return data.size() - 1; }

	public DataInstance getDataInstance(int i) { return i >= data.size() ? null : data.get(i); }

	public DataInstance getDataInstance() {
		return data.get(data.size() - 1);
	}
}