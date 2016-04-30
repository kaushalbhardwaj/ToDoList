package com.example.khome.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AddToDo extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_addtodo);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add New");
    }
}
