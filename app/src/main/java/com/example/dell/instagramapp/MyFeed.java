package com.example.dell.instagramapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MyFeed extends ListActivity {
    public void queryImagesFromParse(){
        ParseQuery<ParseObject> imagesQuery=new ParseQuery<ParseObject>("images");
        imagesQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> images, ParseException e) {
           if(e==null){

               Toast.makeText(getApplicationContext(),images.size()+" ",Toast.LENGTH_LONG).show();
           Adapter adapter=new Adapter(MyFeed.this,images);
           setListAdapter(adapter);

           }else{
               Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

           }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfeed);

    queryImagesFromParse();
    }
}
