package com.example.alimc.gardeningguru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.alimc.gardeningguru.MainActivity.garden;

/**
 * TaskList creates a way to view the list of tasks utilising the taskAdapter
 */

public class TaskList extends AppCompatActivity {
    private TaskAdapter adapter;
    private List<Task> taskList;
    private ListView taskListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        this.taskListView = (ListView) findViewById(R.id.taskListLstViw);
        if(garden.getTasksPending() != null){
            this.taskList = new ArrayList<>(garden.getTasksPending().values());
            this.adapter = new TaskAdapter(this,this.taskList);
            this.taskListView.setAdapter(this.adapter);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }
}
