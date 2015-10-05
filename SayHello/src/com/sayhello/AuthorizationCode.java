package com.sayhello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.sayhello.internetcheck.ConnectionDetector;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AuthorizationCode extends Activity
{
	ImageView imgNext,imgCode1,imgCode2,imgCode3,imgCode4;
	static String code;	
	ArrayList<String> arrCode=null;
	static String message;
	ImageView imgLogoAuthCode;
	static ProgressDialog pDialog;
	static String result;
	LinearLayout lAuthCode5;
	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	static String strCode="";
	static String phoneNumber;
	public static final String PREFS_NAME = "MyPref";
	static String url="https://api-dev.zyngme.net/sayhello/sayhelloservice/v1/user/phone/validate";
	Button buttonOne,buttonBack,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine,buttonZero,buttonCBack;
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.authorizationcode);
		imgLogoAuthCode=(ImageView)findViewById(R.id.imgLogoAuthCode);
		pDialog=new ProgressDialog(AuthorizationCode.this);
		imgNext=(ImageView)findViewById(R.id.imgNext);
		lAuthCode5=(LinearLayout)findViewById(R.id.lAuthCode5);
		buttonOne=(Button)findViewById(R.id.buttonCOne);
		buttonTwo=(Button)findViewById(R.id.buttonCTwo);
		buttonThree=(Button)findViewById(R.id.buttonCThree);
		buttonFour=(Button)findViewById(R.id.buttonCFour);
		buttonFive=(Button)findViewById(R.id.buttonCFive);
		buttonSix=(Button)findViewById(R.id.buttonCSix);
		buttonSeven=(Button)findViewById(R.id.buttonCSeven);
		buttonEight=(Button)findViewById(R.id.buttonCEight);
		buttonNine=(Button)findViewById(R.id.buttonCNine);
		buttonZero=(Button)findViewById(R.id.buttonCZero);
		buttonBack=(Button)findViewById(R.id.buttonCBack);
		imgCode1=(ImageView)findViewById(R.id.imgCode1);
		imgCode2=(ImageView)findViewById(R.id.imgCode2);
		imgCode3=(ImageView)findViewById(R.id.imgCode3);
		imgCode4=(ImageView)findViewById(R.id.imgCode4);
		cd = new ConnectionDetector(getApplicationContext());
		arrCode=new ArrayList<String>();
		Intent intent=getIntent();
		phoneNumber=intent.getStringExtra("phoneNumber");
		isInternetPresent = cd.isConnectingToInternet();		
	
		buttonOne.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				String one="1";
				if(strCode.length()<4)
				{
					strCode=strCode+one;
				}
				arrCode.add(one);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonTwo.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0)
			{
				String two="2";
				if(strCode.length()<4)
				{
					strCode=strCode+two;
				}
				arrCode.add(two);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonThree.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0)
			{
				
				String three="3";
				if(strCode.length()<4)
				{
					strCode=strCode+three;
				}
				arrCode.add(three);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonFour.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				String four="4";
				if(strCode.length()<4)
				{
					strCode=strCode+four;
				}
				arrCode.add(four);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonFive.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) 
			{
				String five="5";
				if(strCode.length()<4)
				{
					strCode=strCode+five;
				}
			
				arrCode.add(five);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonSix.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) 
			{
				String six="6";
				if(strCode.length()<4)
				{
					strCode=strCode+six;
				}

				arrCode.add(six);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonSeven.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				String seven="7";
				if(strCode.length()<4)
				{
					strCode=strCode+seven;
				}
				arrCode.add(seven);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonEight.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				String eight="8";
				if(strCode.length()<4)
				{
					strCode=strCode+eight;
				}

				arrCode.add(eight);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonNine.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				String nine="9";
				if(strCode.length()<4)
				{
					strCode=strCode+nine;
				}

				arrCode.add(nine);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonZero.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{				
				String zero="0";
				if(strCode.length()<4)
				{
					strCode=strCode+zero;
				}
				arrCode.add(zero);
				if(arrCode.size()==1)
				{
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==2)
				{
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==3)
				{
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
				if(arrCode.size()==4)
				{
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse_onclick);
				}
			}
		});
		buttonBack.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if(arrCode.size()==1)
				{
					arrCode.remove(0);
					imgCode1.setBackgroundResource(R.drawable.btn_ellipse);					
				}
				if(arrCode.size()==2)
				{
					arrCode.remove(1);
					imgCode2.setBackgroundResource(R.drawable.btn_ellipse);

				}
				if(arrCode.size()==3)
				{
					arrCode.remove(2);
					imgCode3.setBackgroundResource(R.drawable.btn_ellipse);

				}
				if(arrCode.size()>=4)
				{
					arrCode.remove(3);
					imgCode4.setBackgroundResource(R.drawable.btn_ellipse);

				}
			}
		});
		imgNext.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{

				if(isInternetPresent)
				{
					
						new HttpAsyncTask1().execute(url);
					/*
					else
					{
						Toast.makeText(getApplicationContext(), "Number did not match",Toast.LENGTH_SHORT).show();
						strCode="";
						imgCode1.setBackgroundResource(R.drawable.btn_ellipse);
						imgCode2.setBackgroundResource(R.drawable.btn_ellipse);
						imgCode3.setBackgroundResource(R.drawable.btn_ellipse);
						imgCode4.setBackgroundResource(R.drawable.btn_ellipse);
					}*/
				}
				else
				{
					Toast.makeText(getApplicationContext(),"Please check internet connection",Toast.LENGTH_SHORT).show();
				}				
			}
		});
	}
	@Override
	public void onBackPressed() 
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	@SuppressWarnings("deprecation")
	public static String POST1(String url)
	{
		InputStream inputStream = null;
		String result = "";
		try
		{		
			@SuppressWarnings("deprecation")
			HttpClient httpclient = new DefaultHttpClient();		
			@SuppressWarnings("deprecation")
			HttpPost httpPost = new HttpPost(url);		    
			String json = "";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("phoneNumber",phoneNumber);
			jsonObject.put("validationCode",strCode);			    
			json = jsonObject.toString();
			@SuppressWarnings("deprecation")
			StringEntity se = new StringEntity(json);		
			httpPost.setEntity(se);
			httpPost.setHeader("Content-type", "application/json");		    
			HttpResponse httpResponse = httpclient.execute(httpPost);			
			inputStream = httpResponse.getEntity().getContent();

			if(inputStream != null)
			{
				result = convertInputStreamToString1(inputStream);
				JSONObject jobj=new JSONObject(result);
				message=jobj.getString("message");
			}
			else
			{

			}

		} 
		catch (Exception e)
		{
			Log.d("InputStream", e.getLocalizedMessage());
		}	
		return result;
	}
	@SuppressWarnings("unused")
	private class HttpAsyncTask1 extends AsyncTask<String, Void, String> 
	{
		ProgressDialog pDialog;
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(AuthorizationCode.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... urls)
		{
			return POST1(urls[0]);
		}
		@Override
		protected void onPostExecute(String result)
		{
			final Dialog dialog=new Dialog(AuthorizationCode.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);	
			dialog.setContentView(R.layout.deviceok);
			Button OK=(Button)dialog.findViewById(R.id.btnCancel1);
			dialog.show();
			OK.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) 
				{
						Intent intent=new Intent(AuthorizationCode.this,CreateAccount.class);
						intent.putExtra("phoneNumber",phoneNumber);
						startActivity(intent);
						SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // 0 - for private mode
						SharedPreferences.Editor editor = settings.edit();
						editor.putBoolean("createAccount",true);
						editor.putString("phoneNumber", phoneNumber);
						editor.commit();
						dialog.dismiss();
				}
			});	
		}
	}

	@SuppressWarnings("unused")
	private static String convertInputStreamToString1(InputStream inputStream) throws IOException
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
