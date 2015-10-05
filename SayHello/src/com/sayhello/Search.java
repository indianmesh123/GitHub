package com.sayhello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class Search extends Activity 
{
	LinearLayout lEditActivity,lSearch4,lSearch5,lSearch6,lSearch7,lSearch8,lSearch10;
	ImageView imgBack,imgeditactivity;
	TextView textActivity1,textActivity2,textActivity3,textActivity4,textActivity5,textActivity6;
	View view1,view2,view3,view4,view5,view6;
	private GoogleMap googleMap;
	double latitude;
	static ArrayList<String> arrLatitude=null;
	static ArrayList<String> arrLongitude=null;
	ArrayList<String> arrGetType=null;
	double longitude;
	GPSTracker gps;
	public static final String wurl = "https://api-dev.zyngme.net/sayhello/sayhelloservice/v1/users/match";

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search);		
		imgeditactivity=(ImageView)findViewById(R.id.imgeditactivity);
		lEditActivity=(LinearLayout)findViewById(R.id.lSearch);
		textActivity1=(TextView)findViewById(R.id.textCoffee);
		textActivity2=(TextView)findViewById(R.id.textCoffee1);
		textActivity3=(TextView)findViewById(R.id.textCoffee2);
		textActivity4=(TextView)findViewById(R.id.textCoffee3);
		textActivity5=(TextView)findViewById(R.id.textCoffee4);
		textActivity6=(TextView)findViewById(R.id.textCoffee5);
		lSearch4=(LinearLayout)findViewById(R.id.lSearch4);
		lSearch5=(LinearLayout)findViewById(R.id.lSearch5);
		lSearch6=(LinearLayout)findViewById(R.id.lSearch6);
		lSearch7=(LinearLayout)findViewById(R.id.lSearch7);
		lSearch8=(LinearLayout)findViewById(R.id.lSearch8);
		lSearch10=(LinearLayout)findViewById(R.id.lSearch10);
		
		view1=(View)findViewById(R.id.view1);
		view2=(View)findViewById(R.id.view2);
		view3=(View)findViewById(R.id.view3);
		view4=(View)findViewById(R.id.view4);
		view5=(View)findViewById(R.id.view5);
		view6=(View)findViewById(R.id.view6);
		arrGetType=new ArrayList<String>();
		imgBack=(ImageView)findViewById(R.id.imgBack);
		
		new HttpAsyncTask().execute(wurl);
		gps=new GPSTracker(getApplicationContext());
		arrGetType=EditActivity.arrGetType;
		if(arrGetType.size()==1)
		{
			textActivity1.setText(arrGetType.get(0));
			lSearch5.setVisibility(View.GONE);
			lSearch6.setVisibility(View.GONE);
			lSearch7.setVisibility(View.GONE);
			lSearch8.setVisibility(View.GONE);
			lSearch10.setVisibility(View.GONE);

		}
		if(arrGetType.size()==2)
		{
			textActivity1.setText(arrGetType.get(0));
			textActivity2.setText(arrGetType.get(1));
			lSearch6.setVisibility(View.GONE);
			lSearch7.setVisibility(View.GONE);
			lSearch8.setVisibility(View.GONE);
			lSearch10.setVisibility(View.GONE);
		}
		if(arrGetType.size()==3)
		{
			textActivity1.setText(arrGetType.get(0));
			textActivity2.setText(arrGetType.get(1));
			textActivity3.setText(arrGetType.get(2));
			lSearch7.setVisibility(View.GONE);
			lSearch8.setVisibility(View.GONE);
			lSearch10.setVisibility(View.GONE);

		}
		if(arrGetType.size()==4)
		{
			textActivity1.setText(arrGetType.get(0));
			textActivity2.setText(arrGetType.get(1));
			textActivity3.setText(arrGetType.get(2));
			textActivity4.setText(arrGetType.get(3));
			lSearch8.setVisibility(View.GONE);
			lSearch10.setVisibility(View.GONE);
		}
		if(arrGetType.size()==5)
		{
			textActivity1.setText(arrGetType.get(0));
			textActivity2.setText(arrGetType.get(1));
			textActivity3.setText(arrGetType.get(2));
			textActivity4.setText(arrGetType.get(3));
			textActivity5.setText(arrGetType.get(4));
			lSearch10.setVisibility(View.GONE);

		}
		if(arrGetType.size()==6)
		{
			textActivity1.setText(arrGetType.get(0));
			textActivity2.setText(arrGetType.get(1));
			textActivity3.setText(arrGetType.get(2));
			textActivity4.setText(arrGetType.get(3));
			textActivity5.setText(arrGetType.get(4));
			textActivity6.setText(arrGetType.get(5));

		}
		lSearch4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				
			}
		});
		if(gps.canGetLocation())
		{
			latitude = gps.getLatitude();
			longitude = gps.getLongitude();         
		}
		else
		{   
			final Dialog dialog=new Dialog(Search.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);			
			dialog.setContentView(R.layout.locationdialog);
			Button btnOk=(Button)dialog.findViewById(R.id.btnOkLoc);
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
					gps.showSettingsAlert(Search.this);
					dialog.cancel();
				}
			});
			dialog.show();		
		}
		 int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		 if(status!=ConnectionResult.SUCCESS)
	        { 	 
	            int requestCode = 10;
	            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	            dialog.show(); 
	        }
		 	else
		 	{
		 					MapFragment fm = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
		 					googleMap = fm.getMap();
		 					googleMap.setMyLocationEnabled(true);
		 					googleMap.getUiSettings().setZoomControlsEnabled(false);
		                    googleMap.clear();	 
		                    CircleOptions circleOptions = new CircleOptions();		                    
							googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 13.0f));
		                    LatLng point=new LatLng(latitude,longitude);
		                    circleOptions.center(point);	 
		                    circleOptions.radius(2000);
		                    circleOptions.strokeColor(Color.RED);
		                    circleOptions.fillColor(Color.TRANSPARENT);		 
		                    circleOptions.strokeWidth(2);		 
		                    googleMap.addCircle(circleOptions);
		                    for(int i=0;i<arrLatitude.size();i++)
		                    {
		                    	String lat=arrLatitude.get(i);
		                    	String lon=arrLongitude.get(i);
		                    	drawMarker(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon)));
		                    	
		                    }
		                    
		                }	         
		   
		imgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(Search.this,EditActivityList.class);
				startActivity(intent);
			}
		});		
	}
	@SuppressWarnings("deprecation")
	public static String POST(String url)
	{
		arrLatitude=new ArrayList<String>();
		arrLongitude=new ArrayList<String>();

		InputStream inputStream = null;
		String result = "";
		try
		{		
			HttpClient httpclient = new DefaultHttpClient();		
			HttpGet httpPost = new HttpGet(url);		    
			
			httpPost.setHeader("Content-Type","application/json");	
			httpPost.setHeader("userid","729");			
			HttpResponse httpResponse = httpclient.execute(httpPost);			
			inputStream = httpResponse.getEntity().getContent();

			if(inputStream != null)
			{
				result = convertInputStreamToString(inputStream);
				JSONObject jobj=new JSONObject(result);
				JSONArray jarray=jobj.getJSONArray("listentity");
				for(int i=0;i<jarray.length();i++)
				{
					JSONObject jobj1=jarray.getJSONObject(i);
					String activityType=jobj1.getString("type");
					JSONObject jobj2=jobj1.getJSONObject("location");
					String locId=jobj2.getString("id");
					String latitude=jobj2.getString("latitude");
					String longitude=jobj2.getString("longitude");
					String startTime=jobj1.getString("startTime");
					String endTime=jobj1.getString("endTime");			
					arrLatitude.add(latitude);
					arrLongitude.add(longitude);
				}
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
		ProgressDialog pDialog;
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Search.this);
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
			pDialog.cancel();		
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
	 private void drawMarker(LatLng point){
	        MarkerOptions markerOptions = new MarkerOptions();                    	            
	        markerOptions.position(point);	    
	        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
	        googleMap.addMarker(markerOptions); 
	   
	    }
}