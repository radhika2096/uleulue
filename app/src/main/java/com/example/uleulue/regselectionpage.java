package com.example.uleulue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class regselectionpage extends AppCompatActivity {
    public void changeUser(View view){
        if(view.getTag().toString()=="students")
            startActivity(new Intent(regselectionpage.this,registerStudentPage.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regselectionpage);

    }
}