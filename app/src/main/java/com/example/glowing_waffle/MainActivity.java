package com.example.glowing_waffle;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class MainActivity extends AppCompatActivity {

    private ListView todoList;
    private ArrayList<String> todoArray;
    private ArrayAdapter todoAdapter;
    private String ToDoText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        todoList = findViewById(R.id.todoList);
        todoArray = new ArrayList<>();
        todoAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, todoArray);


        FloatingActionButton addItem = findViewById(R.id.add);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add To List");

                // Set up the input
                final EditText input = new EditText(MainActivity.this);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToDoText = input.getText().toString();

                        todoArray.add(ToDoText);
                        todoList.setAdapter(todoAdapter);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.aboutItem) {
            final PrettyDialog pDialog = new PrettyDialog(this);
            pDialog
                    .setIcon(R.drawable.info_icon)
                    .setTitle("About This App")
                    .setMessage("This is our To Do List App" + "\n"
                            + "Codenamed : Glowing Waffle" + "\n"
                            + "Hope you like it.")
                    .addButton(
                            "OK",     // button text
                            R.color.pdlg_color_black,  // button text color
                            R.color.pdlg_color_gray,  // button background color
                            new PrettyDialogCallback() {  // button OnClick listener
                                @Override
                                public void onClick() {
                                    pDialog.dismiss();
                                }
                            }
                    )
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
