package com.sayhello;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Spinner;

public class CountryMaster 
{

	public static final String TAG = "CountryMaster";
	
	private static CountryMaster sInstance = null;
	private Context mContext = null;
	private String[] mCountryList;
	
	private ArrayList<Country> mCountries = new ArrayList<Country>();
	
	private CountryMaster(Context context) {
		mContext = context;
		Resources res = mContext.getResources();
		
		// builds country data from json
		InputStream is = res.openRawResource(R.raw.countries);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
		    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		    int n;
		    while ((n = reader.read(buffer)) != -1) {
		        writer.write(buffer, 0, n);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String jsonString = writer.toString();
		JSONArray json = new JSONArray();
		try {
			json = new JSONArray(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mCountryList = new String[json.length()];
		for (int i = 0; i < json.length(); i++) {
			JSONObject node = json.optJSONObject(i);
			if (node != null) {
				Country country = new Country();
				country.mCountryIso = node.optString("iso");
				country.mDialPrefix = node.optString("tel");
				country.mCountryName = getCountryName(node.optString("iso"));
				mCountries.add(country);
				mCountryList[i] = country.mCountryIso;
			}
		}
	}

	public static CountryMaster getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new CountryMaster(context);
		}
		return sInstance;
	}
	
	public ArrayList<Country> getCountries() {
		return mCountries;
	}
	
	public String[] getCountriesAsArray() {
		return mCountryList;
	}
	
	public Country getCountryByPosition(int position) {
		Country country = mCountries.get(position);
		//Log.d(TAG, country.mCountryIso);
		return country;
	}
	
	public int getCountryPositionByIso(String isoCode) {
		Country country = null;
		int i = 0;
		int n = mCountries.size();
		while (i < n) {
			country = mCountries.get(i);
			if (country.mCountryIso.equals(isoCode)) {
				break;
			}
			i++;
		}
		return i;
	}

	public Country getCountryByIso(String isoCode) {
		Country country = null;
		int i = 0;
		int n = mCountries.size();
		while (i < n) {
			country = mCountries.get(i++);
			if (country.mCountryIso.equals(isoCode)) {
				break;
			}
		}
		return country;
	}
	

	public Country getCountryByPrefix(String telPrefix) {
		Country country = null;
		int i = 0;
		int n = mCountries.size();
		while (i < n) {
			country = mCountries.get(i++);
			if (country.mDialPrefix.equals(telPrefix)) {
				break;
			}
		}
		return country;
	}
	

	@SuppressLint("DefaultLocale")
	public int getCountryFlagImageResource(String isoCode) {
		String imageName = isoCode.trim().toLowerCase();
		int imageResId = mContext.getResources().getIdentifier("drawable/" + imageName, null, mContext.getPackageName());
		return imageResId;
	}
	
	
	public String getDefaultCountryIso() {
		Locale locale = Locale.getDefault();
		return locale.getCountry();
	}
	
	
	private String getCountryName(String isoCode) {
		Locale locale = new Locale(Locale.getDefault().getDisplayLanguage(), isoCode);
        return locale.getDisplayCountry().trim();
	}
	

	public PhoneNumber getPhoneNumber(String phoneNumber) {
		PhoneNumberUtil util = PhoneNumberUtil.getInstance();
		PhoneNumber number = null;
		try {
			number = util.parse(phoneNumber, getDefaultCountryIso());
		} catch (NumberParseException e) {
			e.printStackTrace();
		}
		return number;
	}
	
	public void debug() {
		int i = 0;
		for (Country country : mCountries) {
			String out = String.format(Locale.getDefault(), "[%d] [%s] prefix: %s, name: %s, resId: %d", 
				i++, country.mCountryIso, country.mDialPrefix, country.mCountryName, getCountryFlagImageResource(country.mCountryIso)
			);
			Log.d(TAG, out);
		}
	}
}
