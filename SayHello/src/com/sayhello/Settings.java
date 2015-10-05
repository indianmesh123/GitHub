package com.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Settings extends Activity
{
	RelativeLayout lEditActivity;
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.livechat);
		lEditActivity=(RelativeLayout)findViewById(R.id.lLiveChat);
		lEditActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{

			}
		});
	}}

