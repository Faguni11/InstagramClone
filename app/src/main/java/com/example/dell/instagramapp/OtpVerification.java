package com.example.dell.instagramapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OtpVerification extends AppCompatActivity  {
    Button sendBtn,proceed;
    TextView status;
    EditText phoneEdt;
    String otp="1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        sendBtn=(Button)findViewById(R.id.sendSMSBTn);
        phoneEdt=(EditText)findViewById(R.id.phoneET);
        status=(TextView)findViewById(R.id.statusTV);
        proceed=(Button)findViewById(R.id.proceedBtn);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"PERMISSION NOT GRANTED",Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},123);
            sendBtn.setEnabled(false);
        }
        else
        {
            Toast.makeText(this,"PERMISSION GRANTED",Toast.LENGTH_SHORT).show();
            sendBtn.setEnabled(true);
        }

proceed.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"OTP VERIFIED,redirecting you...",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(OtpVerification.this,ResetPassword.class);

        startActivity(intent);
    }
});

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(phoneEdt.getText().toString(),null,otp,null,null);
                status.setText("MESSAGE SENT");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==123){
            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"PERMISSION HAS BEEN GRANTED",Toast.LENGTH_SHORT).show();
                sendBtn.setEnabled(true);
            }else{
                status.setText("PERMISSION NOT GRANTED");
                sendBtn.setEnabled(false);
            }
        }

    }






}
