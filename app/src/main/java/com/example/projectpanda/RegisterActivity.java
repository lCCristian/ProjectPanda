package com.example.projectpanda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {

    //views
    EditText mEmailET, mPasswordET;
    Button mRegisterBtn;
    TextView mLoginTV;
    //Declare an instance of FirebaseAuth

    //progressbar to display while registering user
    ProgressDialog progressDialog;

    // Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init
        mEmailET = findViewById(R.id.emailET);
        mPasswordET = findViewById(R.id.passwordET);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginTV = findViewById(R.id.registerTV);

        // Initialize progress dialog and set its message
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User ...");

        //Initialise the FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input email , password
                String email = mEmailET.getText().toString().trim();
                String password = mPasswordET.getText().toString().trim();
                //validate
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    //set error and focus to email edittext
                    mEmailET.setError("Invalid Email");
                    mEmailET.setFocusable(true);
                } else if (password.length() < 6) {
                    //set error and focus to password edittext
                    mPasswordET.setError("Password length must have at least 6 characters");
                    mPasswordET.setFocusable(true);
                } else {
                    //register the user
                    registerUser(email, password);
                }
            }
        });


        // when LoginTV is pressed - send user to LoginActivity
        mLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });


    }

    private void registerUser(String email, String password) {
        //mail and password pattern is valid , show progress dialog and start registering user
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, dismiss dialog and start
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Get user email and uid from auth
                            String email = user.getEmail();
                            String uid = user.getUid();
                            //When user is registered store user info in firebase realtime database too
                            //using HashMap
                            HashMap<Object,String> hashMap = new HashMap<>();
                            //put info in hashmap
                            hashMap.put("email",email);
                            hashMap.put("uid",uid);
                            hashMap.put("name","");// later
                            hashMap.put("phone","");// later
                            hashMap.put("image","");// later
                            hashMap.put("cover","");
                            //firebase database instance
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            //path to store user data named "Users"
                            DatabaseReference reference = database.getReference("Users");
                            //put data within hashmap in database
                            reference.child(uid).setValue(hashMap);






                            Toast.makeText(RegisterActivity.this, "Registered successful ...\n" + user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //error, dismiss progress dialog and get and show the error message
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}