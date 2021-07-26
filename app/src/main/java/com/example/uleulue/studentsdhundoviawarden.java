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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class studentsdhundoviawarden extends AppCompatActivity {
    FirebaseRecyclerOptions<requestdhundo> options;
    FirebaseRecyclerAdapter<requestdhundo,matapitadhundoHolder>adapter;
    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsdhundoviawarden);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();

        mUserRef = FirebaseDatabase.getInstance().getReference().child("databaseofwarden");

        loadUsers( "");


    }




    private void loadUsers(String s) {

        Query query = mUserRef.orderByChild("address").startAt(s).endAt(s+ "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<requestdhundo>().setQuery(query,requestdhundo.class).build();
        adapter = new FirebaseRecyclerAdapter<requestdhundo, matapitadhundoHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull matapitadhundoHolder holder, int position, @NonNull @NotNull requestdhundo model) {





                    holder.username.setText(model.getName());
                    holder.profession.setText(model.getAddress());
                    holder.studentsname.setText(model.getExitDate());

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(studentsdhundoviawarden.this, wardenrequestpreviewpage.class);
                            intent.putExtra("userkey5", getRef(position).getKey().toString());
                            startActivity(intent);

                        }
                    });}




            @NonNull

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




