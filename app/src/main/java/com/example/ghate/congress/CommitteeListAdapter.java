package com.example.ghate.congress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ghate on 11/19/2016.
 */

public class CommitteeListAdapter extends ArrayAdapter<Committee>{

    public CommitteeListAdapter(Context context, ArrayList<Committee> arrayList){
        super(context,0,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Committee committee = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.committee_item,parent,false);
        }
        TextView committee_id = (TextView)convertView.findViewById(R.id.committee_id);
        TextView title = (TextView)convertView.findViewById(R.id.committee_name);
        TextView chamber = (TextView)convertView.findViewById(R.id.chamber);
        committee_id.setText(committee.getCommitteeId());
        title.setText(committee.getName());
        chamber.setText(committee.getChamber().substring(0,1).toUpperCase()+committee.getChamber().substring(1));
        return convertView;
    }
}
