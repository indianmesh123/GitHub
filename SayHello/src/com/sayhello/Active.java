package com.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Active extends Activity 
{
	RelativeLayout lEditActivity;
	LinearLayout lConnectionReq;
	protected void onCreate(final Bundle savedInstanceState)
	  {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.connectionrequests);
	      lConnectionReq=(LinearLayout)findViewById(R.id.lConnReq);
	      lConnectionReq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(Active.this,Settings.class);
				startActivity(intent);
				
			}
		});
	  }}