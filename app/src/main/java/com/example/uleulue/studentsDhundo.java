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

public class studentsDhundo extends AppCompatActivity {
    FirebaseRecyclerOptions<parentsdhundo> options;
    FirebaseRecyclerAdapter<parentsdhundo,matapitadhundoHolder>adapter;
    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    RecyclerView recyclerView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_dhundo);
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mUserRef = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        String parentsname = getIntent().getStringExtra("s");
        loadUsers("",parentsname);
    }

    private void loadUsers(String s,String pname) {

        Query query = mUserRef.orderByChild("email").startAt(s).endAt(s+ "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<parentsdhundo>().setQuery(query,parentsdhundo.class).build();
        adapter = new FirebaseRecyclerAdapter<parentsdhundo, matapitadhundoHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull  matapitadhundoHolder holder, int position, @NonNull  parentsdhundo model) {
                if(pname.equals(model.getParentsname())){
                holder.username.setText(model.getFullname());
                holder.profession.setText(model.getPhonenumber());
                holder.studentsname.setText(model.getParentsname());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(studentsDhundo.this,studentrequestPreviewPage.class);
                        intent.putExtra("userkey1",getRef(position).getKey().toString());
                        startActivity(intent);

                    }
                });}
                else
                {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0,0));

                }

            }



            @NonNull

            @Override
            public matapitadhundoHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_show_parents,parent,false);



                return new matapitadhundoHolder(view);
            }
        };
        adapter.startListening();
        recyclerView1.setAdapter(adapter);

    }
}