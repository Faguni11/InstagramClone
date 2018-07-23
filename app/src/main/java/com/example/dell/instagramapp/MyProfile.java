
package com.example.dell.instagramapp;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MyProfile extends AppCompatActivity {
String active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i=getIntent();
active=i.getStringExtra("uname1");
//Toast.makeText(this,active,Toast.LENGTH_LONG).show();
        queryImagesFromParse();


    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_profile, menu);

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.share){
            Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i,1);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            Uri selectedImage=data.getData();
            try{
                final Bitmap bitmapImage=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                Log.i("AppInfo","Image Received");
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmapImage.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] byteArray =stream.toByteArray();
                ParseFile file=new ParseFile("image.png",byteArray);
                ParseObject object=new ParseObject("images");
                object.put("username", ParseUser.getCurrentUser().getUsername());
                object.put("image",file);

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                   if(e==null){
                       Toast.makeText(getApplication().getApplicationContext(),"Your image has been saved",Toast.LENGTH_SHORT).show();
                      // Toast.makeText(getApplication().getApplicationContext(),activeUsername,Toast.LENGTH_SHORT).show();

                       //ImageView img=(ImageView)findViewById(R.id.imV);
                       //img.setImageBitmap(bitmapImage);

                       //bitmapImage=(Bitmap)data.getExtras().get("data");
                   }
                   else{
                       Toast.makeText(getApplication().getApplicationContext(),"There was an error saving your image-please try again",Toast.LENGTH_SHORT).show();
                   }
                    }
                });


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void queryImagesFromParse(){

        ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("images");
        query.whereEqualTo("username",active




        );
        query.orderByDescending("createdAt");


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {


                if(e==null){
                    ListView lv=(ListView)findViewById(R.id.list2);
                    Toast.makeText(getApplicationContext(),objects.size()+" ",Toast.LENGTH_LONG).show();
                    Adapter adapter=new Adapter(MyProfile.this,objects);
                   // setListAdapter(adapter);
                    lv.setAdapter(adapter);

                }else{
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
    }




}
