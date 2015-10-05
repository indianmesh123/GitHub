package com.sayhello;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class CreateNewAccount extends Activity
{
	ImageView imgLogo;
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccount);
		imgLogo=(ImageView)findViewById(R.id.imgTopBarAccount);
		

	}
}