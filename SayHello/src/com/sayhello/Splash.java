package com.sayhello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity
{
	ImageView imgGo;
	TextView textCreateAccount;
	private static int SPLASH_TIME_OUT = 1000;
	public static final String PREFS_NAME = "MyPref";
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		imgGo=(ImageView)findViewById(R.id.imgGo);
		textCreateAccount=(TextView)findViewById(R.id.textCreateAccount);

		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{	

				SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
				boolean hasCreateAccount = settings.getBoolean("hasCreateAccount",false);
				boolean createAccount=settings.getBoolean("createAccount", false);

				if(hasCreateAccount)
				{
					Intent intent=new Intent(Splash.this,EditActivity.class);
					textCreateAccount.setVisibility(View.GONE);
					imgGo.setEnabled(false);
					startActivity(intent);
				}
				else if(createAccount)
				{
					Intent intent=new Intent(Splash.this,CreateAccount.class);
					textCreateAccount.setVisibility(View.GONE);
					imgGo.setEnabled(false);
					startActivity(intent);
				}
				else
				{
					textCreateAccount.setVisibility(View.VISIBLE);
					Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
					v.vibrate(300);
				}
			}
		}, SPLASH_TIME_OUT);

		imgGo.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(Splash.this,Terms.class);
				startActivity(intent);

			}
		});


	}

}
