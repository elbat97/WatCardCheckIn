package com.example.elbatmesfin.watcardcheckin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsd057 on 2018-11-28.
 */

public class AccountDetails {
    private String name;
    private String password;
    private int totalBalance;
    private String email;
    private List<Expense> food = new ArrayList<>();
    private List<Expense> textbook = new ArrayList<>();
    private List<Expense> printing = new ArrayList<>();
    private List<Expense> other = new ArrayList<>();

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

    public void addExpenseFood(Expense newexp){
        this.food.add(newexp);
    }
    public void addExpenseText(Expense newexp){
        this.textbook.add(newexp);
    }
    public void addExpensePrint(Expense newexp){
        this.printing.add(newexp);
    }
    public void addExpenseOther(Expense newexp){
        this.other.add(newexp);
    }
    public int getExpenseFood(){
        int total = 0;
        for(Expense n: food){
            total = total + n.value;
        }
        return total;
    }
    public int getExpenseText(){
        int total = 0;
        for(Expense n: textbook){
            total = total + n.value;
        }
        return total;
    }

    public int getExpensePrint(){
        int total = 0;
        for(Expense n: printing){
            total = total + n.value;
        }
        return total;
    }

    public int getExpenseOther(){
        int total = 0;
        for(Expense n: other){
            total = total + n.value;
        }
        return total;
    }

    public int totalExpense(){
        int total = 0;
        total = this.getExpenseFood() + this.getExpenseOther() + this.getExpensePrint() + this.getExpenseText();
        return total;
    }
    public String[] getListItemsF(){
        String[] foodItems = new String[food.size()];
        int i = 0;
        for(Expense n: food){
            foodItems[i] = n.item + " " + Integer.toString(n.value);
        }
        return foodItems;
    }

}
