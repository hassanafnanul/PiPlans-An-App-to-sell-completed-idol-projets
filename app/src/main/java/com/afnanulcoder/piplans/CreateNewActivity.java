package com.afnanulcoder.piplans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateNewActivity extends AppCompatActivity {


    String[] categories = new String[]{"Select Category", "Programming & Problem Solving", "Mobile App Development", "Computer Vision", "Machine Learning", "Artificial Intaligence", "AR/VR", "Website", "Web App", "Computer Networking", "Cyber Security", "Others"};

    ProgressBar theProgressbar;

    String theUserName, theUserID;



    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1;


    //----------------- Upload Project Variables---------------
    Button uploadProject;

    ScrollView uploadProjectLayout;


    EditText uTitleET, uShortET, uLongET;
    Spinner uCategorySP;
    Button uProgrammingLanguageBTN, uWebLanguageBTN, uFrameworkBTN, uDatabaseBTN;


    CheckBox c, java, python, rubi, kotlin;
    CheckBox html, css, php, js;
    CheckBox android, ios, spring, wordpress, django;
    CheckBox mysql, sqlite, firebase, oracle;


    String uTitle = "", uCategory = "", uShort = "", uLong = "", uProgrammingLanguage = "", uWebLanguage = "", uFramework = "", uDatabase = "";


    //------------------- Submit Problem Variables---------------------

    Button submitProblem;

    ScrollView submitProblemLayout;

    EditText sTitleET, sShortET, sLongET;
    Spinner sCategorySP;
    RadioButton sHire, sBuy;

    String sTitle = "", sCategory = "", sShort = "", sLong = "", sHireOrBuy = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        //------------------ Porichoy Porbo (Normal) ----------------------------

        theProgressbar = findViewById(R.id.theProgressbarID);



        //----------------- Porichoy Porbo  (Upload Project) ------------------------------

        uploadProject = findViewById(R.id.uploadProjectID);

        uploadProjectLayout = findViewById(R.id.uploadProjectLayoutID);


        uTitleET = findViewById(R.id.uTitleID);
        uShortET = findViewById(R.id.uShortDescriptionID);
        uLongET = findViewById(R.id.uDetailedDescriptionID);

        uCategorySP = findViewById(R.id.uCategoryID);

        uProgrammingLanguageBTN = findViewById(R.id.uProgrammingLanguageBtnID);
        uWebLanguageBTN = findViewById(R.id.uWebLanguageBtnID);
        uFrameworkBTN = findViewById(R.id.uFrameworkBtnID);
        uDatabaseBTN = findViewById(R.id.uDatabaseBtnID);



        c = findViewById(R.id.cID);
        java = findViewById(R.id.javaID);
        python = findViewById(R.id.pythonID);
        rubi = findViewById(R.id.rubiID);
        kotlin = findViewById(R.id.kotlinID);

        html = findViewById(R.id.htmlID);
        css = findViewById(R.id.cssID);
        php = findViewById(R.id.phpID);
        js = findViewById(R.id.jsID);

        android = findViewById(R.id.androidID);
        ios = findViewById(R.id.iosID);
        spring = findViewById(R.id.springID);
        wordpress = findViewById(R.id.wordpressID);
        django = findViewById(R.id.djangoID);

        mysql = findViewById(R.id.mysqlID);
        sqlite = findViewById(R.id.sqliteID);
        firebase = findViewById(R.id.firebaseID);
        oracle = findViewById(R.id.oracleID);




        //----------------- Porichoy Porbo  (Submit Problem) ------------------------------

        submitProblem = findViewById(R.id.submitProblemID);

        submitProblemLayout = findViewById(R.id.submitProblemLayoutID);

        sTitleET = findViewById(R.id.sTitleID);
        sShortET = findViewById(R.id.sShortDescriptionID);
        sLongET = findViewById(R.id.sDetailedDescriptionID);

        sCategorySP = findViewById(R.id.sCategoryID);

        sHire = findViewById(R.id.sHireDeveloperID);
        sBuy = findViewById(R.id.sBuyProjectID);

        //-------------------------------------------------------------------------------------

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.one_line_view, R.id.oneLineTextID, categories);
        uCategorySP.setAdapter(adapter);
        sCategorySP.setAdapter(adapter);

    }

    public void uploadProjectButton(View view)
    {
        uploadProjectLayout.setVisibility(View.VISIBLE);
        //submitProblemLayout.setVisibility(View.INVISIBLE);
        submitProblem.setVisibility(View.INVISIBLE);
    }

    public void submitProblemButton(View view)
    {
        submitProblemLayout.setVisibility(View.VISIBLE);
        //uploadProjectLayout.setVisibility(View.INVISIBLE);
        uploadProject.setVisibility(View.INVISIBLE);
    }

    public void UploadProjectSubmit(View view)
    {
        theProgressbar.setVisibility(View.VISIBLE);

        uTitle = uTitleET.getText().toString();
        uCategory = uCategorySP.getSelectedItem().toString();
        uShort = uShortET.getText().toString();
        uLong = uLongET.getText().toString();

        if(c.isChecked())
        {
            uProgrammingLanguage = uProgrammingLanguage+"C,";
        }

        if(java.isChecked())
        {
            uProgrammingLanguage = uProgrammingLanguage+"JAVA,";
        }

        if(python.isChecked())
        {
            uProgrammingLanguage = uProgrammingLanguage+"Python,";
        }

        if(rubi.isChecked())
        {
            uProgrammingLanguage = uProgrammingLanguage+"Rubi,";
        }

        if(kotlin.isChecked())
        {
            uProgrammingLanguage = uProgrammingLanguage+"Kotlin,";
        }


        if(html.isChecked())
        {
            uWebLanguage = uWebLanguage+"HTML,";
        }

        if(css.isChecked())
        {
            uWebLanguage = uWebLanguage+"CSS,";
        }

        if(php.isChecked())
        {
            uWebLanguage = uWebLanguage+"PHP,";
        }

        if(js.isChecked())
        {
            uWebLanguage = uWebLanguage+"JS,";
        }


        if(android.isChecked())
        {
            uFramework = uFramework+"Android,";
        }

        if(ios.isChecked())
        {
            uFramework = uFramework+"iOS,";
        }

        if(spring.isChecked())
        {
            uFramework = uFramework+"Spring,";
        }

        if(wordpress.isChecked())
        {
            uFramework = uFramework+"WordPress,";
        }

        if(django.isChecked())
        {
            uFramework = uFramework+"Django,";
        }


        if(mysql.isChecked())
        {
            uDatabase = uDatabase+"MySQL,";
        }

        if(sqlite.isChecked())
        {
            uDatabase = uDatabase+"SQLite,";
        }

        if(firebase.isChecked())
        {
            uDatabase = uDatabase+"Firebase,";
        }

        if(oracle.isChecked())
        {
            uDatabase = uDatabase+"Oracle,";
        }


        ProjectDetails projectDetails = new ProjectDetails(uTitle, uCategory, uShort, uLong, uProgrammingLanguage, uWebLanguage, uFramework, uDatabase, theUserName, theUserID);

        FirebaseDatabase.getInstance().getReference("Projects").push()
                .setValue(projectDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CreateNewActivity.this, "The Project Uploaded", Toast.LENGTH_SHORT).show();

                theProgressbar.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(CreateNewActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



        theProgressbar.setVisibility(View.INVISIBLE);
    }

    public void SubmitProblem(View view)
    {
        theProgressbar.setVisibility(View.VISIBLE);

        sTitle = sTitleET.getText().toString();
        sCategory = sCategorySP.getSelectedItem().toString();
        sShort = sShortET.getText().toString();
        sLong = sLongET.getText().toString();
        if(sHire.isChecked())
            sHireOrBuy = "Want To Hire Developer";
        else
            sHireOrBuy = "Want To Buy a Project";


        ProblemDetails problemDetails = new ProblemDetails(sTitle, sCategory, sShort, sLong, sHireOrBuy, theUserName, theUserID);

        FirebaseDatabase.getInstance().getReference("Problems").push()
                .setValue(problemDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CreateNewActivity.this, "The Problem Submitted", Toast.LENGTH_SHORT).show();

                theProgressbar.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(CreateNewActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });




        theProgressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {


        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        theUserID = mAuth.getUid();

        databaseReference = firebaseDatabase.getReference().child("UserList").child(mAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                theUserName = dataSnapshot.child("fullName").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        super.onStart();
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }


}