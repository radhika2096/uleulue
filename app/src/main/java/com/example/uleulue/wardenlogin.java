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

public class wardenlogin extends AppCompatActivity  implements View.OnClickListener{
    private EditText edTextemail, edTextPassword;
    private TextView edregister;
    private Button edsignin;
    private FirebaseAuth mAuth;
    private ProgressBar edprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardenlogin);
        edregister = (TextView) findViewById(R.id.lreg);
        edregister.setOnClickListener(this);
        edsignin= (Button) findViewById(R.id.lbutton);
        edsignin.setOnClickListener(this);

        edTextemail = (EditText) findViewById(R.id.lemail);
        edTextPassword = (EditText) findViewById(R.id.lpass);
        edprogressBar = (ProgressBar) findViewById(R.id.lprogress);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.lreg:
                startActivity(new Intent(this,wardenregisterpage.class));
                break;

            case R.id.lbutton:
                userLogin();
                break;
        }

    }

    private void userLogin() {

        String email = edTextemail.getText().toString().trim();
        String password = edTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            edTextemail.setError("emai; required");
            edTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edTextemail.setError("please provide valid email");
            edTextemail.requestFocus();
            return;

        }
        if(password.isEmpty())
        {
            edTextPassword.setError("password required");
            edTextPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            edTextPassword.setError("min pass require length should be 6");
            edTextPassword.requestFocus();
            return;
        }
        edprogressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {
                        startActivity(new Intent(wardenlogin.this,HomeWarden.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(wardenlogin.this,"check you mail to confirm it",Toast.LENGTH_LONG).show();
                    }

                }else
                {
                    Toast.makeText(wardenlogin.this,"failed to login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
