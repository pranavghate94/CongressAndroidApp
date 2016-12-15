package com.example.ghate.congress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ghate on 11/19/2016.
 */

public class BillsListAdapter extends ArrayAdapter<Bill> {

    SimpleDateFormat read = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat write = new SimpleDateFormat("MMMM dd, yyyy");
    Date ndate;

    public BillsListAdapter(Context context, ArrayList<Bill> arrayList){
        super(context,0,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String newdate="";
        Bill bill = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bill_item,parent,false);
        }
        TextView billid = (TextView)convertView.findViewById(R.id.billid);
        TextView title = (TextView)convertView.findViewById(R.id.short_title);
        TextView date = (TextView)convertView.findViewById(R.id.introduced_on);
        billid.setText(bill.getBillId().toUpperCase());


        if(bill.getShortTitle()!=null){
            title.setText(bill.getShortTitle());
        } else {
            title.setText(bill.getOfficialTitle());
        }
        try{
            ndate = read.parse(bill.getIntroducedOn());
            newdate = write.format(ndate);
        } catch (Exception e){

        }

        date.setText(newdate);

        return convertView;
    }
}
