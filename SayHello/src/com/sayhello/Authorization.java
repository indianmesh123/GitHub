package com.sayhello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.sayhello.adapter.CountryAdapter;
import com.sayhello.internetcheck.ConnectionDetector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings({ "deprecation", "unused" })
public class Authorization extends Activity
{
	String locationAddress;
	ImageView imgLogo;
	static String valid;
	GPSTracker gps;
	double latitude;
	double longitude;
	TextView textCountry;
	static TextView textCode;
	String CountryID="";
	static String CountryZipCode="";
	String REGISTRATION_TIMEOUT="1000";
	static EditText editEnterNumber;
	String value;
	static String phone;
	JSONObject jsonobj;
	static String message;
	ProgressDialog pDialog;
	JSONObject header =null;
	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	String object;
	static String strId;
	public static final String wurl = "https://api-dev.zyngme.net/sayhello/sayhelloservice/v1/user/phone/add";
	ImageView imgAuthArrow,imgNextAuth;
	LinearLayout lAuthArrow,lAuth5;
	Button buttonOne,buttonBack,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine,buttonZero;
	@SuppressWarnings("static-access")
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.authorization);
		textCountry=(TextView)findViewById(R.id.textCountry);
		imgLogo=(ImageView)findViewById(R.id.imgLogoAuth);
		textCode=(TextView)findViewById(R.id.textCode);
		buttonOne=(Button)findViewById(R.id.buttonOne);
		buttonTwo=(Button)findViewById(R.id.buttonTwo);
		buttonThree=(Button)findViewById(R.id.buttonThree);
		buttonFour=(Button)findViewById(R.id.buttonFour);
		buttonFive=(Button)findViewById(R.id.buttonFive);
		buttonSix=(Button)findViewById(R.id.buttonSix);
		buttonSeven=(Button)findViewById(R.id.buttonSeven);
		buttonEight=(Button)findViewById(R.id.buttonEight);
		buttonNine=(Button)findViewById(R.id.buttonNine);
		buttonZero=(Button)findViewById(R.id.buttonZero);
		editEnterNumber=(EditText)findViewById(R.id.editEnterNumber);
		buttonBack=(Button)findViewById(R.id.buttonBack);
		imgAuthArrow=(ImageView)findViewById(R.id.imgAuthArrow);
		lAuthArrow=(LinearLayout)findViewById(R.id.lAuthArrow);
		lAuthArrow.setEnabled(false);
		imgNextAuth=(ImageView)findViewById(R.id.imgNextAuth);
		lAuth5=(LinearLayout)findViewById(R.id.lAuth5);
		CountryMaster cm = CountryMaster.getInstance(Authorization.this);
		final ArrayList<Country> countries = cm.getCountries();
		String countryIsoCode = cm.getDefaultCountryIso();
		Country country = cm.getCountryByIso(countryIsoCode);
		String countryName=country.mCountryName;	
		header=new JSONObject();
		cd = new ConnectionDetector(getApplicationContext());
		editEnterNumber.setInputType(InputType.TYPE_NULL);		
		lAuth5.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(editEnterNumber.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please enter the phone number", Toast.LENGTH_SHORT).show();
				}
				else
				{
					isInternetPresent = cd.isConnectingToInternet();
					if(isInternetPresent)
					{
						new HttpAsyncTask().execute(wurl);
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Please check internet connection", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		buttonBack.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				int length = editEnterNumber.getText().length();
				if (length > 0)
				{
					editEnterNumber.getText().delete(length - 1, length);
				}
			}
		});
		buttonOne.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				editEnterNumber.append("1");
			}
		});
		buttonTwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("2");
			}
		});
		buttonThree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("3");
			}
		});
		buttonFour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("4");
			}
		});
		buttonFive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				editEnterNumber.append("5");
			}
		});
		buttonSix.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("6");
			}
		});
		buttonSeven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("7");
			}
		});
		buttonEight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("8");
			}
		});
		buttonNine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("9");
			}
		});
		buttonZero.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				editEnterNumber.append("0");
			}
		});
	
		gps=new GPSTracker(getApplicationContext());
		if(gps.canGetLocation())
		{

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();         
		}
		else
		{           
			gps.showSettingsAlert(Authorization.this);
		}
		GeocodingLocation gLocation=new GeocodingLocation();
		gLocation.getAddressFromLocation(latitude, longitude, getApplicationContext(), new GeocoderHandler());
		TelephonyManager tm = (TelephonyManager)getSystemService(getApplicationContext().TELEPHONY_SERVICE);
		String countryCode = tm.getNetworkCountryIso();
		TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);	
		CountryID= manager.getSimCountryIso().toUpperCase();
		String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
		for(int i=0;i<rl.length;i++)
		{
			String[] g=rl[i].split(",");
			if(g[1].trim().equals(CountryID.trim()))
			{
				CountryZipCode=g[0];
				break;  
			}
		}       
		textCode.setText("+"+""+CountryZipCode);
		if(locationAddress==null || locationAddress.equalsIgnoreCase("null"))
		{
		lAuthArrow.setEnabled(true);
		lAuthArrow.setOnClickListener(new OnClickListener() 
		{
		
			@Override
			public void onClick(View arg0) 
			{
				final Dialog dialog=new Dialog(Authorization.this);
				dialog.setTitle("Select Country");
				dialog.setContentView(R.layout.listcountries);
				ListView listCountries=(ListView)dialog.findViewById(R.id.lCountries);
				CountryAdapter cAdap=new CountryAdapter(getApplicationContext(),countries);
				listCountries.setAdapter(cAdap);
				listCountries.setOnItemClickListener(new OnItemClickListener()
				{
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3)
					{
						CountryMaster cm = CountryMaster.getInstance(Authorization.this);
						 Country country = cm.getCountryByPosition(arg2);
						 textCountry.setText(country.mCountryIso);
						 textCode.setText("+"+""+country.mDialPrefix);
						 dialog.dismiss();
					}
				});
				dialog.show();
			}
		});
		}
	}	
	@SuppressLint("HandlerLeak")
	private class GeocoderHandler extends Handler 
	{
		@Override
		public void handleMessage(Message message)
		{
			
			switch (message.what)
			{
			case 1:
				Bundle bundle = message.getData();
				locationAddress = bundle.getString("address");
				if(locationAddress==null || locationAddress.equalsIgnoreCase("null"))
				{
					textCountry.setText("USA");
				}
				else 
				{
					textCountry.setText(locationAddress);
				}
				break;
			default:
				locationAddress = null;
			}
			SharedPreferences.Editor editor = getSharedPreferences("mypref", MODE_PRIVATE).edit();
			editor.putString("locationAddress",locationAddress);
			editor.putString("CountryZipCode", CountryZipCode);
			editor.commit();
		}
	}
	public static String POST(String url)
	{
		InputStream inputStream = null;
		String result = "";
		try
		{		
			phone="+"+textCode.getText().toString()+""+editEnterNumber.getText().toString();
			HttpClient httpclient = new DefaultHttpClient();		
			HttpPost httpPost = new HttpPost(url);		    
			String json = "";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("phoneNumber",phone);
			jsonObject.put("os", "ANDROID");
			jsonObject.put("type","PHONE");			    
			json = jsonObject.toString();
			StringEntity se = new StringEntity(json);		
			httpPost.setEntity(se);
			httpPost.setHeader("Content-Type","application/json");		    
			HttpResponse httpResponse = httpclient.execute(httpPost);			
			inputStream = httpResponse.getEntity().getContent();

			if(inputStream != null)
			{
				result = convertInputStreamToString(inputStream);
				JSONObject jobj=new JSONObject(result);
				message=jobj.getString("message");
				 valid=jobj.getString("valid");
				JSONObject jobj1=jobj.getJSONObject("object");
				strId=jobj1.getString("id");
			}
			else
			{
				result = "Did not work!";
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			Log.d("InputStream of Response", e.getLocalizedMessage());
		}	
		return result;
	}
	private class HttpAsyncTask extends AsyncTask<String, Void, String> 
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Authorization.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... urls)
		{
			return POST(urls[0]);
		}
		@Override
		protected void onPostExecute(String result)
		{
			if(valid.equalsIgnoreCase("true"))
			{
			Intent intent=new Intent(Authorization.this,AuthorizationCode.class);
			intent.putExtra("phoneNumber",phone);
			startActivity(intent);
			SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
			editor.putString("id",strId);
			editor.commit();
			pDialog.dismiss();
			}
			else
			{
				pDialog.cancel();
				final Dialog dialog=new Dialog(Authorization.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);			
				dialog.setContentView(R.layout.phonesupported);
				Button btnOk=(Button)dialog.findViewById(R.id.btnOk);
				Button btnCancel=(Button)dialog.findViewById(R.id.btnCancel);
				btnCancel.setOnClickListener(new OnClickListener() 
				{					
					@Override
					public void onClick(View arg0) 
					{
						dialog.cancel();
					}
				});
				btnOk.setOnClickListener(new OnClickListener() 
				{					
					@Override
					public void onClick(View arg0) 
					{
						dialog.cancel();
					}
				});

				dialog.show();
			}
		}
	}
	private static String convertInputStreamToString(InputStream inputStream) throws IOException
	{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
			result += line;
		inputStream.close();
		return result;       
	}
}






