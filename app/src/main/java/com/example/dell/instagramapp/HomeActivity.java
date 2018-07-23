package com.example.dell.instagramapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
{
TextView name,forgot1;
Button logout,profile,feed;
ArrayList<String> usernames;
ArrayAdapter arrayAdapter;

String username1;
    //username1=getIntent().getStringExtra("uname");

    ParseUser currentUser=ParseUser.getCurrentUser();
    String user=currentUser.getUsername().toString();
    String Username=user;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

   Toast.makeText(this,user,Toast.LENGTH_LONG).show();

        name=(TextView)findViewById(R.id.tv8);
     /*   forgot1=(TextView)findViewById(R.id.ff);
        logout=(Button)findViewById(R.id.btn3);
        profile=(Button)findViewById(R.id.btn4);
        feed=(Button)findViewById(R.id.feedBtn);
*/
        name.setText("You are logged in as "+user);


/*        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                finish();
            }
        });
        String text="Forgot Password?";
        SpannableString ss=new SpannableString(text);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_LONG).show();
                Intent i=new Intent(HomeActivity.this,OtpVerification.class);
                startActivity(i);
            }
        };
        ss.setSpan(clickableSpan,0,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        forgot1.setText(ss);
        forgot1.setMovementMethod(LinkMovementMethod.getInstance());
*/

usernames=new ArrayList<String>();
arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usernames);
final ListView userList=(ListView)findViewById(R.id.lv);
//userList.setOnItemClickListener((AdapterView.OnItemClickListener) this);

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


userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Log.i("Hello User", "You clicked Item: " + id + " at position:" + position);
        String selectedFromList = (userList.getItemAtPosition(position)).toString();
        Toast.makeText(getApplicationContext(),"Selected user-"+selectedFromList,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(HomeActivity.this,FeedsActivity.class);
        intent.putExtra("username",selectedFromList);
        startActivity(intent);

    }
});

/*profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"You will be redirected to your page",Toast.LENGTH_SHORT).show();
    Intent intent=new Intent(HomeActivity.this,MyProfile.class);
    startActivity(intent);
    }
});

feed.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(HomeActivity.this,MyFeed.class);
      //  intent.putExtra("username",selectedFromList);
        startActivity(intent);
    }
});*/


    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_home, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sitem1 : {    Toast.makeText(getApplicationContext(),"Logging out,please wait...",Toast.LENGTH_LONG).show();
                                    ParseUser.logOut();
                                    finish();
                Intent i=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);

                                    return true;}
            case R.id.sitem2 :  {    Toast.makeText(getApplicationContext(),"Redirecting you,please wait...",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(HomeActivity.this,OtpVerification.class);
                                    startActivity(i);
                                    return true;}
            case R.id.item2 :   {   Toast.makeText(getApplicationContext(),"You will be redirected to your page "+user,Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(HomeActivity.this,MyProfile.class);
                                    intent.putExtra("uname1",Username);
                                    startActivity(intent);
                                    return true;}
            case R.id.item3 : {
                                     Intent intent=new Intent(HomeActivity.this,MyFeed.class);
                //  intent.putExtra("username",selectedFromList);
                                     startActivity(intent);
                                     return true;

            }


        }


        return super.onOptionsItemSelected(item);
    }

}
