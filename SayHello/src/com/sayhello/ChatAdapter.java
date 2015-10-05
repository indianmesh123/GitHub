package com.sayhello;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import java.util.ArrayList;
 
public class ChatAdapter extends BaseAdapter {
 
    ArrayList<String> myList = new ArrayList<String>();
    LayoutInflater inflater;
    Context context;
 
 
    public ChatAdapter(Context context, ArrayList<String> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }
 
    @Override
    public int getCount() {
        return myList.size();
    }
 
    @Override
    public String getItem(int position) {
        return myList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_edit_activity, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
 
     
        
        return convertView;
    }
 
    private class MyViewHolder {
        
        public MyViewHolder(View item) {
           
        }
    }
}

