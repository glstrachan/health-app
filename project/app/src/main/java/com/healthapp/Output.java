package com.healthapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.*;

public class Output {
	public static void save(LogData data, Context context) {
		try {
			/* Save the serialized Log Data */
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
		/* Create ths CSV string */
		String csv = "";
		csv += "Date,Hours,Activity,Intensity,Quality\n";

		for (DataInstance data : log.getData()) {
			csv += data.getDate() + "," + data.getHours() + "," + data.getActivity() + ", " +
					data.intensityToString(data.getIntensity()) + ","
					+ data.qualityToString(data.getQuality()) + "\n";
		}

		/* Save the file to memory */
		File path = context.getExternalFilesDir(null);
		File file = new File(path, "health_log.csv");
		try {
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(csv.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
