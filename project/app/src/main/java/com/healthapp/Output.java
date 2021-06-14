package com.healthapp;

import android.content.Context;
import android.util.Log;

import java.io.*;

public class Output {
	public static void save(LogData data, Context context) {
		try {
			File path = context.getExternalFilesDir(null);
			File file = new File(path, "saveLog.txt");
			// Save the file idk if this works with Android
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					context.openFileOutput("saveLog.ser", Context.MODE_PRIVATE));
			outputStreamWriter.write(String.valueOf(data));
			outputStreamWriter.close();
		}
		catch (IOException e) {
			Log.e("Exception", "File write failed: " + e.toString());
		}*/
	}

	public static void exportLog(LogData log) {
		String csv = "";
		csv += "Name, " + log.getFirstName() + " " + log.getLastName() + "\n\n";
		csv += "Date, Hours, Activity, Intensity, Quality\n";

		for (DataInstance data : log.getData()) {
			csv += data.getDate() + ", " + data.getHours() + ", " + data.getActivity() + ", " +
					data.intensityToString(data.getIntensity()) + ", "
					+ data.qualityToString(data.getQuality()) + "\n";
		}

		// Save the file to memory
		FileWriter outputStream = null;

		try {
			outputStream = new FileWriter("healthlog.txt");
			outputStream.write(csv);
		} catch(IOException e) {

		}
	}
}