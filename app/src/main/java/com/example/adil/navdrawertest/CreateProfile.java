package com.example.adil.navdrawertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adil on 2017-11-21.
 */


public class CreateProfile extends AppCompatActivity {

    TextView text;
    int counter =0;
    Spinner mySpinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);


        text = (TextView) findViewById(R.id.name);
        mySpinner = (Spinner) findViewById((R.id.spinnerItems));


    }

    public void addProfileToDB (View view)
    {

        MyDBHandler dbHandler = new MyDBHandler(this);
        String spinnerInfo = mySpinner.getSelectedItem().toString();
        System.out.println("THIS IS OUTPUT SPINNER " + spinnerInfo + " COUNTER " + counter);

        Profile profile = new Profile(text.getText().toString() );
        profile.setPoints(0);

        if(spinnerInfo.equals("Child")){
            profile.setType(0);
        }
        else{
            profile.setType(1);
        }
        System.out.println("this is type at cration! " + profile.getType());

         boolean result = dbHandler.addProfile(profile);

        dbHandler.updateSelectedProfile(profile.getProfileName());




         if(result){
             Toast.makeText(this,"DAta inserted", Toast.LENGTH_LONG).show();
         }
         else{
             Toast.makeText(this,"DAta not inserted", Toast.LENGTH_LONG).show();
         }


        Intent i = new Intent().putExtra("profileName",text.getText().toString()); // sends it back to profile list
        setResult(0,i);
        finish();

    }
}
