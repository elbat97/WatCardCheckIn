package com.example.elbatmesfin.watcardcheckin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckingBalance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbal);

        Button noAccount = (Button) findViewById(R.id.no);

        noAccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(CheckingBalance.this, Signup.class));
            }
        });


        Button yesAccount = (Button) findViewById(R.id.yes);

        yesAccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(CheckingBalance.this, Signin.class));
            }
        });



    }
}
