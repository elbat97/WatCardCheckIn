package com.example.elbatmesfin.watcardcheckin;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomePage extends AppCompatActivity {
    private static boolean isEdit = false;
    SQLiteDBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //pulling the string from the signup page, so that we can pull the correct record
        final String theAccEmail = getIntent().getStringExtra("theEmail");

        dbHelper = new SQLiteDBHelper(this);
        final AccountDetails accDetails = dbHelper.getAccount(theAccEmail);

        TextView textView = (TextView) findViewById(R.id.balanceMessage);
        final String balanceCheck = "Current Balance: $" + accDetails.getTotalBalance();
        textView.setText(balanceCheck);



        Button updateBtn = (Button) findViewById(R.id.updateBalance);

        updateBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //DialogFragment changeBal = new updateModal();

                //changeBal.show(getSupportFragmentManager(), "updating");

                Intent intent = new Intent(WelcomePage.this, updateModal.class);
                intent.putExtra("theEmail", theAccEmail);
                startActivity(intent);

            }
        });


        Button signoutBtn = (Button) findViewById(R.id.Signout);

        signoutBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(WelcomePage.this, "Signing out! Goodbye",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(WelcomePage.this, MainActivity.class));

            }
        });

    }


}
