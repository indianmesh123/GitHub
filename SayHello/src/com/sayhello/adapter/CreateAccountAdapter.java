package com.sayhello.adapter;

import java.util.ArrayList;

import com.sayhello.CreateList;
import com.sayhello.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateAccountAdapter extends BaseAdapter{
	private Context mContext;

	private final ArrayList<CreateList> Imageid;

	public CreateAccountAdapter(Context c,ArrayList<CreateList> Imageid) {
		mContext = c;
		this.Imageid = Imageid;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Imageid.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		View grid;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) 
		{
			grid = new View(mContext);
			grid = inflater.inflate(R.layout.item_imagecreate, null);
			ImageView imageView = (ImageView)grid.findViewById(R.id.item_image);
		
			imageView.setImageBitmap(Imageid.get(position).getImageView());						
		} 
		else
		{
			grid = (View) convertView;
		}

		return grid;
	}}