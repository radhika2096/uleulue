
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

public class studentreg extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextname, editTextemail, editTextpassword, editTextphonenumber, editTextusn;
    private TextView registeruser;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentreg);
        mAuth = FirebaseAuth.getInstance();
        registeruser = (Button) findViewById(R.id.preg2);
        registeruser.setOnClickListener(this);
        editTextname = (EditText) findViewById(R.id.nameStud2);
        editTextemail = (EditText) findViewById(R.id.emailreg);
        editTextpassword = (EditText) findViewById(R.id.passwordreg2);
        editTextphonenumber = (EditText) findViewById(R.id.phoneStud2);
        editTextusn = (EditText) findViewById(R.id.usnStud2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.preg2:
                Registeruser();
        }
    }

    private void Registeruser()
    {
        String email = editTextemail.getText().toString();
        String usn = editTextusn.getText().toString();
        String name = editTextname.getText().toString();
        String phone = editTextphonenumber.getText().toString();
        String password = editTextpassword.getText().toString();

        if(name.isEmpty())
        {
            editTextname.setError("full name required");
            editTextname.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editTextpassword.setError("password required");
            editTextpassword.requestFocus();
            return;
        }
        if(usn.isEmpty())
        {
            editTextusn.setError("usn required");
            editTextusn.requestFocus();
            return;
        }
        if(phone.isEmpty())
        {
            editTextphonenumber.setError("phonenumber required");
            editTextphonenumber.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            editTextemail.setError("emai; required");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("please provide valid email");
            editTextemail.requestFocus();
            return;

        }
        if(password.length()<6)
        {
            editTextpassword.setError("min pass require length should be 6");
            editTextpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user useR = new user(name, email, phone, usn);
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(useR).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(studentreg.this, "registered", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.VISIBLE);
                                startActivity(new Intent(studentreg.this,login.class));
                            } else {
                                Toast.makeText(studentreg.this, "registration failed", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });


                } else {
                    Toast.makeText(studentreg.this, "registration failed due", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

        });}}
