package com.example.ghichu.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghichu.AddNewTask;
import com.example.ghichu.FirstView;
import com.example.ghichu.MainActivity;
import com.example.ghichu.Model.TodoModel;
import com.example.ghichu.R;
import com.example.ghichu.Utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewholder> {
    private List<TodoModel> mList;
    private MainActivity activity;
    private DatabaseHelper myDB;
    public TodoAdapter (DatabaseHelper myDB,MainActivity activity){
        this.activity = activity;
        this.myDB=myDB;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        final TodoModel item = mList.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBooleean(item.getStasus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    myDB.updateStatus(item.getId(),1);

                }else
                    myDB.updateStatus(item.getId(),0);
            }
        });


    }
    public Context getContext(){
        return activity;
    }
    public void setTask(List<TodoModel> mList){
        this.mList= mList;
    }
    public void deletTask(int position){
        TodoModel item = mList.get(position);
        myDB.deteleTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }
    public void editItem(int position){
        TodoModel item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("task",item.getTask());
        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(),task.getTag());


    }
    public boolean toBooleean(int num){
        return num!=0;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{
        CheckBox mCheckBox;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.mcheckbox);
        }
    }




}
