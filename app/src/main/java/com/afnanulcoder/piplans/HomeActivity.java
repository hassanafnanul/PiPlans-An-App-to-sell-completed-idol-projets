package com.afnanulcoder.piplans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    SearchView searchSV;



    //--------------------------------------------------

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1;
    //-----------------------------------------------

    private ListView projectsListView, problemsListView;
    private List<ProjectDetails> projectList;
    private List<ProblemDetails> problemList;
    private CustomAdapterForProject customAdapterForProject;
    private CustomAdapterForProblem customAdapterForProblem;
    private ProjectDetails projectDetails;
    private ProblemDetails problemDetails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchSV = findViewById(R.id.searchSvID);



        projectsListView = findViewById(R.id.projectListViewID);
        problemsListView = findViewById(R.id.problemListViewID);

        projectDetails = new ProjectDetails();
        problemDetails = new ProblemDetails();

        databaseReference = FirebaseDatabase.getInstance().getReference("Projects");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Problems");

        projectList = new ArrayList<>();
        problemList = new ArrayList<>();

        customAdapterForProject = new CustomAdapterForProject(HomeActivity.this, projectList);
        customAdapterForProblem = new CustomAdapterForProblem(HomeActivity.this, problemList);



        projectsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(HomeActivity.this, projectList.get(position).getUploaderName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, ProjectsDetailsView.class);

                intent.putExtra("theProjectTitle", projectList.get(position).getuTitle());
                intent.putExtra("theProjectCategory", projectList.get(position).getuCategory());
                intent.putExtra("theProjectUploader", projectList.get(position).getUploaderName());
                intent.putExtra("theProjectLong", projectList.get(position).getuLong());
                intent.putExtra("theProjectProgramming", projectList.get(position).getuProgrammingLanguage());
                intent.putExtra("theProjectWeb", projectList.get(position).getuWebLanguage());
                intent.putExtra("theProjectFramework", projectList.get(position).getuFramework());
                intent.putExtra("theProjectDatabase", projectList.get(position).getuDatabase());

                intent.putExtra("theProjectUploaderKey", projectList.get(position).getUploaderID());

                startActivity(intent);
            }
        });

        problemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(HomeActivity.this, problemList.get(position).getSubmitterName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, ProblemsDetailsView.class);

                intent.putExtra("theProblemTitle", problemList.get(position).getsTitle());
                intent.putExtra("theProblemCategory", problemList.get(position).getsCategory());
                intent.putExtra("theProblemSubmitter", problemList.get(position).getSubmitterName());
                intent.putExtra("theProblemLong", problemList.get(position).getsLong());
                intent.putExtra("theProblemHireOrBuy", problemList.get(position).getsHireOrBuy());

                intent.putExtra("theProblemSubmitterKey", problemList.get(position).getSubmitterID());

                startActivity(intent);
            }
        });



        /*
        //------------SearchView---------------

        searchSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //Toast.makeText(HomeActivity.this, newText, Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(newText))
                {
                    customAdapterForProject.filter("");
                    projectsListView.clearTextFilter();
                }
                else
                {
                    customAdapterForProject.filter(newText);
                }


                return true;
            }
        });

         */




    }

    public void ProPicTouched(View view)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        View myView = getLayoutInflater().inflate(R.layout.profile_pic_touched_alert_diagram_view, null);

        Button goToProfileButton = myView.findViewById(R.id.goToProfileButtonID);
        Button settingsButton = myView.findViewById(R.id.settingsButtonID);
        Button aboutButton = myView.findViewById(R.id.aboutButtonID);
        Button logOutButton = myView.findViewById(R.id.logOutButtonID);

        alert.setView(myView);
        final AlertDialog alertDialog = alert.create();

        goToProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, Profile.class);
                //intent.putExtra("onnoderProfileName", theProblemSubmitter);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Settings Under Development", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(HomeActivity.this, About.class));

            alertDialog.dismiss();
        }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(HomeActivity.this, "LogOut Successful", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public void CreateNew(View view)
    {
        startActivity(new Intent(this, CreateNewActivity.class));
    }


    @Override
    protected void onStart() {

        //-----------------------------Showing PeachRoom Part-------------- (It Is Causing Crash...)
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                projectList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    ProjectDetails projectDetails = dataSnapshot1.getValue(ProjectDetails.class);
                    projectList.add(projectDetails);
                }

                projectsListView.setAdapter(customAdapterForProject);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                problemList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    ProblemDetails problemDetails = dataSnapshot1.getValue(ProblemDetails.class);
                    problemList.add(problemDetails);
                }

                problemsListView.setAdapter(customAdapterForProblem);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        super.onStart();
    }
}