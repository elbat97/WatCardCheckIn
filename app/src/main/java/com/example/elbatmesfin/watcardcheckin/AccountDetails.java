package com.example.elbatmesfin.watcardcheckin;

/**
 * Created by tsd057 on 2018-11-28.
 */

public class AccountDetails {
    private String name;
    private String password;
    private int totalBalance;
    private String email;

    public String getEmail(){
        return email;
    }

    public void setEmail(String em){
        this.email = em;
    }
    public int getTotalBalance(){
        return totalBalance;
    }

    public void setTotalBalance(int value){
        this.totalBalance = value;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        this.name = n;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String pass){
        this.password = pass;
    }

}
