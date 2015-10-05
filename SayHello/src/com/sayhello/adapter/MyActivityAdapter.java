package com.sayhello.adapter;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sayhello.EditActivity;
import com.sayhello.EditActivityList;
import com.sayhello.R;
import com.sayhello.Search;
import com.sayhello.TabLayoutActivity;
import com.sayhello.gettersetter.MyActivityList;

public class MyActivityAdapter extends BaseAdapter
{
	Context context;
	ArrayList<MyActivityList> rowItems;
	MyActivityList rowItem;
	int val = 0;
	ViewHolder holder = null;

	public MyActivityAdapter(Context context, ArrayList<MyActivityList> items) {
		this.context = context;
		this.rowItems = items;
	}

	private class ViewHolder {

		ImageView imgActivityType;
		TextView textMeetingMessage, textStartDay, textEndDay, textStartTime,
		textEndTime,textDay1,textDay2,textDay3,textDay4,textDay5,textDay6,textComma1,textComma2,textComma3,textComma4,textComma5;
		LinearLayout lListEditAct,lListEditActivity1;
		LinearLayout imagelayout, options;
		ImageView imgExpand,imgDelete,imgSearch,imgEdit;

	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		 rowItem =rowItems.get(position);
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) 
		{
			convertView = mInflater.inflate(R.layout.list_edit_activity, null);
			holder = new ViewHolder();
			holder.lListEditAct=(LinearLayout)convertView.findViewById(R.id.lListEditAct);
			holder.lListEditActivity1=(LinearLayout)convertView.findViewById(R.id.lListEditActivity1);
			holder.imgExpand = (ImageView) convertView
					.findViewById(R.id.imgExpand);
			holder.imgDelete = (ImageView) convertView
					.findViewById(R.id.imageDelete);
			holder.imgSearch = (ImageView) convertView
					.findViewById(R.id.imageSearch);
			holder.imgEdit =(ImageView) convertView
					.findViewById(R.id.imageEdit);
			holder.options = (LinearLayout) convertView.findViewById(R.id.options);
			holder.imagelayout = (LinearLayout) convertView
					.findViewById(R.id.imagelayout);
			holder.textComma1=(TextView)convertView.findViewById(R.id.textComma1);
			holder.textComma2=(TextView)convertView.findViewById(R.id.textComma2);
			holder.textComma3=(TextView)convertView.findViewById(R.id.textComma3);
			holder.textComma4=(TextView)convertView.findViewById(R.id.textComma4);
			holder.textComma5=(TextView)convertView.findViewById(R.id.textComma5);

			holder.textMeetingMessage = (TextView) convertView
					.findViewById(R.id.textMeetingMessage);
			holder.textStartDay = (TextView) convertView
					.findViewById(R.id.textStartDay);
			holder.textEndDay = (TextView) convertView
					.findViewById(R.id.textEndDay);
			holder.textStartTime = (TextView) convertView
					.findViewById(R.id.textStartTime1);
			holder.textEndTime = (TextView) convertView
					.findViewById(R.id.textEndTime1);
			holder.imgActivityType = (ImageView) convertView
					.findViewById(R.id.imgActivityType);
			holder.textDay1=(TextView)convertView.findViewById(R.id.textDay1);
			holder.textDay2=(TextView)convertView.findViewById(R.id.textDay2);
			holder.textDay3=(TextView)convertView.findViewById(R.id.textDay3);
			holder.textDay4=(TextView)convertView.findViewById(R.id.textDay4);
			holder.textDay5=(TextView)convertView.findViewById(R.id.textDay5);
			holder.textDay6=(TextView)convertView.findViewById(R.id.textDay6);
			convertView.setTag(holder);
			
		} 
		else 
		{
			holder = (ViewHolder) convertView.getTag();
		}
		if (rowItem.getSelected()==1) {
			holder.imgExpand.setBackgroundResource(R.drawable.btn_collapse_lighter);
			holder.imagelayout.setVisibility(8);
			holder.options.setVisibility(0);
		}
		else {
			holder.imgExpand.setBackgroundResource(R.drawable.btnexpand);
			holder.imagelayout.setVisibility(0);
			holder.options.setVisibility(8);
		}
		holder.imgExpand.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (rowItems.get(position).getSelected() == 0)
				{
					
					rowItems.get(position).setSelected(1);
				} 
				else 
				{
					rowItems.get(position).setSelected(0);
					
				}
				notifyDataSetChanged();
			}
		});		
		holder.imgDelete.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				rowItems.remove(position);
				notifyDataSetChanged();					
			}
		});
		holder.imgEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(MyActivityAdapter.this.context,EditActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
		holder.imgSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(context,TabLayoutActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});

		
		
		for(int i=0;i<rowItems.size();i++)
		{
			holder.textMeetingMessage.setText(rowItem.getTextDescription());
			holder.textStartDay.setText(rowItem.getTextStartDay());
			holder.textEndDay.setText(rowItem.getTextEndDay());
			holder.textStartTime.setText(rowItem.getTextStartTime());
			holder.textEndTime.setText(rowItem.getTextEndTime());
			holder.imgActivityType.setImageBitmap(rowItem.getImgActivity());
		}
		if(!EditActivityList.arrList.contains("end"))
		{
			if(EditActivityList.arrDaysList.size()==1)
			{

				holder.lListEditActivity1.setVisibility(View.GONE);
				holder.lListEditAct.setVisibility(View.VISIBLE);
				holder.textDay1.setText(EditActivityList.arrDaysList.get(0).substring(0,3));
			}
			if(EditActivityList.arrDaysList.size()==2)
			{
				holder.lListEditAct.setVisibility(View.VISIBLE);
				holder.textComma1.setVisibility(View.VISIBLE);
				holder.lListEditActivity1.setVisibility(View.GONE);
				holder.textDay1.setText(EditActivityList.arrDaysList.get(0).substring(0,3));
				holder.textDay2.setText(EditActivityList.arrDaysList.get(1).substring(0,3));
			}
			if(EditActivityList.arrDaysList.size()==3)
			{
				holder.lListEditAct.setVisibility(View.VISIBLE);
				holder.lListEditActivity1.setVisibility(View.GONE);
				holder.textComma1.setVisibility(View.VISIBLE);
				holder.textComma2.setVisibility(View.VISIBLE);

				holder.textDay1.setText(EditActivityList.arrDaysList.get(0).substring(0,3));
				holder.textDay2.setText(EditActivityList.arrDaysList.get(1).substring(0,3));
				holder.textDay3.setText(EditActivityList.arrDaysList.get(2).substring(0,3));
			}
			if(EditActivityList.arrDaysList.size()==4)
			{
				holder.lListEditAct.setVisibility(View.VISIBLE);
				holder.lListEditActivity1.setVisibility(View.GONE);
				holder.textComma1.setVisibility(View.VISIBLE);
				holder.textComma2.setVisibility(View.VISIBLE);
				holder.textComma3.setVisibility(View.VISIBLE);

				holder.textDay1.setText(EditActivityList.arrDaysList.get(0).substring(0,3));
				holder.textDay2.setText(EditActivityList.arrDaysList.get(1).substring(0,3));
				holder.textDay3.setText(EditActivityList.arrDaysList.get(2).substring(0,3));
				holder.textDay4.setText(EditActivityList.arrDaysList.get(3).substring(0,3));
			}
			if(EditActivityList.arrDaysList.size()==5)
			{
				holder.lListEditAct.setVisibility(View.VISIBLE);
				holder.lListEditActivity1.setVisibility(View.GONE);
				holder.textComma1.setVisibility(View.VISIBLE);
				holder.textComma2.setVisibility(View.VISIBLE);
				holder.textComma3.setVisibility(View.VISIBLE);
				holder.textComma4.setVisibility(View.VISIBLE);

				holder.textDay1.setText(EditActivityList.arrDaysList.get(0).substring(0,3));
				holder.textDay2.setText(EditActivityList.arrDaysList.get(1).substring(0,3));
				holder.textDay3.setText(EditActivityList.arrDaysList.get(2).substring(0,3));
				holder.textDay4.setText(EditActivityList.arrDaysList.get(3).substring(0,3));
				holder.textDay5.setText(EditActivityList.arrDaysList.get(4).substring(0,3));
			}
			if(EditActivityList.arrDaysList.size()==5)
			{
				holder.lListEditAct.setVisibility(View.VISIBLE);
				holder.lListEditActivity1.setVisibility(View.GONE);
				holder.textComma1.setVisibility(View.VISIBLE);
				holder.textComma2.setVisibility(View.VISIBLE);
				holder.textComma3.setVisibility(View.VISIBLE);
				holder.textComma4.setVisibility(View.VISIBLE);
				holder.textComma5.setVisibility(View.VISIBLE);
				holder.textDay1.setText(EditActivityList.arrDaysList.get(0).substring(0,3));
				holder.textDay2.setText(EditActivityList.arrDaysList.get(1).substring(0,3));
				holder.textDay3.setText(EditActivityList.arrDaysList.get(2).substring(0,3));
				holder.textDay4.setText(EditActivityList.arrDaysList.get(3).substring(0,3));
				holder.textDay5.setText(EditActivityList.arrDaysList.get(4).substring(0,3));
				holder.textDay6.setText(EditActivityList.arrDaysList.get(5).substring(0,3));
			}
		}

		return convertView;
	}

	@Override
	public int getCount()
	{
		return rowItems.size();
	}

	@Override
	public Object getItem(int position) {
		return rowItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return rowItems.indexOf(getItem(position));
	}
}