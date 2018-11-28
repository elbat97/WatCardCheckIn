package com.example.elbatmesfin.watcardcheckin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.androiddeft.sqlitetutorial.vo.MovieDetailsVO;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Elbat on 28 November 2018
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AccountDetails";

    public static final String TABLE_NAME = "Accounts";
    public static final String KEY_ACCOUNT_ID = "account_id"; //email
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_BALANCE = "balance";

    //Create Table Query
    private static final String SQL_CREATE_ACCOUNTS =
            "CREATE TABLE accounts (" + KEY_ACCOUNT_ID + "  TEXT PRIMARY KEY, "
                    + KEY_USER_NAME + " TEXT, " + KEY_PASSWORD + "  TEXT, "
                    + KEY_BALANCE + "  INTEGER );";

    private static final String SQL_DELETE_ACCOUNTS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop the table while upgrading the database
        // such as adding new column or changing existing constraint
        db.execSQL(SQL_DELETE_ACCOUNTS);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }



    public long addAccount(AccountDetails account) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        //Create a map having account details to be inserted
        ContentValues acc_details = new ContentValues();
        acc_details.put(KEY_ACCOUNT_ID, account.getEmail());
        acc_details.put(KEY_USER_NAME, account.getName());
        acc_details.put(KEY_PASSWORD, account.getPassword());
        acc_details.put(KEY_BALANCE, account.getTotalBalance());

        long newRowId = db.insert(TABLE_NAME, null, acc_details);
        db.close();
        return newRowId;

    }

    public List getAllAccounts() {
        List accountDetailsList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_NAME
                + " ORDER BY " + KEY_ACCOUNT_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                AccountDetails accountDetails = new AccountDetails();
                accountDetails.setEmail(cursor.getString(0));
                accountDetails.setName(cursor.getString(1));
                accountDetails.setPassword(cursor.getString(2));
                accountDetails.setTotalBalance(cursor.getInt(3));

                //Add Account details to list
                accountDetailsList.add(accountDetails);
            } while (cursor.moveToNext());
        }
        db.close();
        return accountDetailsList;
    }

    public AccountDetails getAccount(String acc_id) {

        AccountDetails accountDetails = new AccountDetails();
        SQLiteDatabase db = this.getReadableDatabase();
        //specify the columns to be fetched
        String[] columns = {KEY_ACCOUNT_ID, KEY_USER_NAME, KEY_PASSWORD, KEY_BALANCE};

        //Select condition
        String selection = KEY_ACCOUNT_ID + " = ?";
        //Arguments for selection
        String[] selectionArgs = {String.valueOf(acc_id)};


        for(int i = 0; i < selectionArgs.length; ++i) {
            System.out.println(selectionArgs[i]);
        }

        Cursor cursor = db.query(TABLE_NAME, columns, selection,
                selectionArgs, null, null, null);
        if (null != cursor) {
            cursor.moveToFirst();
            accountDetails.setEmail(cursor.getString(0));
            accountDetails.setName(cursor.getString(1));
            accountDetails.setPassword(cursor.getString(2));
            accountDetails.setTotalBalance(cursor.getInt(3));

        }
        db.close();
        return accountDetails;

    }

    public void updateAccount(AccountDetails acc) {
        SQLiteDatabase db = this.getWritableDatabase();
        String accountIds[] = {String.valueOf(acc.getEmail())};

        ContentValues acc_details = new ContentValues();
        acc_details.put(KEY_USER_NAME, acc.getName());
        acc_details.put(KEY_PASSWORD, acc.getPassword());
        acc_details.put(KEY_BALANCE, acc.getTotalBalance());

        db.update(TABLE_NAME, acc_details, KEY_ACCOUNT_ID + " = ?", accountIds);
        db.close();
    }

    public void deleteAccount(String accId) {
        String accIds[] = {String.valueOf(accId)};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ACCOUNT_ID + " = ?", accIds);
        db.close();
    }

}
