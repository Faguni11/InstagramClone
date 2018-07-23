package com.example.dell.instagramapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends ArrayAdapter<ParseObject> {
   private Context mContext;
   private List<ParseObject> mImages;


   public  Adapter(Context context,List<ParseObject>images){
       super(context,R.layout.single_row,images);

       mContext=context;
       mImages=images;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent){
       final ViewHolder holder;
       if(convertView==null){
           convertView= LayoutInflater.from(mContext).inflate(R.layout.single_row,null);
           holder=new ViewHolder();
           holder.imageView=(ImageView)convertView.findViewById(R.id.imV2);
           convertView.setTag(holder);
       }else{
           holder=(ViewHolder)convertView.getTag();
       }
       ParseObject object=mImages.get(position);
       Picasso.with(getContext().getApplicationContext()).load(object.getParseFile("image").getUrl()).noFade().into(holder.imageView);

       return convertView;
   }
   public static class ViewHolder{
       ImageView imageView;

   }
}
