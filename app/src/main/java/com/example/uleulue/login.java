package com.example.uleulue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextemail, editTextPassword;
    private TextView  register;
    private Button signin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        signin = (Button) findViewById(R.id.btnLogin2);
        signin.setOnClickListener(this);

        editTextemail = (EditText) findViewById(R.id.inputEmail2);
        editTextPassword = (EditText) findViewById(R.id.inputPassword2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register:
                startActivity(new Intent(this,studentreg.class));

                break;

            case R.id.btnLogin2:
                userLogin();
                break;
        }

    }

    private void userLogin() {

        String email = editTextemail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextemail.setError("email; required");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("please provide valid email");
            editTextemail.requestFocus();
            return;

        }
        if(password.isEmpty())
        {
            editTextPassword.setError("password required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editTextPassword.setError("min pass require length should be 6");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {
                        startActivity(new Intent(login.this,HomeStudents.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(login.this,"check you mail to confirm it",Toast.LENGTH_LONG).show();
                    }

                }else
                {
                    Toast.makeText(login.this,"failed to login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}