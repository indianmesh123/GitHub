package com.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Terms extends Activity
{
	TextView textAccept,textCancel;
	RelativeLayout rTerms;
	LinearLayout lTerms10;
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.terms);
		rTerms=(RelativeLayout)findViewById(R.id.lTerms);
		textAccept=(TextView)findViewById(R.id.textAccept);
		textCancel=(TextView)findViewById(R.id.textCancel);
		lTerms10=(LinearLayout)findViewById(R.id.lTerms10);
		lTerms10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Terms.this,Authorization.class);
				startActivity(intent);
				
			}
		});
		
		textCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		finish();
		   moveTaskToBack(true);
				
			}
		});
		rTerms.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(Terms.this,CreateAccount.class);
				startActivity(intent);
			}
		});

	}
}