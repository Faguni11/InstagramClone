package com.example.dell.instagramapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.parse.ParseUser;

public class DispatchActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        username=i.getStringExtra("uname1");
       // Toast.makeText(DispatchActivity.this,username, Toast.LENGTH_SHORT).show();
        if(ParseUser.getCurrentUser()!=null){
            Intent intent=new Intent(this,HomeActivity.class);
           // startActivity(new Intent(this,HomeActivity.class));
            intent.putExtra("uname1","username");
            startActivity(intent);

        }
        else{
            startActivity(new Intent(this,SignUp.class));
        }

    }
    }
