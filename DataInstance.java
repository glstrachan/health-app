import java.util.*;

public class DataInstance {
	private Date date;
	private double hours;
	private String activity;
	private Intensity intensity;
	private Quality quality;

	public enum Intensity {
		LOW, MEDIUM, HIGH
	};

	public enum Quality {
		POOR, GOOD, EXCELLENT
	};

	public DataInstance(Date newDate, double newHours, String newActivity, Intensity newIntensity, Quality newQuality) {
		date = newDate;
		hours = newHours;
		activity = newActivity;
		intensity = newIntensity;
		quality = newQuality;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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