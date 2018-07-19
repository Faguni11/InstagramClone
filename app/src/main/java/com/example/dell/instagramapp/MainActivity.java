package com.example.dell.instagramapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit=(Button)findViewById(R.id.btn1);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=(EditText)findViewById(R.id.et1);
                pass=(EditText)findViewById(R.id.et2);


                final ProgressDialog dig=new ProgressDialog(MainActivity.this);
                dig.setTitle("Please wait");
                dig.setMessage("Logging in...Please Wait");
                dig.show();

                ParseUser.logInInBackground(name.getText().toString(), pass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        dig.dismiss();
                        if(e!=null){
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,SignUp.class);
                            startActivity(i);
                        }
                        else{
                            Intent i=new Intent(MainActivity.this,DispatchActivity.class);
                            startActivity(i);
                        }
                    }
                });






            }
        });


//        name.setText(getIntent().getStringExtra("name"));
  //      pass.setText(getIntent().getStringExtra("password"));



    }

    /*public void showUserList(){
        Intent i=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);
    }
    public void signUp(){
        Intent i=new Intent(getApplicationContext(),SignUp.class);
        startActivity(i);
    }*/
}
