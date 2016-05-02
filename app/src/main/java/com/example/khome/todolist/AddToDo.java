package com.example.khome.todolist;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.khome.todolist.Database.Note;

import java.util.Calendar;

public class AddToDo extends AppCompatActivity {
    FloatingActionButton date,time;

    Note n;
    private Toolbar mToolbar;
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    TextView datetext,timetext;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Button add;
    EditText title,note;
    DatabaseHandler dh=null;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        //add=(Button)findViewById(R.id.add);
        n=new Note();
        n.setDate(null);
        n.setTitle(null);
        n.setNote(null);n.setTime(null);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_addtodo);
        dh=new DatabaseHandler(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Note");
        date=(FloatingActionButton)findViewById(R.id.datebt);
        time=(FloatingActionButton)findViewById(R.id.timebt);
        datetext=(TextView)findViewById(R.id.date);
        timetext=(TextView)findViewById(R.id.time);
        title=(EditText)findViewById(R.id.title);
        note=(EditText)findViewById(R.id.note);

        c=Calendar.getInstance();
        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog tpd = new TimePickerDialog(AddToDo.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                timetext.setText(hourOfDay + ":" + minute);
                                n.setTime(hourOfDay + ":" + minute);
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                            }
                        }, mHour, mMinute, false);
                tpd.show();


            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(AddToDo.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                datetext.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                                n.setDate(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                c.set(Calendar.MONTH,monthOfYear);
                                c.set(Calendar.YEAR,year);
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });
       /* add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.setTitle(title.getText().toString());
                n.setNote(note.getText().toString());

                try {
                    dh.open();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

                }

                dh.insert(n);
                setResult(RESULT_OK);
                finish();

            }
        }); */
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addtodo, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:


                n.setTitle(title.getText().toString());
                n.setNote(note.getText().toString());

                try {
                    dh.open();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

                }

                dh.insert(n);

                if(!(n.getTime().equals(null)))
                {
                    Intent myIntent = new Intent(AddToDo.this, AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(AddToDo.this, 0, myIntent, 0);
                    alarmManager.set(AlarmManager.RTC, c.getTimeInMillis(), pendingIntent);
                    System.out.println("alarm set");


                }

                setResult(RESULT_OK);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
