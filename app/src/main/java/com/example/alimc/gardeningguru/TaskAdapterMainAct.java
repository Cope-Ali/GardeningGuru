package com.example.alimc.gardeningguru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * TaskAdapterMainAct extends the ArrayAdapter to display an array of task objects
 * this is designed for display in the main activity
 */

public class TaskAdapterMainAct extends ArrayAdapter<Task> {

    private List<Task> taskArrayList;

    public TaskAdapterMainAct(Context context, List<Task> tasks){

        super(context,0,tasks);
        this.taskArrayList = tasks;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){

        Task task = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_layout_main_act,parent,false);
        }

        TextView taskName = (TextView) convertView.findViewById(R.id.taskNameTxtMain);

        taskName.setText(task.getName());

        return convertView;

    }


}
