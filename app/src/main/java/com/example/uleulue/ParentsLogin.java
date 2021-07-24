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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ParentsLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText dTextemail, dTextPassword;
    private TextView  dregister;
    private Button signin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_login);
        dregister = (TextView) findViewById(R.id.register);
        dregister.setOnClickListener(this);
        signin = (Button) findViewById(R.id.btnLogin2);
        signin.setOnClickListener(this);

        dTextemail = (EditText) findViewById(R.id.inputEmail2);
        dTextPassword = (EditText) findViewById(R.id.inputPassword2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register:
                startActivity(new Intent(this, matashreepitashreeRegister.class));

                break;

            case R.id.btnLogin2:
                userLogin();
                break;
        }

    }

    private void userLogin() {

        String email = dTextemail.getText().toString().trim();
        String password = dTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            dTextemail.setError("email required");
            dTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            dTextemail.setError("please provide valid email");
            dTextemail.requestFocus();
            return;

        }
        if(password.isEmpty())
        {
            dTextPassword.setError("password required");
            dTextPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            dTextPassword.setError("min pass require length should be 6");
            dTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {startActivity(new Intent(ParentsLogin.this,ParentsHome.class));
                        finish();}
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(ParentsLogin.this,"Check your mail to confirm it",Toast.LENGTH_LONG).show();
                    }

                }else
                {
                    Toast.makeText(ParentsLogin.this,"Failed to login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}