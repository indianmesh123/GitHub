package com.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class TellAFriend extends Activity 
{
	RelativeLayout lEditActivity;
	protected void onCreate(final Bundle savedInstanceState)
	  {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.tellafriend);
	      lEditActivity=(RelativeLayout)findViewById(R.id.lTellFriend);
	      lEditActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(TellAFriend.this,Notifications.class);
				startActivity(intent);
			}
		});
}}
