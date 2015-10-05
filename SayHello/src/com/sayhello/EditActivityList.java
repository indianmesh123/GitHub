package com.sayhello;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sayhello.adapter.MyActivityAdapter;
import com.sayhello.gettersetter.GetActivityList;
import com.sayhello.gettersetter.MyActivityList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class EditActivityList extends Activity {
	RelativeLayout lEditActivity;
	public static ListView listEditActivity;
	MyActivityAdapter adap;
	int val = 0;
	ArrayList<MyActivityList> myList = null;
	ArrayList<GetActivityList> arrMyActivityList = null;
	ImageView imgCheck, imgBack;
	ArrayList<Integer> arrIntValue = null;
	String description;
	ImageView imageView1;
	public static ArrayList<String> arrDaysList = null;
	ArrayList<String> arrWeekDays = new ArrayList<String>();
	int MONDAY = 1, TUESDAY = 2, WEDNESDAY = 3, THURSDAY = 4, FRIDAY = 5,
			SATURDAY = 6;
	int drawable;
	String weekDay;
	public static ArrayList<String> arrList = null;
	String keyEnd = null, keyStart = null;
	MyActivityList mya;
	int g = 0;
	String imageSide;
	ImageView imgSide;

	@SuppressWarnings("unchecked")
	@SuppressLint("CommitPrefEdits")
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.editactivitylist);
		lEditActivity = (RelativeLayout) findViewById(R.id.lEditActivityList);
		listEditActivity = (ListView) findViewById(R.id.listEditActivity);
		arrIntValue = new ArrayList<Integer>();
		arrDaysList = new ArrayList<String>();
		imgCheck = (ImageView) findViewById(R.id.imgCheck);
		imgBack = (ImageView) findViewById(R.id.imgBack);
		imgSide = (ImageView) findViewById(R.id.imgSide);
		arrList = new ArrayList<String>();
		arrMyActivityList = new ArrayList<GetActivityList>();
		SharedPreferences prefs = getSharedPreferences("MyPref",Context.MODE_PRIVATE);
		try 
		{
			arrMyActivityList = (ArrayList<GetActivityList>) ObjectSerializer
					.deserialize(prefs.getString("activity", ObjectSerializer
							.serialize(new ArrayList<GetActivityList>())));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		arrWeekDays.add("MONDAY");
		arrWeekDays.add("TUESDAY");
		arrWeekDays.add("WEBDNESDAY");
		arrWeekDays.add("THURSDAY");
		arrWeekDays.add("FRIDAY");
		arrWeekDays.add("SATURDAY");
		arrWeekDays.add("SUNDAY");
		arrWeekDays.set(0, "MONDAY");
		arrWeekDays.set(1, "TUESDAY");
		arrWeekDays.set(2, "WEDNESDAY");
		arrWeekDays.set(3, "THURSDAY");
		arrWeekDays.set(4, "FRIDAY");
		arrWeekDays.set(5, "SATURDAY");
		arrWeekDays.set(6, "SUNDAY");
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
		Calendar calendar = Calendar.getInstance();
		weekDay = dayFormat.format(calendar.getTime());
		SharedPreferences settings = getSharedPreferences("MyPref", 0);
		imageSide = settings.getString("image", "");
		Bitmap bitmap1 = decodeBase64(imageSide);
		imgSide.setImageBitmap(bitmap1);
		myList = new ArrayList<MyActivityList>();
		for (int i = 0; i < EditActivity.arrGetList.size(); i++) 
		{
			mya = new MyActivityList();
			mya.setTextDescription(EditActivity.arrGetList.get(i)
					.getGetDescription());
			mya.setImgActivity(EditActivity.arrGetList.get(i).getImgActivity());
			arrDaysList = EditActivity.arrGetList.get(i).getArrGetDays();
			mya.setTextEndTime(EditActivity.arrGetList.get(i).getGetEndTime());
			mya.setTextStartTime(EditActivity.arrGetList.get(i).getGetStartTime());
			SortWeekDays sWeek = new SortWeekDays();
			Collections.sort(arrDaysList, sWeek);
			getWeekList();
			myList.add(mya);
		}
		adap = new MyActivityAdapter(getApplicationContext(), myList);
		listEditActivity.setAdapter(adap);
		imgBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EditActivityList.this,
						EditActivity.class);
				startActivity(intent);
			}
		});
		imgCheck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EditActivityList.this,
						TabLayoutActivity.class);
				startActivity(intent);
			}
		});	
	}

	class SortWeekDays implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			int ai = arrWeekDays.size();
			int bi = arrWeekDays.size();
			for (int i = 0; i < arrWeekDays.size(); i++) {
				if (arrWeekDays.get(i).equalsIgnoreCase(a))
					ai = i;
				if (arrWeekDays.get(i).equalsIgnoreCase(b))
					bi = i;
			}
			return ai - bi;
		}
	}

	public static Bitmap decodeBase64(String input) {
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

	public void getWeekList1()
	{
		for (int i = 0; i < arrDaysList.size(); i++) 
		{
			if (arrDaysList.get(i).equals("MONDAY"))
			{
				arrIntValue.add(1);
			}
			if (arrDaysList.get(i).equals("TUESDAY"))
			{
				arrIntValue.add(2);
			}
			if (arrDaysList.get(i).equals("WEDNESDAY"))
			{
				arrIntValue.add(3);
			}
			if (arrDaysList.get(i).equals("THURSDAY"))
			{
				arrIntValue.add(4);
			}
			if (arrDaysList.get(i).equals("FRIDAY")) {
				arrIntValue.add(5);
			}
			if (arrDaysList.get(i).equals("SATURDAY")) 
			{
				arrIntValue.add(6);
			}
			if (arrDaysList.get(i).equals("SUNDAY"))
			{
				arrIntValue.add(7);
			}
		}
		ArrayList<Integer> arrCdays = new ArrayList<Integer>();
		ArrayList<Integer> arrCday = new ArrayList<Integer>();
		for (int j = 0; j < arrIntValue.size(); j++) {
			if (j != arrIntValue.size() - 1)

			{
				int y = arrIntValue.get(j + 1);
				if ((y - arrIntValue.get(j)) == 1)
				{
					arrCdays.add(j + 1);
				}
				else 
				{
					arrCday.add(j);
				}
			}
		}
	}
	public void getWeekList() {

		ArrayList<HashMap<String, String>> day = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> daymap = null;
		int s = 0;
		for (int i = 0; i < arrDaysList.size(); i++) {
			daymap = new HashMap<String, String>();
			for (int j = 0; j < arrWeekDays.size() - 1; j++) {
				if (arrDaysList.get(i).equals(arrWeekDays.get(j))) {
					if (g > 0) {
						if (s + 1 == j) {
							s = j;
							switch (day.size()) {
							case 1:
								daymap.put("end", arrDaysList.get(i));
								break;
							case 2:
								day.remove(1);
								daymap.put("end", arrDaysList.get(i));
								break;
							default:
								daymap.put("end", arrDaysList.get(i));
								break;
							}
						} else {
							daymap.put("start", arrDaysList.get(i));
							g++;
							s = j;
						}
					} else {
						daymap.put("start", arrDaysList.get(i));
						g++;
						s = j;
					}
					break;
				}
			}
			day.add(daymap);
		}
		for (HashMap<String, String> hashMap : day) {
			for (Map.Entry<String, String> entry : hashMap.entrySet()) {
				String key = entry.getKey();
				if (key.contains("start")) {
					keyStart = entry.getValue();
					arrList.add("start");
				}
				if (key.contains("end")) {
					keyEnd = entry.getValue();
					arrList.add("end");
				}

			}
		}
		for (int i = 0; i < arrList.size(); i++) {
			if (arrList.contains("end")) {
				if (keyStart.equalsIgnoreCase("MONDAY")) {
					if (weekDay.equalsIgnoreCase(keyStart)) {
						mya.setTextStartDay("Today");
					} else {
						mya.setTextStartDay("Mon");

					}
				}
				if (keyStart.equalsIgnoreCase("TUESDAY")) {
					if (weekDay.equalsIgnoreCase(keyStart)) {
						mya.setTextStartDay("Today");
					} else {
						mya.setTextStartDay("Tue");
					}
				}
				if (keyStart.equalsIgnoreCase("WEDNESDAY")) {
					if (weekDay.equalsIgnoreCase(keyStart)) {
						mya.setTextStartDay("Today");
					} else {
						mya.setTextStartDay("Wed");
					}
				}
				if (keyStart.equalsIgnoreCase("THURSDAY")) {
					if (weekDay.equalsIgnoreCase(keyStart)) {
						mya.setTextStartDay("Today");
					} else {
						mya.setTextStartDay("Thu");

					}
				}
				if (keyStart.equalsIgnoreCase("FRIDAY")) {
					if (weekDay.equalsIgnoreCase(keyStart)) {
						mya.setTextStartDay("Today");
					} else {
						mya.setTextStartDay("Fri");
					}
				}
				if (keyStart.equalsIgnoreCase("SATURDAY")) {
					if (weekDay.equalsIgnoreCase(keyStart)) {
						mya.setTextStartDay("Today");
					} else {
						mya.setTextStartDay("Sat");
					}
				}
				if (keyStart.equalsIgnoreCase("SUNDAY")) {
					if (weekDay.equalsIgnoreCase(keyStart)) {
						mya.setTextStartDay("Today");
					} else {
						mya.setTextStartDay("Sun");

					}
				}
				if (keyEnd.equalsIgnoreCase("MONDAY")) {
					if (weekDay.equalsIgnoreCase(keyEnd)) {
						mya.setTextEndDay("Today");
					} else {
						mya.setTextEndDay("Mon");
					}
				}
				if (keyEnd.equalsIgnoreCase("TUESDAY")) {
					if (weekDay.equalsIgnoreCase(keyEnd)) {
						mya.setTextEndDay("Today");
					} else {
						mya.setTextEndDay("Tue");
					}
				}
				if (keyEnd.equalsIgnoreCase("WEDNESDAY")) {
					if (weekDay.equalsIgnoreCase(keyEnd)) {
						mya.setTextEndDay("Today");
					} else {
						mya.setTextEndDay("Wed");
					}
				}
				if (keyEnd.equalsIgnoreCase("THURSDAY")) {
					if (weekDay.equalsIgnoreCase(keyEnd)) {
						mya.setTextEndDay("Today");
					} else {
						mya.setTextEndDay("Thu");
					}
				}
				if (keyEnd.equalsIgnoreCase("FRIDAY")) {
					if (weekDay.equalsIgnoreCase(keyEnd)) {
						mya.setTextEndDay("Today");
					}
					{
						mya.setTextEndDay("Fri");
					}
				}
				if (keyEnd.equalsIgnoreCase("SATURDAY")) {
					if (weekDay.equalsIgnoreCase(keyEnd)) {
						mya.setTextEndDay("Today");
					} else {
						mya.setTextEndDay("Sat");
					}
				}
				if (keyEnd.equalsIgnoreCase("SUNDAY")) {
					if (weekDay.equalsIgnoreCase(keyEnd)) {
						mya.setTextEndDay("Today");
					} else {
						mya.setTextEndDay("Sun");
					}
				}

			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
	}
}
