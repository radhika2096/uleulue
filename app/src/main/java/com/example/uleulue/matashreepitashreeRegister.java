
package com.example.uleulue;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.util.Patterns;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.FirebaseDatabase;

public class matashreepitashreeRegister extends AppCompatActivity implements View.OnClickListener {

    private EditText dTextname, dTextemail, dTextpassword, dTextphonenumber, dTextusn,dTextstudentname,dTextcpassword;
    private TextView wapasloginpejaobutton;
    private Button dregisteruser;
    private ProgressBar dprogressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matashreepitashree_register);
        mAuth = FirebaseAuth.getInstance();
        dregisteruser = (Button) findViewById(R.id.preg);
        dregisteruser.setOnClickListener(this);
        dTextname = (EditText) findViewById(R.id.inputUsername);
        dTextemail = (EditText) findViewById(R.id.inputEmail3);
        dTextpassword = (EditText) findViewById(R.id.inputPassword);
        dTextphonenumber = (EditText) findViewById(R.id.contact);
        dTextusn = (EditText) findViewById(R.id.inputUsn);
        dTextstudentname = (EditText) findViewById(R.id.studentsName);
        wapasloginpejaobutton = (TextView) findViewById(R.id.alreadyhave);
        dTextcpassword = (EditText) findViewById(R.id.inputConfirmPassword);

        dprogressBar = (ProgressBar) findViewById(R.id.progressBar3);
    }
    public void lechaloUsPaar(View view)
    {
        startActivity(new Intent(matashreepitashreeRegister.this,ParentsLogin.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.preg:
                DRegisteruser();

        }
    }

    private void DRegisteruser() {

        String emaill = dTextemail.getText().toString();
        String usnn = dTextusn.getText().toString();
        String parentsnamee = dTextname.getText().toString();
        String phonee = dTextphonenumber.getText().toString();
        String passwordd = dTextpassword.getText().toString();
        String studentname = dTextstudentname.getText().toString();
        String confirmpassword = dTextcpassword.getText().toString();


        if(parentsnamee.isEmpty())
        {
            dTextname.setError("Full name required");
            dTextname.requestFocus();
            return;
        }
        if(passwordd.isEmpty())
        {
            dTextpassword.setError("Password required");
            dTextpassword.requestFocus();
            return;
        }
        if(usnn.isEmpty())
        {
            dTextusn.setError("Usn required");
            dTextusn.requestFocus();
            return;
        }
        if(phonee.isEmpty())
        {
            dTextphonenumber.setError("Phone number required");
            dTextphonenumber.requestFocus();
            return;
        }
        if(emaill.isEmpty())
        {
            dTextemail.setError(" Valid Email required");
            dTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()) {
            dTextemail.setError("Please provide valid email");
            dTextemail.requestFocus();
            return;

        }
        if(passwordd.length()<6)
        {
            dTextpassword.setError("Minimum pass require length should be 6");
            dTextpassword.requestFocus();
            return;
        }
        if(studentname.isEmpty())
        {
            dTextstudentname.setError("Student Name is required");
            dTextstudentname.requestFocus();
            return;
        }

        if(confirmpassword.isEmpty())
        {
            dTextcpassword.setError("Enter Confirm Password");
            dTextcpassword.requestFocus();
            return;
        }
        if(!confirmpassword.equals(passwordd))
        {
            dTextcpassword.setError("Passwords do not match.Kindly recheck");
            dTextcpassword.requestFocus();
            return;
        }

        dprogressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emaill,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user4 useR4 = new user4(parentsnamee, emaill, phonee,studentname,usnn);
                    FirebaseDatabase.getInstance().getReference("pusers").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(useR4).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(matashreepitashreeRegister.this, "User has been Registered", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(matashreepitashreeRegister.this, ParentsLogin.class));
                            } else {
                                Toast.makeText(matashreepitashreeRegister.this, "Registration failed", Toast.LENGTH_LONG).show();

                            }
                        }
                    });


                } else {
                    Toast.makeText(matashreepitashreeRegister.this, "registration failed due", Toast.LENGTH_LONG).show();
                    dprogressBar.setVisibility(View.GONE);
                }
            }

        });}}
