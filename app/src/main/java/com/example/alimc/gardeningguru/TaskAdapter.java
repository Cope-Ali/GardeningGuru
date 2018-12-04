package com.example.alimc.gardeningguru;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

import static com.example.alimc.gardeningguru.MainActivity.garden;


public class TaskAdapter extends ArrayAdapter<Task> {

    private List<Task> taskArrayList;

    public TaskAdapter(Context context, List<Task> tasks){

        super(context,0,tasks);
        this.taskArrayList = tasks;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){

        Task task = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_layout,parent,false);
        }

        TextView taskName = (TextView) convertView.findViewById(R.id.taskNameTxtV);
        Button editButton = (Button) convertView.findViewById(R.id.editTaskBtn);
        Button deleteButton = (Button) convertView.findViewById(R.id.deleteTaskBtn);

        taskName.setText(task.getName());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), EditTask.class);
                Bundle bundle = new Bundle();
                bundle.putString("POSITION", Integer.toString(position));
                intent.putExtras(bundle);
                parent.getContext().startActivity(intent);

            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task taskObj = getItem(position);
                taskArrayList.remove(position);
                garden.removeTask(taskObj.getDueDate());
                notifyDataSetChanged();
            }
        });

        return convertView;

    }


}
