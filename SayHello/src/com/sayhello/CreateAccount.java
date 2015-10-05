package com.sayhello;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.sayhello.adapter.CreateAccountAdapter;
import com.sayhello.internetcheck.ConnectionDetector;

@SuppressWarnings("deprecation")
public class CreateAccount extends Activity {
	RelativeLayout lCreateAccount;
	static EditText editName;
	EditText editGender;
	Button buttonFeMale;
	static Button buttonMale;
	String imgDecodableString,imageEncoded;
	Boolean isInternetPresent=false;
	static Boolean isMaleSelected=false;
	Boolean isFeMaleSelected=false;
	static String buttonText;
	ConnectionDetector cd;
	TableLayout lCreateAccount2, lCreateAccount4;
	static String message;
	CreateAccountAdapter adapterFemale, adapterMale;
	ImageView  imgCheck, imgCancel;
	public final static int REQUEST_CAMERA = 1;
	public final static int SELECT_FILE = 2;
	Bitmap bm;
	public static final String PREFS_NAME = "MyPref";
	static Bitmap bmImage;
	public static final String wurl ="https://api-dev.zyngme.net/sayhello/sayhelloservice/v1/user/register";
	static String phoneNumber;
	ImageView imgSide;
	String selectGender;
	static Bitmap bitmapFinal;
	Bitmap bitmapFFinal;
	GridView gridMale, gridFemale;
	String temp;
	ArrayList<CreateList> imgMale = null;
	ArrayList<CreateList> imgFemale = null;
	private static int RESULT_LOAD_IMG = 1;

	ImageView imgMale1,imgMale2,imgMale3,imgMale4,imgMale5,imgMale6,imgMale7,imgMale8,imgMale9,imgMale10,imgMale11,imgMale12,imgMale13,
	imgMale14,imgMale15,imgMale16,imgMale17,imgFemale1,imgFemale2,imgFemale3,imgFemale4,imgFemale5,imgFemale6,imgFemale7,imgFemale8,imgFemale9,imgFemale10,
	imgFemale11,imgFemale12,imgFemale13,imgFemale14,imgFemale15,imgFemale16,imgFemale17;
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		int orientation = display.getOrientation(); 
		switch(orientation) {
		case Configuration.ORIENTATION_PORTRAIT:
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		case Configuration.ORIENTATION_LANDSCAPE:
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;               	
		}
		setContentView(R.layout.createaccount);
		imgSide=(ImageView)findViewById(R.id.imgSide);
		lCreateAccount = (RelativeLayout) findViewById(R.id.lCreateAccount);
		editName = (EditText) findViewById(R.id.editName);
		editGender = (EditText) findViewById(R.id.editGender);
		buttonFeMale = (Button) findViewById(R.id.buttonFeMale);
		buttonMale = (Button) findViewById(R.id.buttonMale);		
		lCreateAccount2=(TableLayout)findViewById(R.id.lCreateAccount2);
		lCreateAccount4=(TableLayout)findViewById(R.id.lCreateAccount4);
		imgMale1=(ImageView)findViewById(R.id.imgMale1);
		imgMale2=(ImageView)findViewById(R.id.imgMale2);
		imgMale3=(ImageView)findViewById(R.id.imgMale3);
		imgMale4=(ImageView)findViewById(R.id.imgMale4);
		imgMale5=(ImageView)findViewById(R.id.imgMale5);
		imgMale6=(ImageView)findViewById(R.id.imgMale6);
		imgMale7=(ImageView)findViewById(R.id.imgMale7);
		imgMale8=(ImageView)findViewById(R.id.imgMale8);
		imgMale9=(ImageView)findViewById(R.id.imgMale9);
		imgMale10=(ImageView)findViewById(R.id.imgMale10);
		imgMale11=(ImageView)findViewById(R.id.imgMale11);
		imgMale12=(ImageView)findViewById(R.id.imgMale12);
		imgMale13=(ImageView)findViewById(R.id.imgMale13);
		imgMale14=(ImageView)findViewById(R.id.imgMale14);
		imgMale15=(ImageView)findViewById(R.id.imgMale15);
		imgMale16=(ImageView)findViewById(R.id.imgMale16);
		imgMale17=(ImageView)findViewById(R.id.imgMale17);
		imgFemale1=(ImageView)findViewById(R.id.imgFemale1);
		imgFemale2=(ImageView)findViewById(R.id.imgFemale2);
		imgFemale3=(ImageView)findViewById(R.id.imgFemale3);
		imgFemale4=(ImageView)findViewById(R.id.imgFemale4);
		imgFemale5=(ImageView)findViewById(R.id.imgFemale5);
		imgFemale6=(ImageView)findViewById(R.id.imgFemale6);
		imgFemale7=(ImageView)findViewById(R.id.imgFemale7);
		imgFemale8=(ImageView)findViewById(R.id.imgFemale8);
		imgFemale9=(ImageView)findViewById(R.id.imgFemale9);
		imgFemale10=(ImageView)findViewById(R.id.imgFemale10);
		imgFemale11=(ImageView)findViewById(R.id.imgFemale11);
		imgFemale12=(ImageView)findViewById(R.id.imgFemale12);
		imgFemale13=(ImageView)findViewById(R.id.imgFemale13);
		imgFemale14=(ImageView)findViewById(R.id.imgFemale14);
		imgFemale15=(ImageView)findViewById(R.id.imgFemale15);
		imgFemale16=(ImageView)findViewById(R.id.imgFemale16);
		imgFemale17=(ImageView)findViewById(R.id.imgFemale17);
		buttonFeMale.setBackgroundResource(R.drawable.female);
		imgCheck = (ImageView) findViewById(R.id.imgCheck);
		imgCancel = (ImageView) findViewById(R.id.imgCancel);
		cd = new ConnectionDetector(getApplicationContext());
		selectGender="Female";
		SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
		phoneNumber=settings.getString("phoneNumber", "");
		
		String userName=settings.getString("name", "");
		String getGender=settings.getString("gender","");
		String bitmapname=settings.getString("image","");
		Bitmap bitmapIntent=decodeBase64(bitmapname);
		if(!userName.equals("") && !getGender.equals("") && !bitmapname.equals(""))
		{			
			imgSide.setImageBitmap(bitmapIntent);
			editName.setText(userName);
			if(getGender.equals("MALE"))
			{
				buttonFeMale.setBackgroundResource(R.drawable.female_white);
				buttonMale.setBackgroundResource(R.drawable.male);
			}
			else
			{
				buttonFeMale.setBackgroundResource(R.drawable.female);
				buttonMale.setBackgroundResource(R.drawable.male_white);
			}
		}	
		imgMale1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				selectImage();

				bitmapFinal=bm;
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(),R.drawable.largetwo);
				imgMale2.setBackgroundResource(R.drawable.slargetwo);
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(),R.drawable.largethree);
				imgMale3.setBackgroundResource(R.drawable.slargethree);
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largefour);
				imgMale4.setBackgroundResource(R.drawable.slargefour);
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largefive);
				imgMale5.setBackgroundResource(R.drawable.slargefive);
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largesix);
				imgMale6.setBackgroundResource(R.drawable.slargesix);
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largeseven);
				imgMale7.setBackgroundResource(R.drawable.slargeseven);
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largeeight);
				imgMale8.setBackgroundResource(R.drawable.slargeeight);
				imgSide.setImageBitmap(bitmapFinal);
			}
		});
		imgMale9.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largenine);
				imgMale9.setBackgroundResource(R.drawable.slargenine);
				imgSide.setImageBitmap(bitmapFinal);

			}
		});
		imgMale10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largeten);
				imgMale10.setBackgroundResource(R.drawable.slargeten);
				imgSide.setImageBitmap(bitmapFinal);

			}
		});
		imgMale11.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				setMaleImageBackGround();
				imgMale11.setBackgroundResource(R.drawable.slargeeleven);

				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largeeleven);
				imgSide.setImageBitmap(bitmapFinal);				
			}
		});
		imgMale12.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largetwelve);
				imgSide.setImageBitmap(bitmapFinal);
				imgMale12.setBackgroundResource(R.drawable.slargetwelve);
			}
		});
		imgMale13.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largethirteen);
				imgSide.setImageBitmap(bitmapFinal);
				imgMale13.setBackgroundResource(R.drawable.slargethirteen);
			}
		});
		imgMale14.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largefourteen);
				imgSide.setImageBitmap(bitmapFinal);
				imgMale14.setBackgroundResource(R.drawable.slargefourteen);

			}
		});
		imgMale15.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largefifteen);
				imgSide.setImageBitmap(bitmapFinal);
				imgMale15.setBackgroundResource(R.drawable.slargefifteen);
			}
		});
		imgMale16.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largesixteen);
				imgSide.setImageBitmap(bitmapFinal);
				imgMale16.setBackgroundResource(R.drawable.slargesixteen);

			}
		});
		imgMale17.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setMaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.largeseventeen);
				imgSide.setImageBitmap(bitmapFinal);
				imgMale17.setBackgroundResource(R.drawable.slargeseventeen);

			}
		});
		imgFemale1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				selectImage();

				bitmapFinal=bm;
				imgSide.setImageBitmap(bitmapFinal);
			}
		});

		imgFemale2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargeone);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale2.setBackgroundResource(R.drawable.sflargeone);

			}
		});
		imgFemale3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargetwo);		
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale3.setBackgroundResource(R.drawable.sflargetwo);

			}
		});
		imgFemale4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargethree);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale4.setBackgroundResource(R.drawable.sflargethree);

			}

		});
		imgFemale5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargefour);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale5.setBackgroundResource(R.drawable.sflargefour);

			}
		});
		imgFemale6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargefive);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale6.setBackgroundResource(R.drawable.sflargefive);

			}
		});
		imgFemale7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargesix);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale7.setBackgroundResource(R.drawable.sflargesix);

			}
		});
		imgFemale8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargeseven);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale8.setBackgroundResource(R.drawable.sflargeseven);
			}
		});
		imgFemale9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargeeight);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale9.setBackgroundResource(R.drawable.sflargeeight);
			}
		});
		imgFemale10.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(),R.drawable.flargenine);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale10.setBackgroundResource(R.drawable.sflargenine);
			}
		});
		imgFemale11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(),R.drawable.flargeten);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale11.setBackgroundResource(R.drawable.sflargeten);
			}
		});
		imgFemale12.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargeeleven);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale12.setBackgroundResource(R.drawable.sflargeeleven);
			}
		});
		imgFemale13.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargetwelve);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale13.setBackgroundResource(R.drawable.sflargetwelve);

			}
		});
		imgFemale14.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 

			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargethirteen);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale14.setBackgroundResource(R.drawable.sflargethirteen);
			}
		});
		imgFemale15.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargefourteen);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale15.setBackgroundResource(R.drawable.sflargefourteen);

			}
		});
		imgFemale17.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargesixteen);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale17.setBackgroundResource(R.drawable.sflargesixteen);

			}
		});
		imgFemale16.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setFemaleImageBackGround();
				bitmapFinal=BitmapFactory.decodeResource(getResources(), R.drawable.flargefiften);
				imgSide.setImageBitmap(bitmapFinal);
				imgFemale16.setBackgroundResource(R.drawable.sflargefifteen);
			}
		});
		imgCheck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{	
				 SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); 
				    Editor editor = pref.edit();
				    editor.putString("name",buttonText);
				isInternetPresent = cd.isConnectingToInternet();
				if(!editName.getText().toString().equals(""))
				{	
					imgCheck.setEnabled(true);
					if(isInternetPresent)
					{
						new HttpAsyncTask().execute(wurl);	

					}
					else
					{
						Toast.makeText(getApplicationContext(), "Please check internet connection",Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please enter the nickname",Toast.LENGTH_SHORT).show();
				}

			}
		});
		imgCancel.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				finish();
				moveTaskToBack(true);
			}
		});
		/*	if (buttonMale.isSelected()) {
			buttonMale.setBackgroundResource(R.drawable.male);
			buttonFeMale.setBackgroundResource(R.drawable.female_white);
		}
		if (buttonFeMale.isSelected()) {
			buttonFeMale.setBackgroundResource(R.drawable.female);
			buttonMale.setBackgroundResource(R.drawable.male_white);
			lCreateAccount4.setVisibility(View.VISIBLE);
			lCreateAccount2.setVisibility(View.GONE);
		}*/
		buttonFeMale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				isMaleSelected=false;
				isFeMaleSelected=true;
				selectGender = "Female";
				buttonFeMale.setBackgroundResource(R.drawable.female);
				buttonMale.setBackgroundResource(R.drawable.male_white);
				lCreateAccount4.setVisibility(View.VISIBLE);
				lCreateAccount2.setVisibility(View.GONE);
			}
		});
		buttonMale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				selectGender = "Male";
				isMaleSelected=true;
				isFeMaleSelected=false;
				buttonMale.setBackgroundResource(R.drawable.male);
				buttonFeMale.setBackgroundResource(R.drawable.female_white);
				lCreateAccount2.setVisibility(View.VISIBLE);
				lCreateAccount4.setVisibility(View.GONE);

			}
		});		
	}
	public static String POST(String url) 
	{
	
		InputStream inputStream = null;
		String result = "";
		try {
			@SuppressWarnings("deprecation")
			HttpClient httpclient = new DefaultHttpClient();
			@SuppressWarnings("deprecation")
			HttpPost httpPost = new HttpPost(url);
			String json = "";
			if (isMaleSelected==true)
			{
				buttonText = "MALE";
			} 
			else
			{
				buttonText = "FEMALE";
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("phoneNumber",phoneNumber);
			jsonObject.put("nickName",editName.getText().toString());
			jsonObject.put("gender",buttonText);
			Log.i("encoded url",encodeTobase64(bitmapFinal));
			jsonObject.put("avatarUri",encodeTobase64(bitmapFinal));
			json = jsonObject.toString();
			@SuppressWarnings("deprecation")
			StringEntity se = new StringEntity(json);
			httpPost.setEntity(se);
			httpPost.setHeader("Content-type","application/json");
			HttpResponse httpResponse = httpclient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();

			if (inputStream != null) {
				result = convertInputStreamToString(inputStream);
				JSONObject jobj = new JSONObject(result);
				message = jobj.getString("message");
			}

			else {
				result = "Did not work!";
			}

		} catch (Exception e)
		{
		}
		return result;
	}

	@SuppressWarnings("unused")
	private class HttpAsyncTask extends AsyncTask<String, Void, String>
	{
		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(CreateAccount.this);
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
			Intent intent = new Intent(CreateAccount.this,EditActivity.class);
			startActivity(intent);
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // 0 - for private mode
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("image",encodeTobase64(bitmapFinal));
			editor.putBoolean("hasCreateAccount",true);
			editor.putString("name",editName.getText().toString());
			editor.putString("gender",buttonText);
			
			editor.commit();
			pDialog.dismiss();
		}
	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException 
			{
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;
		inputStream.close();
		return result;
	}

	private void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
		"Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(
				CreateAccount.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, REQUEST_CAMERA);
				} else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(
							Intent.createChooser(intent, "Select File"),
							SELECT_FILE);

				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_CAMERA) {
				bm = (Bitmap) data.getExtras().get("data");
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				bm.compress(Bitmap.CompressFormat.JPEG, 70, bytes);

				File destination = new File(
						Environment.getExternalStorageDirectory(),
						System.currentTimeMillis() + ".jpg");

				FileOutputStream fo;
				try {
					destination.createNewFile();
					fo = new FileOutputStream(destination);
					fo.write(bytes.toByteArray());
					fo.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bm.compress(Bitmap.CompressFormat.PNG, 80, baos);
				byte[] b = baos.toByteArray();
				temp = Base64.encodeToString(b, Base64.DEFAULT);

				if (selectGender.equals("Male"))
				{
					imgMale1.setImageBitmap(bm);
				}

				else if (selectGender.equals("Female"))
				{
					imgFemale1.setImageBitmap(bm);
				}
				else
				{
					imgFemale1.setImageBitmap(bm);
				}
			} else if (requestCode == SELECT_FILE) {
				Uri selectedImageUri = data.getData();
				String[] projection = { MediaColumns.DATA };
				Cursor cursor = managedQuery(selectedImageUri, projection,
						null, null, null);
				int column_index = cursor
						.getColumnIndexOrThrow(MediaColumns.DATA);
				cursor.moveToFirst();
				String selectedImagePath = cursor.getString(column_index);

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(selectedImagePath, options);
				final int REQUIRED_SIZE = 200;
				int scale = 1;
				while (options.outWidth / scale / 2 >= REQUIRED_SIZE
						&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
					scale *= 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				bm = BitmapFactory.decodeFile(selectedImagePath, options);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bm.compress(Bitmap.CompressFormat.PNG, 80, baos);
				byte[] b = baos.toByteArray();

				temp = Base64.encodeToString(b, Base64.DEFAULT);
				if (selectGender.equals("Male"))
				{
					imgMale1.setImageBitmap(bm);

				}
				if (selectGender.equals("Female"))
				{
					imgFemale1.setImageBitmap(bm);

				}
			}

		}
	}
	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = null;

		if (drawable instanceof BitmapDrawable) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			if (bitmapDrawable.getBitmap() != null) {
				return bitmapDrawable.getBitmap();
			}
		}

		if (drawable.getIntrinsicWidth() <= 0
				|| drawable.getIntrinsicHeight() <= 0) {
			bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); 
		} else {
			bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public static String encodeTobase64(Bitmap image) {
		Bitmap immagex = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
		return imageEncoded;
	}
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();

	}
	public void setMaleImageBackGround()
	{
		imgMale2.setBackgroundResource(R.drawable.largetwo);
		imgMale3.setBackgroundResource(R.drawable.largethree);
		imgMale4.setBackgroundResource(R.drawable.largefour);
		imgMale5.setBackgroundResource(R.drawable.largefive);
		imgMale6.setBackgroundResource(R.drawable.largesix);
		imgMale7.setBackgroundResource(R.drawable.largeseven);
		imgMale8.setBackgroundResource(R.drawable.largeeight);
		imgMale9.setBackgroundResource(R.drawable.largenine);
		imgMale10.setBackgroundResource(R.drawable.largeten);
		imgMale11.setBackgroundResource(R.drawable.largeeleven);
		imgMale12.setBackgroundResource(R.drawable.largetwelve);
		imgMale13.setBackgroundResource(R.drawable.largethirteen);
		imgMale14.setBackgroundResource(R.drawable.largefourteen);
		imgMale15.setBackgroundResource(R.drawable.largefifteen);
		imgMale16.setBackgroundResource(R.drawable.largesixteen);		
	}
	public void setFemaleImageBackGround()
	{
		imgFemale2.setBackgroundResource(R.drawable.flargeone);
		imgFemale3.setBackgroundResource(R.drawable.flargetwo);
		imgFemale4.setBackgroundResource(R.drawable.flargethree);
		imgFemale5.setBackgroundResource(R.drawable.flargefour);
		imgFemale6.setBackgroundResource(R.drawable.flargefive);
		imgFemale7.setBackgroundResource(R.drawable.flargesix);
		imgFemale8.setBackgroundResource(R.drawable.flargeseven);
		imgFemale9.setBackgroundResource(R.drawable.flargeeight);
		imgFemale10.setBackgroundResource(R.drawable.flargenine);
		imgFemale11.setBackgroundResource(R.drawable.flargeten);
		imgFemale12.setBackgroundResource(R.drawable.flargeeleven);
		imgFemale13.setBackgroundResource(R.drawable.flargetwelve);
		imgFemale14.setBackgroundResource(R.drawable.flargethirteen);
		imgFemale15.setBackgroundResource(R.drawable.flargefourteen);
		imgFemale16.setBackgroundResource(R.drawable.flargefiften);	
		imgFemale17.setBackgroundResource(R.drawable.flargesixteen);	
	}	
	public static Bitmap decodeBase64(String input)
	{
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
}
