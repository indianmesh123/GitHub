package com.sayhello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashNext extends Activity
{
	ImageView imgGo;
	TextView textCreateAccount;
	private static int SPLASH_TIME_OUT = 3000;
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashnext);
		imgGo=(ImageView)findViewById(R.id.imgGo);
		textCreateAccount=(TextView)findViewById(R.id.textCreateAccount);	   
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		v.vibrate(2000);
		imgGo.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				Intent intent=new Intent(SplashNext.this,Authorization.class);
				startActivity(intent);
			}
		});
		/*  new Handler().postDelayed(new Runnable() {


	            @Override
	            public void run()
	            {	

	            	Intent i = new Intent(Splash.this, Splash.class);
	                startActivity(i);	 
	                finish();
	            }
	        }, SPLASH_TIME_OUT);*/


	}

}
