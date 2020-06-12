package com.afnanulcoder.piplans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OnnoderProfile extends AppCompatActivity {

    String onnoderProfileKey;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1, databaseReference2;
    String currentUserName = "";

    // ---------------------
    TextView nameTV, eduDegreeTV, organisationTV, ageTV, genderTV, countryTV, emailTV, skypeTV, githubTV, numberOfProjectsTV, numberOfProblemsTV;
    LinearLayout personalDetailsLayout, contractDetailsLayout;

    String fullName, organisation, gender, age, eduDegree, country, skype, gitHub, email, numberOfProjects, numberOfProblems;

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
        setContentView(R.layout.activity_onnoder_profile);

        onnoderProfileKey = getIntent().getStringExtra("onnoderProfileKey");

        nameTV = findViewById(R.id.userNameID);
        eduDegreeTV = findViewById(R.id.userEduDegreeID);
        organisationTV = findViewById(R.id.userOrganizationID);
        ageTV = findViewById(R.id.userAgeID);
        genderTV = findViewById(R.id.userGenderID);
        countryTV = findViewById(R.id.userCountryID);
        emailTV = findViewById(R.id.userEmailID);
        skypeTV = findViewById(R.id.userSkypeID);
        githubTV = findViewById(R.id.userGithubID);
        numberOfProjectsTV = findViewById(R.id.userNumberOfProjectsID);
        numberOfProblemsTV = findViewById(R.id.userNumberOfProblemsID);

        personalDetailsLayout = findViewById(R.id.personalDetailsID);
        contractDetailsLayout = findViewById(R.id.contractDetailsID);





        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("UserList").child(onnoderProfileKey);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fullName = dataSnapshot.child("fullName").getValue().toString();
                organisation = dataSnapshot.child("organisation").getValue().toString();
                gender = dataSnapshot.child("gender").getValue().toString();
                age = dataSnapshot.child("age").getValue().toString();
                eduDegree = dataSnapshot.child("eduDegree").getValue().toString();
                country = dataSnapshot.child("country").getValue().toString();
                skype = dataSnapshot.child("skype").getValue().toString();
                gitHub = dataSnapshot.child("gitHub").getValue().toString();
                email = dataSnapshot.child("email").getValue().toString();
                numberOfProjects = dataSnapshot.child("numberOfProjects").getValue().toString();
                numberOfProblems = dataSnapshot.child("numberOfProblems").getValue().toString();


                //---------Setting The Values--------

                nameTV.setText(fullName);
                eduDegreeTV.setText(eduDegree);
                organisationTV.setText(organisation);
                ageTV.setText("Age:\n"+age);
                genderTV.setText("Gender:\n"+gender);
                countryTV.setText("Country:\n"+country);
                emailTV.setText("E-mail:\n"+email);
                skypeTV.setText("Skype:\n"+skype);
                githubTV.setText("Github:\n"+gitHub);
                numberOfProjectsTV.setText(numberOfProjects);
                numberOfProblemsTV.setText(numberOfProblems);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });



        //---------------For Message Current user name requared-----------
        {
            mAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("UserList").child(mAuth.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    currentUserName = dataSnapshot.child("fullName").getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        ShowPersonalProjectsAndProblems();



    }

    public void openDetails(View view)
    {
        Button btn = (Button)view;

        if(btn.getText().toString().equals("See Personal Details"))
        {
            personalDetailsLayout.setVisibility(View.VISIBLE);
            btn.setText("Hide Personal Details");
        }
        else if(btn.getText().toString().equals("See Contract Details"))
        {
            contractDetailsLayout.setVisibility(View.VISIBLE);
            btn.setText("Hide Contract Details");
        }
        else if(btn.getText().toString().equals("Hide Personal Details"))
        {
            personalDetailsLayout.setVisibility(View.INVISIBLE);
            btn.setText("See Personal Details");
        }else if(btn.getText().toString().equals("Hide Contract Details"))
        {
            contractDetailsLayout.setVisibility(View.INVISIBLE);
            btn.setText("See Contract Details");
        }
        else
        {
            Toast.makeText(this, "Any Unexpected Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void GoToMessage(View view)
    {

        if(currentUserName.equals(fullName))
        {
            Toast.makeText(this, "Sorry!!! You cannot Message Yourself", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra("senderID", mAuth.getUid());
            intent.putExtra("senderName", currentUserName);
            intent.putExtra("ReceiverID", onnoderProfileKey);
            intent.putExtra("ReceiverName", fullName);
            startActivity(intent);
        }

    }

    @Override
    protected void onStart() {





        super.onStart();
    }

    public void ShowPersonalProjectsAndProblems()
    {
        final String onnoderProfileName = getIntent().getStringExtra("onnoderProfileName");

        projectsListView = findViewById(R.id.userProjectsListViewID);
        problemsListView = findViewById(R.id.userProblemsListViewID);

        projectDetails = new ProjectDetails();
        problemDetails = new ProblemDetails();

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Projects");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Problems");

        projectList = new ArrayList<>();
        problemList = new ArrayList<>();

        customAdapterForProject = new CustomAdapterForProject(OnnoderProfile.this, projectList);
        customAdapterForProblem = new CustomAdapterForProblem(OnnoderProfile.this, problemList);



        //-----------------------------Showing PeachRoom Part-------------- (It Is Causing Crash...)
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                projectList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    ProjectDetails projectDetails = dataSnapshot1.getValue(ProjectDetails.class);

                    if(projectDetails.getUploaderName().equals(onnoderProfileName))
                    {
                        projectList.add(projectDetails);
                    }

                }

                projectsListView.setAdapter(customAdapterForProject);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                problemList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    ProblemDetails problemDetails = dataSnapshot1.getValue(ProblemDetails.class);


                    if(problemDetails.getSubmitterName().equals(onnoderProfileName))
                    {
                        problemList.add(problemDetails);
                    }

                }

                problemsListView.setAdapter(customAdapterForProblem);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //---------------------------------------------------


        projectsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(HomeActivity.this, projectList.get(position).getUploaderName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OnnoderProfile.this, ProjectsDetailsView.class);

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

                Intent intent = new Intent(OnnoderProfile.this, ProblemsDetailsView.class);

                intent.putExtra("theProblemTitle", problemList.get(position).getsTitle());
                intent.putExtra("theProblemCategory", problemList.get(position).getsCategory());
                intent.putExtra("theProblemSubmitter", problemList.get(position).getSubmitterName());
                intent.putExtra("theProblemLong", problemList.get(position).getsLong());
                intent.putExtra("theProblemHireOrBuy", problemList.get(position).getsHireOrBuy());

                intent.putExtra("theProblemSubmitterKey", problemList.get(position).getSubmitterID());

                startActivity(intent);
            }
        });





    }


}