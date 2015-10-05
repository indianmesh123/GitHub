package com.sayhello;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class TabLayoutActivity extends TabActivity 
{

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		setTabs() ;
	}
	private void setTabs()
	{
		addTab(R.drawable.btn_activities_inactive, Search.class);
		addTab(R.drawable.btn_search_mv_active,Active.class);		
		addTab(R.drawable.btn_notif_inactive, Notif.class);
		addTab(R.drawable.btn_settings_inactive, Settings.class);
	}

	private void addTab(int drawableId, Class<?> c)
	{
		@SuppressWarnings("deprecation")
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" );	

		@SuppressWarnings("deprecation")
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator,getTabWidget(), false);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}