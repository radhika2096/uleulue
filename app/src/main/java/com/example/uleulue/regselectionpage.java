package com.example.uleulue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class regselectionpage extends AppCompatActivity {
    FirebaseAuth mAuth;

    Button s,p,w,g;
    public void changeUser(View view){
        int a =1 , b=2, c=3,d=4;
        if(view.getTag().toString().equals(Integer.toString(a)))
            startActivity(new Intent(regselectionpage.this,login.class));
        if(view.getTag().toString().equals(Integer.toString(b)))
            startActivity(new Intent(regselectionpage.this,ParentsLogin.class));
        if(view.getTag().toString().equals(Integer.toString(c)))
           startActivity(new Intent(regselectionpage.this,GatekeeperLogin.class));
        if(view.getTag().toString().equals(Integer.toString(d)))
          startActivity(new Intent(regselectionpage.this,wardenlogin.class));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regselectionpage);
        /*mAuth = FirebaseAuth.getInstance();
        s = findViewById(R.id.s);
        p = findViewById(R.id.p);
        w = findViewById(R.id.w);
        g = findViewById(R.id.g);
        FirebaseUser mUser = mAuth.getCurrentUser();
        if(mUser!=null)
        {
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, HomeStudents.class));
                }
            });
            p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, ParentsHome.class));
                }
            });
            w.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, HomeWarden.class));
                }
            });
            g.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, GatekeeperHome.class));
                }
            });

        }
        else {
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, login.class));
                }
            });
            p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, ParentsLogin.class));
                }
            });
            w.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, wardenlogin.class));
                }
            });
            g.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(regselectionpage.this, GatekeeperLogin.class));
                }
            });

        }
*/
    }


}