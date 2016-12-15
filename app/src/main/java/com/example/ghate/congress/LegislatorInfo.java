package com.example.ghate.congress;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LegislatorInfo extends AppCompatActivity {

    private ImageView photo;
    private ImageView party_photo;
    private TextView party;
    private TextView legislator_name;
    private TextView legislator_email;
    private TextView legislator_chamber;
    private TextView legislator_contact;
    private TextView legislator_start_term;
    private TextView legislator_end_term;
    private TextView legislator_office;
    private TextView legislator_state;
    private TextView legislator_fax;
    private TextView legislator_birthday;
    private TextView percentage_text;

    private ProgressBar progressBar;

    private ImageView fb;
    private ImageView tw;
    private ImageView wb;
    private ImageView fav;

    SimpleDateFormat read = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat write = new SimpleDateFormat("MMMM dd, yyyy");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_info);

        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Date a,b,c;
        int daysa=0;
        int daysb=0;
        int progress=0;

        photo = (ImageView)findViewById(R.id.mugshot);
        party_photo = (ImageView)findViewById(R.id.partylogo);
        party = (TextView)findViewById(R.id.party);
        legislator_name = (TextView)findViewById(R.id.legislator_name);
        legislator_email = (TextView)findViewById(R.id.legislator_email);
        legislator_chamber = (TextView)findViewById(R.id.legislator_chamber);
        legislator_contact = (TextView)findViewById(R.id.legislator_contact);
        legislator_start_term = (TextView)findViewById(R.id.legislator_start_term);
        legislator_end_term = (TextView)findViewById(R.id.legislator_end_term);
        legislator_office = (TextView)findViewById(R.id.legislator_office);
        legislator_state = (TextView)findViewById(R.id.legislator_state);
        legislator_fax = (TextView)findViewById(R.id.legislator_fax);
        legislator_birthday = (TextView)findViewById(R.id.legislator_birthday);
        progressBar = (ProgressBar)findViewById(R.id.progbar);
        percentage_text = (TextView)findViewById(R.id.percentage_text);

        fb = (ImageView)findViewById(R.id.facebook);
        tw = (ImageView)findViewById(R.id.twitter);
        wb = (ImageView)findViewById(R.id.website);
        fav = (ImageView)findViewById(R.id.favorite);




        String legislator_input_string = getIntent().getStringExtra("legislator_object");
        final Legislator legislator = new Gson().fromJson(legislator_input_string,Legislator.class);


        Log.e("FAV",Integer.toString(FavoriteLegislators.checkFavorite(legislator)));
        if(FavoriteLegislators.checkFavorite(legislator) == 1){
            fav.setImageResource(R.drawable.favon);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FavoriteLegislators.checkFavorite(legislator) == 0){
                    FavoriteLegislators.addFavorite(legislator);
                    fav.setImageResource(R.drawable.favon);
                } else {
                    FavoriteLegislators.removeFavorite(legislator);
                    fav.setImageResource(R.drawable.favoff);
                }
            }
        });




        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(legislator.getFacebook()!=null){
                    String profile_uri = "http://www.facebook.com/"+legislator.getFacebook();
                    Intent facebook_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(profile_uri));
                    startActivity(facebook_intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Faceboook Profile Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(legislator.getTwitter()!=null){
                    String twitter_profile = "http://www.twitter.com/"+legislator.getTwitter();
                    Intent twitter_intent = new Intent(Intent.ACTION_VIEW,Uri.parse(twitter_profile));
                    startActivity(twitter_intent);
                } else {
                    Toast.makeText(getApplicationContext(),"No Twitter Available",Toast.LENGTH_SHORT).show();
                }

            }
        });

        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(legislator.getWebsite() !=null){

                    Intent website = new Intent(Intent.ACTION_VIEW,Uri.parse(legislator.getWebsite()));
                    startActivity(website);
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Website",Toast.LENGTH_SHORT).show();
                }
            }
        });



        Picasso.with(this).load("https://theunitedstates.io/images/congress/original/"+legislator.getBioguideId()+".jpg").resize(300,345).into(photo);
        Picasso.with(this).load("http://cs-server.usc.edu:45678/hw/hw8/images/"+legislator.getParty().toLowerCase()+".png").into(party_photo);


        party.setText((legislator.getParty().compareTo("R")==0?"Republican":"Democrat"));
        legislator_name.setText(legislator.getTitle()+", "+legislator.getLastName()+", "+legislator.getFirstName());
        legislator_email.setText((legislator.getEmail() != null?legislator.getEmail():"N/A"));
        legislator_chamber.setText(legislator.getChamber().substring(0,1).toUpperCase()+legislator.getChamber().substring(1));
        legislator_contact.setText((legislator.getPhone()!=null?legislator.getPhone():"N/A"));
        try{
            legislator_start_term.setText(write.format(read.parse(legislator.getTermStart())));
            legislator_end_term.setText(write.format(read.parse(legislator.getTermEnd())));
            legislator_birthday.setText(write.format(read.parse(legislator.getBirthday())));
            a = read.parse(legislator.getTermStart());
            b = read.parse(legislator.getTermEnd());
            c = new Date();
            daysa = Days.daysBetween(new DateTime(c),new DateTime(a)).getDays();
            daysb = Days.daysBetween(new DateTime(b),new DateTime(a)).getDays();
            progress = (int)(((float)daysa/daysb)*100);
            progressBar.setProgress(progress);
            progressBar.setMax(100);
            percentage_text.setText(progress+"%");



        }
        catch (Exception e){

        }




        legislator_office.setText(legislator.getOffice());
        legislator_state.setText(legislator.getStateName());
        legislator_fax.setText((legislator.getFax()!=null?legislator.getFax():"N/A"));




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
