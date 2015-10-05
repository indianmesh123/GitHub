package com.sayhello.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
 
import java.util.ArrayList;

import com.sayhello.Country;
import com.sayhello.R;

 
public class CountryAdapter extends BaseAdapter {
 
    ArrayList<Country> myList = new ArrayList<Country>();
    LayoutInflater inflater;
    Context context;
 
 
    public CountryAdapter(Context context, ArrayList<Country> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }
 
    @Override
    public int getCount() {
        return myList.size();
    }
 
    @Override
    public Country getItem(int position) {
        return myList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MyViewHolder mViewHolder; 
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_countries, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        else 
        {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
 
        Country currentListData = getItem(position);   
        mViewHolder.tvCountry.setText(currentListData.mCountryName);
        return convertView;
    }
 
    private class MyViewHolder {
        TextView tvCountry;
 
        public MyViewHolder(View item) {
        	tvCountry = (TextView) item.findViewById(R.id.textCountry);
           
        }
    }
}

