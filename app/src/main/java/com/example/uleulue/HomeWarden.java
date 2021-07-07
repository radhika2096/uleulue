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

public class HomeWarden extends AppCompatActivity {
    private FirebaseUser user2;
    private DatabaseReference reference2;
    private String userId2;
    private Button logout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_warden);

        logout2 = (Button) findViewById(R.id.wbutton);
        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeWarden.this,wardenlogin.class));
            }
        });

        user2 = FirebaseAuth.getInstance().getCurrentUser();
        reference2 = FirebaseDatabase.getInstance().getReference("user2");
        userId2 = user2.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.meratext);
        final TextView fullnameText = (TextView) findViewById(R.id.wname);
        final TextView emailText= (TextView) findViewById(R.id.wemail);
        final TextView phonenumberText = (TextView) findViewById(R.id.wnum);
        final TextView sharingText = (TextView) findViewById(R.id.wshar);
        final TextView hostelnameText = (TextView) findViewById(R.id.whostname);

        reference2.child(userId2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user2 user2profile = snapshot.getValue(user2.class);
                if(user2profile != null)
                {
                    String ffullname = user2profile.nname;
                    String eemail = user2profile.eemail;
                    String phone = user2profile.pphonenumber;
                    String sharing = user2profile.ssharing;
                    String hostelname = user2profile.hhostelname;

                    greetingTextView.setText("welcome " + ffullname);
                    fullnameText.setText("Name - " + ffullname);
                    emailText.setText("email - " + eemail);
                    phonenumberText.setText("phoneNumber- " + phone);
                    sharingText.setText("sharing- " +  sharing);
                    hostelnameText.setText("hostelname- " +  hostelname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeWarden.this,"something went wrong",Toast.LENGTH_LONG).show();
            }
        });

    }
}

