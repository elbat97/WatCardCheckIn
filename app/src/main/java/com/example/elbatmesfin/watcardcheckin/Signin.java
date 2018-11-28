package com.example.elbatmesfin.watcardcheckin;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends Activity {
    private static boolean isEdit = false;
    SQLiteDBHelper dbHelper = null;
    private String Emailid = "";
    private String Passwordid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        dbHelper = new SQLiteDBHelper(this);

        Button signingin = (Button) findViewById(R.id.SigninBtn);

        signingin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(findAccount()) {

                    Intent intent = new Intent(Signin.this, WelcomePage.class);
                    intent.putExtra("theEmail", Emailid);
                    startActivity(intent);
                }else{

                }

            }
        });
    }


    private boolean findAccount() {


        EditText accEmail = (EditText) findViewById(R.id.Name);
        EditText accPassword = (EditText) findViewById(R.id.Password);
        Emailid = accEmail.getText().toString();
        Passwordid = accPassword.getText().toString();


        AccountDetails accDetails = dbHelper.getAccount(Emailid);
        if(accDetails != null && Passwordid.equals(accDetails.getPassword())){
            return true;
        }else{
            Toast.makeText(Signin.this, "Sorry this is not a valid account",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
