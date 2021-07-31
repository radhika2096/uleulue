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
import com.google.firebase.database.FirebaseDatabase;

public class GatekeeperRegister extends AppCompatActivity implements View.OnClickListener{
    private EditText hname, hemail, hpassword, hphonenumber,chpassword2;
    private TextView hregister;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatekeeper_register);
        mAuth = FirebaseAuth.getInstance();
        hregister = (Button) findViewById(R.id.chregister);
        hregister.setOnClickListener(this);
        hname = (EditText) findViewById(R.id.chname);
        hemail = (EditText) findViewById(R.id.chemail);

        hpassword = (EditText) findViewById(R.id.chpassword);
        hphonenumber = (EditText) findViewById(R.id.chphoneno);
        chpassword2 = (EditText) findViewById(R.id.chpassword2);
    }
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.chregister:
                    Registerr();
            }
        }

        private void Registerr()
        {
            String email1 = hemail.getText().toString();
            String name1 = hname.getText().toString();
            String phone1 = hphonenumber.getText().toString();
            String password1 = hpassword.getText().toString();
            String password2 = chpassword2.getText().toString();

            if(name1.isEmpty())
            {
                hname.setError("full name required");
                hname.requestFocus();
                return;

            }
                if (phone1.isEmpty()) {
                    hphonenumber.setError("phonenumber required");
                    hphonenumber.requestFocus();
                    return;
                }
                if (email1.isEmpty()) {
                    hemail.setError("email required");
                    hemail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
                    hemail.setError("please provide valid email");
                    hemail.requestFocus();
                    return;

                }
                if (password1.length() < 6) {
                    hpassword.setError("min pass require length should be 6");
                    hpassword.requestFocus();
                    return;
                }
            if(password2.isEmpty())
            {
                chpassword2.setError("Enter Confirm Password");
                chpassword2.requestFocus();
                return;
            }
            if(password2.equals(hpassword)) {
                chpassword2.setError("Passwords do not match.Kindly recheck");
                chpassword2.requestFocus();
                return;

            }
            mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user3 useR3 = new user3(name1, email1, phone1);
                        FirebaseDatabase.getInstance().getReference("gusers").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(useR3).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(GatekeeperRegister.this, "registered", Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(GatekeeperRegister.this,GatekeeperLogin.class));
                                } else {
                                    Toast.makeText(GatekeeperRegister.this, "registration failed", Toast.LENGTH_LONG).show();

                                }
                            }
                        });


                    } else {
                        Toast.makeText(GatekeeperRegister.this, "registration failed due", Toast.LENGTH_LONG).show();

                    }
                }

            });}}