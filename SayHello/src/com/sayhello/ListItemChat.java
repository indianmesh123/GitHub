package com.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

public class ListItemChat extends Activity
{
	LinearLayout lItemChat;
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_item_chat);
		lItemChat=(LinearLayout)findViewById(R.id.lItemChat);
		lItemChat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(ListItemChat.this,Active.class);
				startActivity(intent);
			}
		});
		
}}
