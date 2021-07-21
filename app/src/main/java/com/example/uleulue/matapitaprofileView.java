package com.example.uleulue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class matapitaprofileView extends AppCompatActivity {

    DatabaseReference mUserRef,requestRef,friendref;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String username, phone,email;
    TextView username9810,phone9810,email9810,adress,exitdate,entrydate,exittime,entrytime;
    Button btnperfrom,btndecline;

    String currentstate = "nothingHappened";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matapitaprofile_view);
        String userid = getIntent().getStringExtra("userkey");
        Toast.makeText(this , "" + userid, Toast.LENGTH_LONG).show();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("pusers").child(userid);
        requestRef = FirebaseDatabase.getInstance().getReference().child("OutpassRequesttoParent");
        friendref = FirebaseDatabase.getInstance().getReference().child("requestcompleteHistory");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnperfrom = findViewById(R.id.request9810);
        btndecline = findViewById(R.id.cancelRequest);
        username9810 = findViewById(R.id.usernameparentsshow);
        phone9810 = findViewById(R.id.showPhone);
        email9810 = findViewById(R.id.showEmail);
        adress = findViewById(R.id.adress2);
        exitdate = findViewById(R.id.exitDate2);
        entrydate = findViewById(R.id.entryDate2);
        entrytime = findViewById(R.id.entryTime2);
        exittime = findViewById(R.id.exitTime2);


        LoadUser();
        btnperfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfromaction(userid);

            }
        });
    }

    private void perfromaction(String userid) {
        String staddress = adress.getText().toString();
        String stexitDate = exitdate.getText().toString();
        String stentryDate = entrydate.getText().toString();
        String stentrytime = entrytime.getText().toString();
        String stexittime = exittime.getText().toString();
        if(currentstate.equals("nothingHappened"))
        {
            HashMap hashMap = new HashMap();
            hashMap.put("status","pending");
            hashMap.put("address",staddress);
            hashMap.put("exitDate",stexitDate);
            hashMap.put("exitTime",stexittime);
            hashMap.put("entryTime",stentrytime);
            hashMap.put("entryDate",stentryDate);


            requestRef.child(mUser.getUid()).child(userid).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(matapitaprofileView.this," request sent",Toast.LENGTH_LONG ).show();
                        btndecline.setVisibility(View.GONE);
                        currentstate = "i_sent_pending";
                        btnperfrom.setText("cancel outpass request");
                    }
                    else
                    {
                        Toast.makeText(matapitaprofileView.this,""+task.getException(),Toast.LENGTH_LONG).show();

                    }


                }
            });

        }
        if(currentstate.equals("i_sent_pending")||currentstate.equals("i_sent_decline"))
        {
            requestRef.child(mUser.getUid()).child(userid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(matapitaprofileView.this,"your request has been cancelled",Toast.LENGTH_LONG).show();
                        //agar parents decline kiye to kuchh to karo
                        currentstate = "nothingHappened";
                        btnperfrom.setText("send outPass request");
                        btndecline.setVisibility(View.GONE);
                    }
                    else
                    {
                        Toast.makeText(matapitaprofileView.this,""+task.getException(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        if (currentstate.equals("heSentPending"))
        {
            requestRef.child(mUser.getUid()).child(userid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        HashMap hashMap = new HashMap();
                        hashMap.put("status","Accepted");
                        hashMap.put("username",username);
                        friendref.child(mUser.getUid()).child(userid).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if(task.isSuccessful())
                                {
                                    friendref.child(userid).child(mUser.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull  Task task) {
                                            Toast.makeText(matapitaprofileView.this,"completeByParents",Toast.LENGTH_LONG).show();
                                            currentstate = "friend";
                                            btnperfrom.setText("completed");
                                            btnperfrom.setText("undo");
                                            btndecline.setVisibility(View.VISIBLE);

                                        }
                                    });

                                }
                            }
                        });



                    }
                }
            });




        }
        if (currentstate.equals("friend"))

        {
            //
        }



    }

    private void LoadUser(){
        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
              if(snapshot.exists())
              {
                  username = snapshot.child("parentsname").getValue().toString();
                  phone = snapshot.child("phonenumber").getValue().toString();
                  email = snapshot.child("email").getValue().toString();
                  username9810.setText(username);
                  phone9810.setText(phone);
                  email9810.setText(email);


              }
              else {
                  Toast.makeText(matapitaprofileView.this,"data not found",Toast.LENGTH_LONG).show();

              }


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(matapitaprofileView.this,""+error.getMessage().toString(),Toast.LENGTH_LONG).show();



            }
        });

    }
}