package com.healthapp;

import java.util.*;
import java.util.concurrent.atomic.DoubleAccumulator;

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

	public ArrayList<DataInstance> quickSortEntries(ArrayList<DataInstance> list){
		if(list.size() > 1) {
			DataInstance pivot = list.get(list.size() - 1);
			ArrayList<DataInstance> left = new ArrayList<DataInstance>();
			ArrayList<DataInstance> right = new ArrayList<DataInstance>();
			for (DataInstance d : list) {
				if (d.getDate().compareTo(pivot.getDate()) < 0) {
					left.add(d);
				} else {
					right.add(d);
				}
			}
			ArrayList<DataInstance> result = new ArrayList<DataInstance>();
			result.addAll(quickSortEntries(left));
			result.addAll(quickSortEntries(right));
			return result;
		}
			return list;
	}
}