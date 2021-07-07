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

public class GatekeeperLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText editemail, editPassword;
    private TextView  ttregister;
    private Button signinn;
    private FirebaseAuth mAuth;
    private ProgressBar progresssBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gatekeeper_login);

        ttregister = (TextView) findViewById(R.id.cregister2);
        ttregister.setOnClickListener(this);
        signinn = (Button) findViewById(R.id.clogin);
        signinn.setOnClickListener(this);

        editemail = (EditText) findViewById(R.id.Cemail);
        editPassword = (EditText) findViewById(R.id.cPassword3);
        progresssBar = (ProgressBar) findViewById(R.id.cprogressBar3);
        mAuth = FirebaseAuth.getInstance();
    }

@Override
public void onClick(View v) {
    switch (v.getId())
    {
        case R.id.cregister2:
            startActivity(new Intent(this,GatekeeperRegister.class));
            break;

        case R.id.clogin:
            userLoginn();
            break;
    }

}

    private void userLoginn() {

        String email = editemail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editemail.setError("email required");
            editemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("please provide valid email");
            editemail.requestFocus();
            return;

        }
        if(password.isEmpty())
        {
            editPassword.setError("password required");
            editPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editPassword.setError("min pass require length should be 6");
            editPassword.requestFocus();
            return;
        }
        progresssBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {
                        startActivity(new Intent(GatekeeperLogin.this,HomeStudents.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(GatekeeperLogin.this,"check you mail to confirm it",Toast.LENGTH_LONG).show();
                    }

                }else
                {
                    Toast.makeText(GatekeeperLogin.this,"failed to login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}