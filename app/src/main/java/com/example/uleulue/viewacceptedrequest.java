package com.example.uleulue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class viewacceptedrequest extends AppCompatActivity {
    FirebaseRecyclerOptions<acceptedrequestdhundo> options;
    FirebaseRecyclerAdapter<acceptedrequestdhundo,matapitadhundoHolder> adapter;
    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewacceptedrequest);
        recyclerView = findViewById(R.id.rc0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserRef = FirebaseDatabase.getInstance().getReference().child("databaseofwarden");

        mAuth = FirebaseAuth.getInstance();

        muser = mAuth.getCurrentUser();

        loadUsers( "");


    }




    private void loadUsers(String s) {


        Query query = mUserRef.orderByChild("address").startAt(s).endAt(s+ "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<acceptedrequestdhundo>().setQuery(query,acceptedrequestdhundo.class).build();
        adapter = new FirebaseRecyclerAdapter<acceptedrequestdhundo, matapitadhundoHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull matapitadhundoHolder holder, int position, @NonNull acceptedrequestdhundo model) {
                if(model.getStatus2().toString().equals("Accepted By Warden")){

                holder.username.setText(model.getAddress());
                holder.profession.setText(model.getName());
                holder.studentsname.setText(model.getStatus2());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(viewacceptedrequest.this, setrealtime.class);
                        intent.putExtra("userkey67", getRef(position).getKey().toString());

                        startActivity(intent);


                    }}
                );}
                else
                {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0,0));
                }
            }





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