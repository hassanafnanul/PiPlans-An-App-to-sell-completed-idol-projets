package com.afnanulcoder.piplans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProblemsDetailsView extends AppCompatActivity {


    TextView theProblemTitleTV, theProblemCategoryTV, theProblemSubmitterTV, theProblemLongTV, theProblemHireOrBuyTV;

    String theProblemTitle, theProblemCategory, theProblemSubmitter, theProblemLong, theProblemHireOrBuy, theProblemSubmitterKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems_details_view);

        theProblemTitle = getIntent().getStringExtra("theProblemTitle");
        theProblemCategory = getIntent().getStringExtra("theProblemCategory");
        theProblemSubmitter = getIntent().getStringExtra("theProblemSubmitter");
        theProblemLong = getIntent().getStringExtra("theProblemLong");
        theProblemHireOrBuy = getIntent().getStringExtra("theProblemHireOrBuy");
        theProblemSubmitterKey = getIntent().getStringExtra("theProblemSubmitterKey");

        theProblemTitleTV = findViewById(R.id.theProblemTitleID);
        theProblemCategoryTV = findViewById(R.id.theProblemCategoryID);
        theProblemSubmitterTV = findViewById(R.id.theProblemSubmitterID);
        theProblemLongTV = findViewById(R.id.theProblemLongID);
        theProblemHireOrBuyTV = findViewById(R.id.theProblemHireOrBuyID);

        theProblemTitleTV.setText(theProblemTitle);
        theProblemCategoryTV.setText(theProblemCategory);
        theProblemSubmitterTV.setText("Submitted By: "+theProblemSubmitter);
        theProblemLongTV.setText(theProblemLong);
        theProblemHireOrBuyTV.setText(theProblemHireOrBuy);

    }




    public void GoToProfile(View view)
    {
        Intent intent = new Intent(this, OnnoderProfile.class);
        intent.putExtra("onnoderProfileKey", theProblemSubmitterKey);
        intent.putExtra("onnoderProfileName", theProblemSubmitter);
        startActivity(intent);
    }


}