package com.weee.todolist;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.ArrayAdapter;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.widget.AdapterView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
TextView name;
Button addtask;
EditText newtask;
ListView task;
    ArrayList<String> List_task;
    ArrayAdapter<String> arrayAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        newtask = findViewById(R.id.newtask);
        addtask = findViewById(R.id.addtask);
        task = findViewById(R.id.task);
        List_task = new ArrayList<>();
        List_task.add("Project");
        List_task.add("Shopping");
        List_task.add("Mobile");
        List_task.add("Study");
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, List_task);
        task.setAdapter(arrayAdapter);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = newtask.getText().toString();
                arrayAdapter.add(text);
                newtask.setText("");
            }
        });
        task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                String item = arrayAdapter.getItem(position);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("delete task")
                        .setMessage("Are you sure you want to delete")
                        .setNegativeButton("cancel", null)
                        .setPositiveButton("delete", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int
                                            which) {
                                        arrayAdapter.remove(item);
                                        arrayAdapter.notifyDataSetChanged();
                                        Toast.makeText(MainActivity.this,
                                                "Task deleted", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}