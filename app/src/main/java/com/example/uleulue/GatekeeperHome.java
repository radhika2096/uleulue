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

public class GatekeeperHome extends AppCompatActivity {
   /* private FirebaseUser user3;
    private DatabaseReference reference3;
    private String userId3;
    private Button logout3;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatekeeper_home);
        /*logout3 = (Button) findViewById(R.id.clogout2);
        logout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(GatekeeperHome.this, GatekeeperLogin.class));
            }
        });
    user3 = FirebaseAuth.getInstance().getCurrentUser();
    reference3 = FirebaseDatabase.getInstance().getReference("user3");
    userId3 = user3.getUid();

    final TextView greetingTextView = (TextView) findViewById(R.id.cmeratext);
    final TextView fullnameTextView = (TextView) findViewById(R.id.cnameLabel2);
    final TextView emailTextView = (TextView) findViewById(R.id.cemailadressdlabel);
    final TextView phonenumberTextView = (TextView) findViewById(R.id.cphonenumberLabel2);


        reference3.child(userId3).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user3 user3profile = snapshot.getValue(user3.class);
                if(user3profile != null)
                {
                    String fullname1 = user3profile.;
                    String email1 = user3profile.email1;
                    String phonenumber1 = user3profile.phonenumber1;


                    greetingTextView.setText("welcome " + fullname1);
                    fullnameTextView.setText("Name - " + fullname1);
                    emailTextView.setText("email - " + email1);
                    phonenumberTextView.setText("phoneNumber- " + phonenumber1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GatekeeperHome.this,"something went wrong",Toast.LENGTH_LONG).show();
            }
        });

    }*/
}}
