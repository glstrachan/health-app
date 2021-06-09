package com.healthapp;

import java.io.*;

public class Output {
	public void save(LogData data) {
		try {
			// Save the file idk if this works with Android
			FileOutputStream fileOut = new FileOutputStream("/saveLog.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportLog(LogData log) {
		String csv = "";
		csv += "Name, " + log.getFirstName() + " " + log.getLastName() + "\n\n";
		csv += "Date, Hours, Activity, Intensity, Quality\n";

		for (DataInstance data : log.getData()) {
			csv += data.getDate() + ", " + data.getHours() + ", " + data.getActivity() + ", " + data.getIntensity() + ", "
					+ data.getQuality() + "\n";
		}

		// Save the file to memory
	}
}