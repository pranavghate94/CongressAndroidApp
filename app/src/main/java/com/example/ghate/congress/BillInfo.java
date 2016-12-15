package com.example.ghate.congress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;

public class BillInfo extends AppCompatActivity {

    protected ImageView fav;

    protected TextView billid;
    protected TextView title;
    protected TextView billtype;
    protected TextView sponsor;
    protected TextView chamber;
    protected TextView status;
    protected TextView introduced;
    protected TextView congressurl;
    protected TextView versionstat;
    protected TextView billurl;
    protected Toolbar toolbar;

    SimpleDateFormat read = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat write = new SimpleDateFormat("MMMM dd, yyyy");



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);

        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        billid=(TextView)findViewById(R.id.bill_id);
        title=(TextView)findViewById(R.id.bill_title);
        billtype=(TextView)findViewById(R.id.bill_type);
        sponsor=(TextView)findViewById(R.id.bill_sponsor);
        chamber=(TextView)findViewById(R.id.bill_chamber);
        status=(TextView)findViewById(R.id.bill_status);
        introduced=(TextView)findViewById(R.id.bill_intro);
        congressurl = (TextView)findViewById(R.id.bill_curl);
        versionstat = (TextView)findViewById(R.id.bill_verstat);
        billurl = (TextView)findViewById(R.id.bill_url);
        fav = (ImageView)findViewById(R.id.fav);



        String bill_input_string = getIntent().getStringExtra("bill_object");
        final Bill bill = new Gson().fromJson(bill_input_string,Bill.class);

        if(FavoriteBills.checkFavorite(bill) == 1){
            fav.setImageResource(R.drawable.favon);
        }


        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FavoriteBills.checkFavorite(bill) == 0){
                    FavoriteBills.addFavorite(bill);
                    fav.setImageResource(R.drawable.favon);
                } else {
                    FavoriteBills.removeFavorite(bill);
                    fav.setImageResource(R.drawable.favoff);
                }
            }
        });


        Log.e("BILLID",bill.getBillId().toUpperCase());
        billid.setText(bill.getBillId().toUpperCase());
        title.setText(bill.getShortTitle()!=null?bill.getShortTitle():bill.getOfficialTitle());
        billtype.setText(bill.getBillType().toUpperCase());
        sponsor.setText(bill.getSponsor().getTitle()+", "+bill.getSponsor().getLastName()+", "+bill.getSponsor().getFirstName());
        chamber.setText(bill.getChamber().substring(0,1).toUpperCase()+bill.getChamber().substring(1));
        status.setText(bill.getHistory().getActive().compareTo("true") == 0? "Active":"New");
        try{
            introduced.setText(write.format(read.parse(bill.getIntroducedOn())));
        } catch (Exception e){

        }
        congressurl.setText(bill.getUrls().getCongress()!=null?bill.getUrls().getCongress():"N/A");
        versionstat.setText(bill.getLastVersion()!=null?bill.getLastVersion().getVersionName():"N/A");
        billurl.setText(bill.getLastVersion()!=null && bill.getLastVersion().getUrls().getPdf()!=null?bill.getLastVersion().getUrls().getPdf():"N/A");

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
