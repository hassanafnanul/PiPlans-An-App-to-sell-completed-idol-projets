package com.afnanulcoder.piplans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllMessageChecker extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    //---------------------------------------------


    private ListView allMessagesListView;
    private List<UserInformation> messagesList;
    private List<String> sendersList;
    //private String[] sendersList;
    private ArrayAdapter<String> adapter;
    UserInformation userInformation;


    String amarNam = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_message_checker);

        progressBar = findViewById(R.id.progressBarID);
        progressBar.setVisibility(View.VISIBLE);

        mAuth = FirebaseAuth.getInstance();


        allMessagesListView = findViewById(R.id.allMessagesListID);
        userInformation = new UserInformation();
        messagesList = new ArrayList<UserInformation>();
        sendersList = new ArrayList<String>();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserList").child(mAuth.getUid()).child("allMessageSender");





        allMessagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(sendersList.get(position).equals("No Message"))
                {
                    Toast.makeText(AllMessageChecker.this, "No Message", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(AllMessageChecker.this, MessageActivity.class);
                intent.putExtra("senderID", mAuth.getUid());
                intent.putExtra("senderName", amarNam); //---------------------------------------Vul kora ase nam e----------------
                intent.putExtra("ReceiverID", messagesList.get(position).memberKey);
                intent.putExtra("ReceiverName", sendersList.get(position));
                intent.putExtra("fromWhere", "AllMessageChecker");
                startActivity(intent);

            }


        });



        // handler.postDelayed(runnable, 5000);


    }


    @Override
    protected void onStart() {

        FirebaseDatabase.getInstance().getReference("UserList").child(mAuth.getUid()).child("fullName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                amarNam = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                messagesList.clear();
                sendersList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {

                    UserInformation userInformation = new UserInformation(dataSnapshot1.getValue().toString(), dataSnapshot1.getKey().toString());


                    //UserInformations userInformations = dataSnapshot1.getValue(UserInformations.class);
                    messagesList.add(userInformation);
                    sendersList.add(userInformation.getMemberName());
                }


                if(messagesList.size() == 0)
                {
                    sendersList.add("No Message");
                }

                allMessagesListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        adapter = new ArrayAdapter<String>(AllMessageChecker.this, R.layout.one_line_view, R.id.oneLineTextID, sendersList);
        progressBar.setVisibility(View.INVISIBLE);


        super.onStart();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}