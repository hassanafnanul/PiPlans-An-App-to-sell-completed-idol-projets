package com.afnanulcoder.piplans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProjectsDetailsView extends AppCompatActivity {


    TextView theProjectTitleTV, theProjectCategoryTV, theProjectUploaderTV, theProblemLongTV, theProjectTagTV;

    String theProjectTitle, theProjectCategory, theProjectUploader, theProjectLong, theProjectProgramming, theProjectWeb, theProjectFramework, theProjectDatabase, theProjectUploaderKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_details_view);

        theProjectTitle = getIntent().getStringExtra("theProjectTitle");
        theProjectCategory = getIntent().getStringExtra("theProjectCategory");
        theProjectUploader = getIntent().getStringExtra("theProjectUploader");
        theProjectLong = getIntent().getStringExtra("theProjectLong");
        theProjectProgramming = getIntent().getStringExtra("theProjectProgramming");
        theProjectWeb = getIntent().getStringExtra("theProjectWeb");
        theProjectFramework = getIntent().getStringExtra("theProjectFramework");
        theProjectDatabase = getIntent().getStringExtra("theProjectDatabase");

        theProjectUploaderKey = getIntent().getStringExtra("theProjectUploaderKey");



        theProjectTitleTV = findViewById(R.id.theProjectTitleID);
        theProjectCategoryTV = findViewById(R.id.theProjectCategoryID);
        theProjectUploaderTV = findViewById(R.id.theProjectUploaderID);
        theProblemLongTV = findViewById(R.id.theProjectLongID);
        theProjectTagTV = findViewById(R.id.theProjectTagID);


        theProjectTitleTV.setText(theProjectTitle);
        theProjectCategoryTV.setText(theProjectCategory);
        theProjectUploaderTV.setText(theProjectUploader);
        theProblemLongTV.setText(theProjectLong);
        theProjectTagTV.setText(theProjectProgramming+theProjectWeb+theProjectFramework+theProjectDatabase);



    }

    public String StringChanger(String inputString)
    {
        String outPutString = "";

        char[] inputArray = inputString.toCharArray();
        int i, len = inputArray.length;

        for(i=0; i<len; i++)
        {
            if(inputArray[i] == ',')
                inputArray[i] = '\n';
        }

        outPutString = inputArray.toString();

        return  outPutString;

    }

    public void GoToProfile(View view)
    {
        Intent intent = new Intent(this, OnnoderProfile.class);
        intent.putExtra("onnoderProfileKey", theProjectUploaderKey);
        intent.putExtra("onnoderProfileName", theProjectUploader);
        startActivity(intent);
    }
}