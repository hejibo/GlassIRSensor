package com.mrk1869.irsensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public class IRSensorManager {

	private Boolean isRunning;
	private Thread mThread;
	private IRSensorListener mListener = null;

	private float getIRSensorData() {
		try {
			Process process = Runtime.getRuntime().exec(
					"cat /sys/bus/i2c/devices/4-0035/proxraw");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			int read;
			char[] buffer = new char[8];
			StringBuffer output = new StringBuffer();
			while ((read = reader.read(buffer)) > 0) {
				output.append(buffer, 0, read);
			}
			reader.close();
			float value = Float.valueOf(output.toString());
			process = null;
			reader = null;
			buffer = null;
			output = null;
			return value;
		} catch (IOException e) {
			// permission error
			Log.v("IRSensorManager", "Permission error!");
			return -1.0f;
		}
	}

	public boolean isInstallationFinished() {
		float res = getIRSensorData();
		if (res == -1.0f) {
			return false;
		}
		return true;
	}

	public void setListener(IRSensorListener listener) {
		this.mListener = listener;

		mThread = new Thread() {
			@Override
			public void run() {
				while (isRunning) {
					try {
						float irData = getIRSensorData();
						if (irData > 0.0f) {
							mListener.recievedIRSensorValue(irData);
						}
					} catch (Exception e) {
						Log.v("IRSensorManager", "IRSensor has some errors..");
					}
				}
			}
		};
		mThread.start();
		isRunning = true;
	}

	public void removeListener() {
		isRunning = false;
		mThread.interrupt();
		this.mListener = null;
	}

}
