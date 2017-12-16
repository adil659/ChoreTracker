package com.example.adil.navdrawertest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Adil on 2017-12-04.
 */

public class SelectProfile extends AppCompatActivity{
    private ListView listView;
    private ArrayList<String> profileList;
    private ProfileCustomAdapter adapter;
    private Profile selected;



    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);   // content is page of all profiles
        profileList = new ArrayList<>();
         final MyDBHandler dbHandler = new MyDBHandler(this);

        listView = (ListView) findViewById(R.id.profileListID);   // setting up variables

        updateProfiles();


        adapter= new ProfileCustomAdapter(this,profileList);  // setting up adapter
        listView.setAdapter(adapter);

        System.out.println("DO U GO ON CREATE AFTER CREATING PROFILE?? LET ME KNOW OK");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                System.out.println("profileList position iSS " + profileList.get(position));
                dbHandler.updateSelectedProfile(profileList.get(position));
                finish();
            }
        });
    }

    private void updateProfiles(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Cursor res = dbHandler.getAllProfiles();

        while(res.moveToNext()){
            profileList.add(res.getString(1));
            System.out.println(res.getString(1));
        }
    }
}
