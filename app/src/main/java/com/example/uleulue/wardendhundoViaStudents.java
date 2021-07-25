package com.example.uleulue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class wardendhundoViaStudents extends AppCompatActivity {
    FirebaseRecyclerOptions<wardenDhundo> options;
    FirebaseRecyclerAdapter<wardenDhundo,matapitadhundoHolder>adapter;
    DatabaseReference mUserRef,studentref,databaseofwarden;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    RecyclerView recyclerView;
   String ad,ent,ext,end,exd,n,useridparents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardendhundo_via_students);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserRef = FirebaseDatabase.getInstance().getReference().child("wusers");
        studentref = FirebaseDatabase.getInstance().getReference().child("users");
        databaseofwarden = FirebaseDatabase.getInstance().getReference().child("databaseofwarden");

        mAuth = FirebaseAuth.getInstance();

        muser = mAuth.getCurrentUser();
        ad = getIntent().getStringExtra("ad");
        ent = getIntent().getStringExtra("ent");
        ext= getIntent().getStringExtra("ext");
        end = getIntent().getStringExtra("end");
        exd = getIntent().getStringExtra("exd");
        n = getIntent().getStringExtra("n");
        useridparents = getIntent().getStringExtra("useridparents");


        loadUsers( "");



    }




    private void loadUsers(String s) {

        Query query = mUserRef.orderByChild("eemail").startAt(s).endAt(s+ "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<wardenDhundo>().setQuery(query,wardenDhundo.class).build();
        adapter = new FirebaseRecyclerAdapter<wardenDhundo, matapitadhundoHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull matapitadhundoHolder holder, int position, @NonNull wardenDhundo model) {


                    holder.username.setText(model.getNname());
                    holder.profession.setText(model.getEemail());
                    holder.studentsname.setText(model.getSsharing());

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            HashMap hashMap = new HashMap();
                            hashMap.put("address", ad);
                            hashMap.put("exitDate", exd);
                            hashMap.put("exitTime", ext);
                            hashMap.put("entryTime", ent);
                            hashMap.put("entryDate", end);
                            hashMap.put("name",n);
                            hashMap.put("useridstudent",muser.getUid().toString());
                            hashMap.put("status", "AcceptedByParents");
                            hashMap.put("status2", "pendingbywarden");
                            hashMap.put("useridparents", useridparents);
                            databaseofwarden.child(getRef(position).getKey().toString()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task task) {
                                    Toast.makeText(wardendhundoViaStudents.this,"you succesfully sent request to warden",Toast.LENGTH_LONG).show();
                                }
                            });



                        }});}






            @Override
            public matapitadhundoHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                                            int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_show_parents, parent, false);


                return new matapitadhundoHolder(view);
            }
        }

        ;
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }
}