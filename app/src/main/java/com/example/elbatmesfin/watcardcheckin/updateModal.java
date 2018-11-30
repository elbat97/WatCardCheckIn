package com.example.elbatmesfin.watcardcheckin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by tsd057 on 2018-11-29.
 */

public class updateModal extends AppCompatActivity {
    private static boolean isEdit = false;
    SQLiteDBHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_modal);

        //pulling the string from the signup page, so that we can pull the correct record
        final String theAccEmail = getIntent().getStringExtra("theEmail");

        dbHelper = new SQLiteDBHelper(this);
        final AccountDetails accDetails = dbHelper.getAccount(theAccEmail);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height *.3));
        setTitle("What is your Current Balance?");



        Button ok = (Button) findViewById(R.id.updateOk);

        ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final EditText balanceAmt = (EditText) findViewById(R.id.amt);
                accDetails.setTotalBalance(Integer.parseInt(balanceAmt.getText().toString()));
                dbHelper.updateAccount(accDetails);
                Intent intent = new Intent(updateModal.this, WelcomePage.class);
                intent.putExtra("theEmail", theAccEmail);
                startActivity(intent);
            }
        });


    }


}