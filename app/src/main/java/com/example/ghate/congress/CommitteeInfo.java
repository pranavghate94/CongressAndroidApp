package com.example.ghate.congress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class CommitteeInfo extends AppCompatActivity {

    private TextView committeid;
    private TextView name;
    private TextView chamber;
    private TextView parcomm;
    private TextView contact;
    private TextView office;
    private ImageView fav;

    private ImageView chamberlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_info);

        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        committeid = (TextView) findViewById(R.id.committee_id);
        name = (TextView)findViewById(R.id.committee_name);
        chamber = (TextView)findViewById(R.id.committee_chamber);
        parcomm = (TextView)findViewById(R.id.parent_committee);
        contact = (TextView)findViewById(R.id.committee_contact);
        office = (TextView)findViewById(R.id.committee_office);

        chamberlogo = (ImageView)findViewById(R.id.chamber_logo);
        fav = (ImageView)findViewById(R.id.favinsky);



        String comm_input_string = getIntent().getStringExtra("comm_object");
        final Committee committee = new Gson().fromJson(comm_input_string,Committee.class);
        Log.e("CI.class",new Gson().toJson(committee));

        if(FavoriteCommittees.checkFavorite(committee) == 1){
            fav.setImageResource(R.drawable.favon);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FavoriteCommittees.checkFavorite(committee) == 0){
                    FavoriteCommittees.addFavorite(committee);
                    fav.setImageResource(R.drawable.favon);
                } else {
                    FavoriteCommittees.removeFavorite(committee);
                    fav.setImageResource(R.drawable.favoff);
                }
            }
        });

        committeid.setText(committee.getCommitteeId().toUpperCase());
        name.setText(committee.getName());
        chamber.setText(committee.getChamber().substring(0,1).toUpperCase()+committee.getChamber().substring(1));
        parcomm.setText(committee.getParentCommitteeId()!=null?committee.getParentCommitteeId().toUpperCase():"None");
        contact.setText(committee.getPhone()!=null?committee.getPhone():"None");
        office.setText(committee.getOffice()!=null?committee.getOffice():"None");

        if(committee.getChamber().compareTo("joint")!=0)
            Picasso.with(this).load(committee.getChamber().compareTo("house") == 0 ? R.drawable.h:R.drawable.s).resize(80,80).into(chamberlogo);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return true;
        }
    }
}
