package com.example.uleulue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerStudentPage extends AppCompatActivity {

    private EditText memail, mPass;
    private TextView mtextView;
    private Button signupButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student_page);

        memail = findViewById(R.id.Emailreg);
        mPass = findViewById(R.id.passwordreg);
        signupButton = findViewById(R.id.signUp);
        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createUser();
            }


        });
    }
        private void createUser(){

            String email = memail.getText().toString();
            String pass = mPass.getText().toString();

            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!pass.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(registerStudentPage.this,"succesfull", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(registerStudentPage.this, login_Student.class));
                                    finish();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            Toast.makeText(registerStudentPage.this,"registrationErroe",Toast.LENGTH_LONG);
                        }
                    });

                } else {
                    mPass.setError("empty fields are not allowed");
                }

            } else if (email.isEmpty()) {
                memail.setError("empty fields not allowed");
            } else {
                memail.setError("please enter correct email");
            }
        }
    }

