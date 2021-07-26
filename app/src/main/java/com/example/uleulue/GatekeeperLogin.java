package com.example.uleulue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class GatekeeperLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText editemail, editPassword;
    private TextView ttregister, forgotpasswor1d;
    private Button signinn;
    private FirebaseAuth mAuth;
    private ProgressBar progresssBar;
    String checkbox4;
    CheckBox remember4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gatekeeper_login);

        ttregister = (TextView) findViewById(R.id.cregister2);
        ttregister.setOnClickListener(this);
        forgotpasswor1d = (TextView) findViewById(R.id.cforgotPassword);
        forgotpasswor1d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GatekeeperLogin.this, forgotpassword.class));
            }
        });
        signinn = (Button) findViewById(R.id.clogin);
        signinn.setOnClickListener(this);
        remember4=findViewById(R.id.remember4);
        editemail = (EditText) findViewById(R.id.Cemail);
        editPassword = (EditText) findViewById(R.id.cPassword3);
        progresssBar = (ProgressBar) findViewById(R.id.cprogressBar3);
        mAuth = FirebaseAuth.getInstance();

        SharedPreferences preferences4 = getSharedPreferences("checkbox4",MODE_PRIVATE);
        checkbox4= preferences4.getString("remember4","");

        if(checkbox4.equals("true"))
        {
            startActivity(new Intent(GatekeeperLogin.this,GatekeeperHome.class));
        }
        else if (checkbox4.equals("false"))
        {
            Toast.makeText(this,"please login",Toast.LENGTH_LONG).show();
        }

        remember4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    SharedPreferences preferences4 = getSharedPreferences("checkbox4",MODE_PRIVATE);
                    SharedPreferences.Editor editor4 = preferences4.edit();
                    editor4.putString("remember4","true");
                    editor4.apply();
                    Toast.makeText(GatekeeperLogin.this,"checked",Toast.LENGTH_LONG).show();
                }
                else if(!compoundButton.isChecked())
                {
                    SharedPreferences preferences4= getSharedPreferences("checkbox4",MODE_PRIVATE);
                    SharedPreferences.Editor editor4 = preferences4.edit();
                    editor4.putString("remember4","false");
                    editor4.apply();
                    Toast.makeText(GatekeeperLogin.this,"Unchecked",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cregister2:
                startActivity(new Intent(this, GatekeeperRegister.class));
                break;

            case R.id.clogin:
                userLoginn();
                break;
            case R.id.cforgotPassword:
                startActivity(new Intent(this,forgotpassword.class));
                break;

        }

    }

    private void userLoginn() {

        String email = editemail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editemail.setError("email required");
            editemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("please provide valid email");
            editemail.requestFocus();
            return;

        }
        if (password.isEmpty()) {
            editPassword.setError("password required");
            editPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editPassword.setError("min pass require length should be 6");
            editPassword.requestFocus();
            return;
        }
        progresssBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        startActivity(new Intent(GatekeeperLogin.this, GatekeeperHome
                                .class));
                    } else
                        user.sendEmailVerification();
                    Toast.makeText(GatekeeperLogin.this, "check you mail to confirm it", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(GatekeeperLogin.this, "failed to login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }}

 