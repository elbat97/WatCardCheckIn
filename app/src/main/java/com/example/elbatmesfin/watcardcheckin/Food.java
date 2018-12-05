package com.example.elbatmesfin.watcardcheckin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Food extends AppCompatActivity {
    RecyclerView recyclerView;

    SQLiteDBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String theAccEmail = getIntent().getStringExtra("theEmail");
        dbHelper = new SQLiteDBHelper(this);
        final AccountDetails accDetails = dbHelper.getAccount(theAccEmail);
        Expense n = new Expense();
        n.item = "pizza";
        n.value = 24;
        accDetails.addExpenseFood(n);
        dbHelper.updateAccount(accDetails); //update the database


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, accDetails.getListItemsF()));



        Button addExpBtn = (Button) findViewById(R.id.addExp);

        addExpBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Food.this, "Signing out! Goodbye",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(Food.this, WelcomePage.class));

            }
        });
    }


}
