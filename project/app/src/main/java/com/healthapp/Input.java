package com.healthapp;

import android.content.Context;

import java.io.*;

public class Input {
	public static LogData recoverSave(Context context) {
		LogData log = null;
		try {
			File path = context.getExternalFilesDir(null);
			File file = new File(path, "saveLog.txt");
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			log = (LogData) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("HI");
		} catch (IOException e) {
			log = new LogData("John", "Doe");
			return log;
		} catch (ClassNotFoundException c) {
			//System.out.println("LogData class not found");
			//c.printStackTrace();

			log = new LogData("John", "Doe");
			return log;
		}

		return log;
	}
}
