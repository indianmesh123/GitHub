package com.sayhello.gettersetter;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class GetActivityList
{
	String getDescription;
	String getType;
	String getStartTime;
	String getEndTime;
	ArrayList<String> arrGetDays;
	Bitmap imgActivity;
	public Bitmap getImgActivity()
	{
		return imgActivity;
	}
	public void setImgActivity(Bitmap imgActivity) {
		this.imgActivity = imgActivity;
	}
	public String getGetDescription() {
		return getDescription;
	}
	public void setGetDescription(String getDescription) {
		this.getDescription = getDescription;
	}
	public String getGetType() {
		return getType;
	}
	public void setGetType(String getType) {
		this.getType = getType;
	}
	public String getGetStartTime() {
		return getStartTime;
	}
	public void setGetStartTime(String getStartTime) {
		this.getStartTime = getStartTime;
	}
	public String getGetEndTime() {
		return getEndTime;
	}
	public void setGetEndTime(String getEndTime) {
		this.getEndTime = getEndTime;
	}
	public ArrayList<String> getArrGetDays() {
		return arrGetDays;
	}
	public void setArrGetDays(ArrayList<String> arrGetDays) {
		this.arrGetDays = arrGetDays;
	}
}
