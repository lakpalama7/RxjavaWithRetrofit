package com.liveinbits.rxjavafirstproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private List<User> userlist;
    int i=0;
    int j=0;
    int k=0;
   // private View convertView;
    public CustomAdapter(List<User> userlist){
        this.userlist=userlist;
    }
    @Override
    public int getCount() {
        return userlist.size();
    }

    @Override
    public Object getItem(int position) {
        return userlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder=null;
        Log.i("outside : ",""+i++);
        if(convertView==null){
            Log.i("inside :" ,""+j++);
            holder=new MyViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
            holder.userid=convertView.findViewById(R.id.userid);
            holder.id=convertView.findViewById(R.id.id);
            holder.title=convertView.findViewById(R.id.title);
            holder.body=convertView.findViewById(R.id.body);
            convertView.setTag(holder);
        }
        else{
            Log.i("else : ",""+k++);
            holder= (MyViewHolder) convertView.getTag();
        }
         User user=userlist.get(position);
        holder.userid.setText(String.valueOf(user.getUserid()));
        holder.id.setText(String.valueOf(user.getId()));
        holder.title.setText(user.getTitle());
        holder.body.setText(user.getBody());
        return convertView;
    }

    public class MyViewHolder{
            TextView userid,id,title,body;
    }
}
