package com.example.uleulue;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class matapitadhundoHolder extends RecyclerView.ViewHolder {

    TextView username,profession;

    public matapitadhundoHolder(@NonNull  View itemView) {


        super(itemView);
        profession = itemView.findViewById(R.id.proffesion);
         username= itemView.findViewById(R.id.uname);
    }
}
