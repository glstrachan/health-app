package com.healthapp;

import android.content.Context;

import java.io.*;

public class Input {
	public static LogData recoverSave(Context context) {
		LogData log = null;

		try {
			File path = context.getExternalFilesDir(null);
			File file = new File(path, "saveLog.ser");

			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			log = (LogData) in.readObject();

			in.close();
			fileIn.close();
		} catch (IOException e) {

			log = new LogData(e.getMessage(), "Doe");
		} catch (ClassNotFoundException c) {
			log = new LogData("John", "Doe");
		}

		return log;
	}
}
