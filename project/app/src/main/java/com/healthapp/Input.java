package com.healthapp;

import java.io.*;

public class Input {
	public LogData recoverSave() {
		LogData log = null;
		try {
			FileInputStream fileIn = new FileInputStream("/saveLog.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			log = (LogData) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
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
