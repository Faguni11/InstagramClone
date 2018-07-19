package com.example.dell.instagramapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

public class DispatchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ParseUser.getCurrentUser()!=null){
            startActivity(new Intent(this,HomeActivity.class));

        }
        else{
            startActivity(new Intent(this,SignUp.class));
        }

    }
    }
