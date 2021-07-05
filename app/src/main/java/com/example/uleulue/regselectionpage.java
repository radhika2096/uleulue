package com.example.uleulue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class regselectionpage extends AppCompatActivity {
    public void changeUser(View view){
        int a =1 , b=2, c=3,d=4;
        if(view.getTag().toString().equals(Integer.toString(a)))
            startActivity(new Intent(regselectionpage.this,login.class));
        if(view.getTag().toString().equals(Integer.toString(b)))
            startActivity(new Intent(regselectionpage.this,ParentsRegisterActivity.class));
        if(view.getTag().toString().equals(Integer.toString(c)))
           startActivity(new Intent(regselectionpage.this,GatekeeperRegister.class));
        if(view.getTag().toString().equals(Integer.toString(d)))
          startActivity(new Intent(regselectionpage.this,wardenregisterpage.class));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regselectionpage);

    }
}