package com.example.alimc.gardeningguru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alimc.gardeningguru.MainActivity.garden;

public class EditTask extends AppCompatActivity {

    private EditText plantingEtxt;
    private EditText taskName;
    private EditText dueDate;
    private CheckBox done;
    private EditText description;
    private Button taskSave;
    private Button taskRest;
    private Task task;
    private int taskListPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Bundle bundle = getIntent().getExtras();
        this.taskListPosition = Integer.parseInt(bundle.getString("POSITION"));

        List<Task> taskList = new ArrayList<>(garden.getTasksPending().values());
        this.task = taskList.get(this.taskListPosition);

        this.dueDate = (EditText) findViewById(R.id.dueDateTxtE);
        this.plantingEtxt = (EditText) findViewById(R.id.plantingEtxt);
        this.taskName = (EditText) findViewById(R.id.taskNameTxtE);
        this.done = (CheckBox) findViewById(R.id.doneChBox);
        this.description = (EditText) findViewById(R.id.descriptionTxtE);
        this.taskRest = (Button) findViewById(R.id.taskEditResetBtn);
        this.taskSave = (Button) findViewById(R.id.taskEditSaveBtn);

        this.fullTaskInput();

    }


    public void onClickSaveTask(View view){

        this.updateTaskObject();
        this.finish();
    }


    public void onClickRestTask(View view){

        this.clearTaskInput();
    }

    public void clearTaskInput(){
        this.dueDate.setText("");
        this.plantingEtxt.setText("");
        this.taskName.setText("");
        this.done.setChecked(false);
        this.description.setText("");
    }

    public void fullTaskInput(){

        //fill the form
        this.dueDate.setText(dateToString(this.task.getDueDate()));
        this.plantingEtxt.setText(this.task.getPlantingName());
        this.taskName.setText(this.task.getName());
        this.done.setChecked(this.task.isDone());
        this.description.setText(this.task.getDescription());
    }

    public void updateTaskObject(){

        //update the task object
        this.task.setDescription(this.description.getText().toString());
        this.task.setDone(this.done.isChecked());
        this.task.setName(this.taskName.getText().toString());
        this.task.setPlantingName(this.plantingEtxt.getText().toString());
        this.task.setDueDate(stringToDate(this.dueDate.getText().toString()));
    }

    public Date stringToDate(String dateString){

       // SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String[] dateArray = dateString.split("/");
        Date returnDate = new Date(Integer.parseInt(dateArray[2]),
                                   Integer.parseInt(dateArray[1]),
                                   Integer.parseInt(dateArray[0]));


      /*  try {
            returnDate = dateFormat.parse(dateString);

        } catch (ParseException e) {

            e.printStackTrace();
        }*/

        return returnDate;
    }

    public String dateToString(Date dateString){

        String month = Integer.toString(dateString.getMonth());
        String day = Integer.toString(dateString.getDate());
        String year = Integer.toString(dateString.getYear());

        return month + "/" + day + "/" + year;
    }

}
