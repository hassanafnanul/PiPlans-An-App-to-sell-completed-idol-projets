package com.afnanulcoder.piplans;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpForm extends AppCompatActivity {

    FirebaseAuth mAuth;
    //---------------------------------------

    String[] educationalDegrees = new String[]{"Select Educational Degree", "S.S.C", "H.S.C", "B.Sc.", "B.Com.", "B.A.", "M.Sc.", "M.Com", "M.A", "P.Hd.", "Others"};
    String[] allCountries = new String[]{"Select Country","Bangladesh", "Afghanistan","Albania","Algeria","Andorra","Angola","Antigua and Barbuda","Argentina","Armenia","Australia","Austria","Azerbaijan", "Bahamas","Bahrain","Barbados","Belarus","Belgium","Belize","Benin","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina Faso","Burundi", "Cabo Verde","Cambodia","Cameroon","Canada","Central African Republic","Chad","Chile","China","Colombia","Comoros","Congo","Costa Rica","Côte d’Ivoire","Croatia","Cuba","Cyprus","Czech Republic", "Denmark","Djibouti","Dominica","Dominican Republic", "East Timor (Timor-Leste)","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Eswatini","Ethiopia", "Fiji","Finland","France", "Gabon","The Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana", "Haiti","Honduras","Hungary", "Iceland","India","Indonesia","Iran","Iraq","Ireland","Israel","Italy", "Jamaica","Japan","Jordan", "Kazakhstan","Kenya","Kiribati","Korea","North Korea", "South Korea","Kosovo","Kuwait","Kyrgyzstan", "Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg", "Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar", "Namibia","Nauru","Nepal","Netherlands","New Zealand","Nicaragua","Niger","Nigeria","North Macedonia","Norway", "Oman", "Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal", "Qatar", "Romania","Russia","Rwanda", "Saint Kitts and Nevis","Saint Lucia","Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","Spain","Sri Lanka","Sudan","South Sudan","Suriname","Sweden","Switzerland","Syria", "Taiwan","Tajikistan","Tanzania","Thailand","Togo","Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu", "Uganda","Ukraine","United Arab Emirates","United Kingdom","United States","Uruguay","Uzbekistan", "Vanuatu","Vatican City","VenezuelaVietnam", "Yemen", "Zambia","Zimbabwe"};

    ProgressBar progressBar;
    EditText fullNameET, organisationET, ageET, eduDegreeET, skypeET, gitHubET, emailET, passwordET, confPasswordET;
    RadioGroup genderRG;
    RadioButton maleRB, femaleRB;
    Spinner countrySP;

    String fullName = "", organisation = "", gender = "", age = "", eduDegree = "", country = "", skype = "", gitHub = "", email = "", password = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.theProgressbarID);

        fullNameET = findViewById(R.id.nameID);
        organisationET = findViewById(R.id.organizationID);
        ageET = findViewById(R.id.ageID);
        eduDegreeET = findViewById(R.id.eduDegreeID);
        countrySP = findViewById(R.id.countryID);
        skypeET = findViewById(R.id.skypeIdID);
        gitHubET = findViewById(R.id.gitProfileID);
        emailET = findViewById(R.id.emailID);
        passwordET = findViewById(R.id.passwordID);
        confPasswordET = findViewById(R.id.confPasswordID);

        genderRG = findViewById(R.id.genderID);
        maleRB = findViewById(R.id.maleRbID);
        femaleRB = findViewById(R.id.femaleRbID);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.one_line_view, R.id.oneLineTextID, allCountries);
        countrySP.setAdapter(adapter);


    }


    public void SignUp(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Progressing!!! Please Wait", Toast.LENGTH_SHORT).show();

        fullName = fullNameET.getText().toString().trim();
        organisation = organisationET.getText().toString().trim();
        age = ageET.getText().toString().trim();
        country = countrySP.getSelectedItem().toString();
        skype = skypeET.getText().toString().trim();
        gitHub = gitHubET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        eduDegree = eduDegreeET.getText().toString().trim();
        password = passwordET.getText().toString().trim();


        if(maleRB.isChecked())
        {
            gender = "Male";
        }
        else if(femaleRB.isChecked())
        {
            gender = "Female";
        }


        if(!password.equals(confPasswordET.getText().toString().trim()))
        {
            confPasswordET.setError("Password Did Not Matched");
            confPasswordET.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            progressBar.setVisibility(View.INVISIBLE);
            emailET.setError("Enter Valid Email");
            emailET.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            progressBar.setVisibility(View.INVISIBLE);
            passwordET.setError("Password Must Be More Than 6 Character");
            passwordET.requestFocus();
            return;
        }

        if(fullName.equals("") || organisation.equals("") || gender.equals("") || age.equals("") || country.equals("Select Country") || email.equals("") || eduDegree.equals("Select Educational Degree") || password.equals(""))
        {
            Toast.makeText(this, "Please Fill Up Mandatory Boxes", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        finalSignUp();

    }


    public void finalSignUp()
    {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    UserInformation userInformation = new UserInformation(fullName, organisation, gender, age, eduDegree, country, skype, gitHub, email, "0", "0");

                    FirebaseDatabase.getInstance().getReference("UserList")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(userInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SignUpForm.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(SignUpForm.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(SignUpForm.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(SignUpForm.this, "Failed\nCheck Your Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        progressBar.setVisibility(View.INVISIBLE);
    }

}
