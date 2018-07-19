package com.example.dell.instagramapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {
Button signup;
EditText name,pass1,pass2;
String n,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup=(Button)findViewById(R.id.btn2);
        name=(EditText)findViewById(R.id.et3);
        pass1=(EditText)findViewById(R.id.et4);
        pass2=(EditText)findViewById(R.id.et5);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n=name.getText().toString();
                p=pass1.getText().toString();

                final ProgressDialog dig=new ProgressDialog(SignUp.this);
                dig.setTitle("Please wait");
                dig.setMessage("Signing Up..Please Wait");
                dig.show();
                ParseUser user=new ParseUser();

                user.setUsername(name.getText().toString());
                user.setPassword(pass1.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        dig.dismiss();
                        if(e!=null){
                            Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent i=new Intent(SignUp.this,DispatchActivity.class);
                                    startActivity(i);
                        }
                    }
                });
                Intent i=new Intent(SignUp.this,MainActivity.class);
                i.putExtra("name",n);
                i.putExtra("password",p);
                startActivity(i);

            }
        });
    }
    }
