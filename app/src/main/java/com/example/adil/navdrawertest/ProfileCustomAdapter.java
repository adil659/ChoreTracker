package com.example.adil.navdrawertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adil on 2017-11-20.
 */

public class ProfileCustomAdapter extends ArrayAdapter {


    private final Context context;
    private final List<String> myChores;

    public ProfileCustomAdapter(Context context, List<String> profileList) {
        super(context, R.layout.profile_item_layout, profileList);
        this.context = context;
        this.myChores = profileList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);

        MyDBHandler dbHandler = new MyDBHandler(getContext());

        Profile profile = dbHandler.findProfile(myChores.get(position));
        int type = profile.getType();
        String tip;
        if(type == 1){
            tip = "Parent";
        }
        else{
            tip = "Child";
        }

        ((TextView) rowView.findViewById(R.id.itemName)).setText(myChores.get(position));
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.itemDescription);
        choreDescriptionTextField.setText(tip);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);


        if (position%2 == 0)
        {
            choreImage.setBackgroundResource(R.mipmap.ic_launcher);
        }
        return rowView;
    }
}
