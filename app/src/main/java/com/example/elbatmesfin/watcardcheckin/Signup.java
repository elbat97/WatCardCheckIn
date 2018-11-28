package com.example.elbatmesfin.watcardcheckin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    private static boolean isEdit = false;
    SQLiteDBHelper dbHelper = null;
    private String Emailid = "";
    private String Nameid = "";
    private String Passwordid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbHelper = new SQLiteDBHelper(this);

        Button signingup = (Button) findViewById(R.id.SignupBtn);

        signingup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(addNewAccount()) {
                    Intent intent = new Intent(Signup.this, WelcomePage.class);
                    intent.putExtra("theEmail", Emailid);
                    startActivity(intent);
                }else{

                }

            }
        });


    }


    private boolean addNewAccount() {
        AccountDetails accDetails = new AccountDetails();

        EditText accName = (EditText) findViewById(R.id.Name);
        EditText accPassword = (EditText) findViewById(R.id.Password);
        EditText accEmail = (EditText) findViewById(R.id.email);
        Emailid = accEmail.getText().toString();
        Nameid = accName.getText().toString();
        Passwordid = accPassword.getText().toString();
        String empty = "";

        if (!empty.equals(accName.getText().toString()) &&
                !empty.equals(accPassword.getText().toString()) &&
                !empty.equals(accEmail.getText().toString())) {
            accDetails.setEmail(accEmail.getText().toString());
            accDetails.setName(accName.getText().toString());
            accDetails.setPassword(accPassword.getText().toString());
            accDetails.setTotalBalance(0);
            long id = dbHelper.addAccount(accDetails);
            return true;

        } else {
            Toast.makeText(Signup.this, "One or more fields left empty!",
                    Toast.LENGTH_LONG).show();

            return false;
        }

    }

}
