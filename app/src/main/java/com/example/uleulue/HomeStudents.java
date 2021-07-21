package com.example.uleulue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeStudents extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
   private Button logout,opreq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_students);
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeStudents.this,login.class));
            }
        });
        opreq = (Button) findViewById(R.id.outpassrequest);
        opreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeStudents.this,matapitadhundo.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userId = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.Headline);
        final TextView fullnameTextView = (TextView) findViewById(R.id.nameLabel);
        final TextView emailTextView = (TextView) findViewById(R.id.Emailadressdlabel);
        final TextView phonenumberTextView = (TextView) findViewById(R.id.phonenumberLabel);
        final TextView usnTextView = (TextView) findViewById(R.id.USNlabel);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userprofile = snapshot.getValue(user.class);
                if(userprofile != null)
                {
                    String fullname = userprofile.fullname;
                    String email = userprofile.email;
                    String phone = userprofile.phonenumber;
                    String usn = userprofile.usn;

                    greetingTextView.setText("welcome " + fullname);
                    fullnameTextView.setText("Name - " + fullname);
                    emailTextView.setText("email - " + email);
                    phonenumberTextView.setText("phoneNumber- " + phone);
                    usnTextView.setText("usn- " +  usn);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(HomeStudents.this,"something went wrong",Toast.LENGTH_LONG).show();
            }
        });

    }

}

