package com.example.ghate.congress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ghate on 11/19/2016.
 */

public class LegislatorListAdapter extends ArrayAdapter<Legislator> {
    public LegislatorListAdapter(Context context, ArrayList<Legislator> legislators){
        super(context,0,legislators);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Legislator legislator = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.legislator_item,parent,false);
        }
        ImageView mugshot = (ImageView)convertView.findViewById(R.id.legislator_photo);
        TextView name = (TextView)convertView.findViewById(R.id.legislator_name);
        TextView initial_details = (TextView)convertView.findViewById(R.id.legislator_initial_details);

        Picasso.with(getContext()).load("https://theunitedstates.io/images/congress/original/"+legislator.getBioguideId()+".jpg").resize(120,150).into(mugshot);
        name.setText(legislator.getLastName()+", "+legislator.getFirstName());
        String app = legislator.getDistrict() != null? " - District "+legislator.getDistrict() : "";
        initial_details.setText("("+legislator.getParty()+")"+legislator.getStateName()+app);

        return convertView;
    }
}
