package com.example.uleulue;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.uleulue.databinding.ActivityLoginStudentBinding;

public class login_Student extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }


}