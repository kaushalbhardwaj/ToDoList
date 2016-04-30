package com.example.khome.todolist;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khome.todolist.Database.Note;

import java.util.List;

public class MainPage extends AppCompatActivity {

    private Toolbar mToolbar;
    CoordinatorLayout cl;
    ListView lv1;
    DatabaseHandler dh;
    List<Note> li;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        cl=(CoordinatorLayout)findViewById(R.id.mainpagecl);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_mainpage);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("To Do List");


        dh=new DatabaseHandler(this);
        lv1=(ListView)findViewById(R.id.listView1);
        try {
            dh.open();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

        }
        li=dh.getData();


        dh.close();


        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getApplicationContext(), li);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Note c=(Note)parent.getItemAtPosition(position);
                Intent i=new Intent(MainPage.this,UpdateNote.class);
                i.putExtra("title",c.getTitle());
                i.putExtra("id",""+c.getId());
                i.putExtra("note",c.getNote());
                i.putExtra("time",c.getTime());
                i.putExtra("date",c.getDate());
                startActivityForResult(i,6);


                //Toast.makeText(getActivity().getApplicationContext(), "item "+position, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:

                Intent i=new Intent(getApplicationContext(),AddToDo.class);
                startActivityForResult(i, 1);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        dh=new DatabaseHandler(this);
        lv1=(ListView)findViewById(R.id.listView1);
        try {
            dh.open();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

        }
        li=dh.getData();

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getApplicationContext(), li);
        lv1.setAdapter(adapter);

        dh.close();
        if(requestCode==1)
        {
//            Toast.makeText(getApplicationContext(),"Note Added",Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar
                    .make(cl, "Note Added", Snackbar.LENGTH_LONG);

            snackbar.show();
        }

        if(requestCode==6)
        {
//            Toast.makeText(getApplicationContext(),"Note Added",Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar
                    .make(cl, "Note Updated", Snackbar.LENGTH_LONG);

            snackbar.show();
        }
    }

}
