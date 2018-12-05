package com.example.elbatmesfin.watcardcheckin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.logging.Handler;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String[] items;
    public MyAdapter(Context context, String[] items){
        this.context=context;
        this.items = items;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.activity_expense_list, viewGroup, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myViewHolder, int i) {
        ((Item) myViewHolder).textView.setText(items[i]);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView textView;

        public Item(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item);
        }
    }
}