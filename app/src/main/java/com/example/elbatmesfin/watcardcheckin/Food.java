package com.example.elbatmesfin.watcardcheckin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Food extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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
        mRecyclerView = (RecyclerView) findViewById(R.id.foodLayout);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(accDetails.getListItemsF());
        mRecyclerView.setAdapter(mAdapter);
    }

}
