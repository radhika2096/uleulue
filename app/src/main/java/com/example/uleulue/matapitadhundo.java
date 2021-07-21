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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

public class matapitadhundo extends AppCompatActivity {
    FirebaseRecyclerOptions<parentsdhundo> options;
    FirebaseRecyclerAdapter<parentsdhundo,matapitadhundoHolder>adapter;
   DatabaseReference mUserRef;
   FirebaseAuth mAuth;
   FirebaseUser muser;
   RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matapitadhundo);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserRef = FirebaseDatabase.getInstance().getReference().child("pusers");
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        loadUsers("");
    }

    private void loadUsers(String s) {
     Query query = mUserRef.orderByChild("email").startAt(s).endAt(s+ "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<parentsdhundo>().setQuery(query,parentsdhundo.class).build();
        adapter = new FirebaseRecyclerAdapter<parentsdhundo, matapitadhundoHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull  matapitadhundoHolder holder, int position, @NonNull  parentsdhundo model) {

                holder.username.setText(model.getParentsname());
                holder.profession.setText(model.getPhonenumber());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(matapitadhundo.this,matapitaprofileView.class);
                        intent.putExtra("userkey",getRef(position).getKey().toString());
                        startActivity(intent);

                    }
                });

            }



            @NonNull

            @Override
            public matapitadhundoHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_show_parents,parent,false);



                return new matapitadhundoHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}