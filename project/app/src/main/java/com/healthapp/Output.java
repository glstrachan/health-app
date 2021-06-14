package com.healthapp;

import android.content.Context;
import android.util.Log;

import java.io.*;

public class Output {
	public static void save(LogData data, Context context) {
		try {
			//Save the serialized Log Data
			File path = context.getExternalFilesDir(null);
			File file = new File(path, "saveLog.ser");

			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exportLog(LogData log, Context context) {
		String csv = "";
		// Name functionality not yet implemented
		//csv += "Name, " + log.getFirstName() + " " + log.getLastName() + "\n\n";
		csv += "Date, Hours, Activity, Intensity, Quality\n";

		for (DataInstance data : log.getData()) {
			csv += data.getDate() + ", " + data.getHours() + ", " + data.getActivity() + ", " +
					data.intensityToString(data.getIntensity()) + ", "
					+ data.qualityToString(data.getQuality()) + "\n";
		}

		// Save the file to memory
		File path = context.getExternalFilesDir(null);
		File file = new File(path, "health_log.csv");

		FileOutputStream stream = new FileOutputStream(file);
		try {
		    stream.write(csv.getBytes());
		} finally {
		    stream.close();
		}
	}
}
