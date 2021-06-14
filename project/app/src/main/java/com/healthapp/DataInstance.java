package com.healthapp;

import java.util.*;

public class DataInstance implements java.io.Serializable{
	private String date;
	private double hours;
	private String activity;
	private Intensity intensity;
	private Quality quality;

	public enum Intensity {
		LOW, MEDIUM, HIGH
	};

	String[] stringIntensity = { "low", "medium", "high" };

	public String intensityToString(Intensity intensity) {
		return stringIntensity[intensity.ordinal()];
	}

	public enum Quality {
		POOR, GOOD, EXCELLENT
	};

	String[] stringQuality = { "poor", "good", "high" };

	public String qualityToString(Quality quality) {
		return stringQuality[quality.ordinal()];
	}

	public DataInstance(String date, double hours, String activity, Intensity intensity, Quality quality) {
		this.date = date;
		this.hours = hours;
		this.activity = activity;
		this.intensity = intensity;
		this.quality = quality;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public Intensity getIntensity() {
		return intensity;
	}

	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}

}