package com.example.dell.instagramapp;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class FeedsActivity extends ListActivity {


TextView follow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_feed);

       follow=(TextView)findViewById(R.id.textView13);

        final ConstraintLayout linearLayout=(ConstraintLayout) findViewById(R.id.mainLayout);

        Intent i=getIntent();
        String activeUsername=i.getStringExtra("username");
        Log.i("AppInfo",activeUsername);

        setTitle(activeUsername+"'s Feed");
        follow.setText("Follow "+activeUsername+" to be able to see their future posts.");

        ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("images");
        query.whereEqualTo("username",activeUsername);
        query.orderByDescending("createdAt");


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {


                if(e==null){

                    Toast.makeText(getApplicationContext(),objects.size()+" ",Toast.LENGTH_LONG).show();
                    Adapter adapter=new Adapter(FeedsActivity.this,objects);
                    setListAdapter(adapter);

                }else{
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                }

           /*if(e==null){
               if(objects.size()>0){
                   Toast.makeText(getApplicationContext(),"Total posts by this user- "+objects.size(),Toast.LENGTH_LONG).show();
                   for(ParseObject object:objects){
                       ParseFile file=(ParseFile) object.get("image");
                       file.getDataInBackground(new GetDataCallback() {
                           @Override
                           public void done(byte[] data, ParseException e) {
                               if(e==null){
                                   Bitmap image= BitmapFactory.decodeByteArray(data,0,data.length);
                                   ImageView imageView=new ImageView(getApplicationContext());
                                  imageView.setImageBitmap(image);

                                  imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                                   linearLayout.addView(imageView);

                               }

                           }

                       });

                   }
               }
           }*/


            }
        });



    }

}
