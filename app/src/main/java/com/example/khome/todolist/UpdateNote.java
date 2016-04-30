package com.example.khome.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class UpdateNote extends AppCompatActivity {
    ImageView date,time;

    Note n;
    private Toolbar mToolbar;
    TextView datetext,timetext;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Button add;
    EditText title,note;
    DatabaseHandler dh=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        add=(Button)findViewById(R.id.update);
        n=new Note();
        Bundle b=getIntent().getExtras();
        String n1=b.getString("title").toString();
        String p=b.getString("note").toString();
        String c=b.getString("time").toString();
        String id=b.getString("id").toString();
        String d1=b.getString("date").toString();
        n.setTitle(n1);
        n.setNote(p);
        n.setId(Long.parseLong(id));
        n.setTime(c);
        n.setDate(d1);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_addtodo);
        dh=new DatabaseHandler(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Note");
        try {
            dh.open();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

        }

        date=(ImageView)findViewById(R.id.datebt);
        time=(ImageView)findViewById(R.id.timebt);
        datetext=(TextView)findViewById(R.id.date);
        timetext=(TextView)findViewById(R.id.time);
        title=(EditText)findViewById(R.id.title);
        note=(EditText)findViewById(R.id.note);

        title.setText(n.getTitle());
        note.setText(n.getNote());
        datetext.setText(n.getDate());
        timetext.setText(n.getTime());

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog tpd = new TimePickerDialog(UpdateNote.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                timetext.setText(hourOfDay + ":" + minute);
                                n.setTime(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpd.show();


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.setTitle(title.getText().toString());
                n.setNote(note.getText().toString());

                dh.update(n);
                setResult(RESULT_OK);
                finish();



            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(UpdateNote.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                datetext.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                                n.setDate(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

    }
}


