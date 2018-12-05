package com.example.elbatmesfin.watcardcheckin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, accDetails.getListItemsF()));
    }

}
