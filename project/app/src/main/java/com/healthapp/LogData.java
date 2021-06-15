package com.healthapp;

import java.util.*;
import java.util.concurrent.atomic.DoubleAccumulator;

/* Implement serializeable for easy read write access */
public class LogData implements java.io.Serializable {

	private static ArrayList<DataInstance> data = new ArrayList<DataInstance>();

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

	public DataInstance getDataInstance(int i) { return i >= data.size() ? null : i < 0 ? null : data.get(i); }

	/* Used for sorting Log Data before saving */
	
	public void quickSortEntries()
	{
		data = sortHelper(data);
	}
	
	public ArrayList<DataInstance> sortHelper(ArrayList<DataInstance> list){
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
			result.addAll(sortHelper(left));
			result.addAll(sortHelper(right));
			return result;
		}
			return list;
	}
}
