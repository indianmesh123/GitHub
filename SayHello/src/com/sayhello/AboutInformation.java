package com.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class AboutInformation extends Activity 
{
	RelativeLayout lEditActivity;
	protected void onCreate(final Bundle savedInstanceState)
	  {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.aboutinformation);
	      lEditActivity=(RelativeLayout)findViewById(R.id.lAboutInformation);
	      lEditActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(AboutInformation.this,TellAFriend.class);
				startActivity(intent);
			}
		});
}}
