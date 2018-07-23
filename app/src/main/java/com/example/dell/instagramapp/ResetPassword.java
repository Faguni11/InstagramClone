package com.example.dell.instagramapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SaveCallback;

import java.util.List;

public class ResetPassword extends AppCompatActivity {
   EditText password,password1,username;
   String p,u;
Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        password=(EditText)findViewById(R.id.passEt);
        password1=(EditText)findViewById(R.id.passEt2);
        update=(Button)findViewById(R.id.updateBtn);
        username=(EditText)findViewById(R.id.uname);
        //final ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        /*query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
            public void done(ParseObject gameScore, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, only cheatMode and score
                    // will get sent to the Parse Cloud. playerName hasn't changed.
                    gameScore.put("score", 1338);
                    gameScore.put("cheatMode", true);
                    gameScore.saveInBackground();
                }
            }*
        });*/


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=password.getText().toString();
                u=username.getText().toString();

               // ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
                ParseQuery<ParseUser> query=ParseUser.getQuery();
                query.whereEqualTo("username", u);
                query.getFirstInBackground(new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser object, ParseException e) {
                        if(object==null){
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Corresponding Username found",Toast.LENGTH_LONG).show();
                            object.put("password",p);
                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e==null){
                                        Toast.makeText(getApplicationContext(),"Changed password successfully...",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                    }



                    }
                });

            }
        });
    }
}
