package com.example.dell.instagramapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
TextView name;
Button logout,profile;
ArrayList<String> usernames;
ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ParseUser currentUser=ParseUser.getCurrentUser();
        String user=currentUser.getUsername().toString();
        name=(TextView)findViewById(R.id.tv8);
        logout=(Button)findViewById(R.id.btn3);
        profile=(Button)findViewById(R.id.btn4);

        name.setText("You are logged in as "+user);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                finish();
            }
        });


usernames=new ArrayList<String>();
arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usernames);
final ListView userList=(ListView)findViewById(R.id.lv);

        ParseQuery<ParseUser> query=ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseUser user:objects){
                            usernames.add(user.getUsername());
                        }

                        userList.setAdapter(arrayAdapter);
                    }
                }
            }
        });



profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    Intent i=new Intent(HomeActivity.this,MyProfile.class);
    startActivity(i);
    }
});

    }
}
