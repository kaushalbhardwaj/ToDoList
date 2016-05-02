package com.example.khome.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khome.todolist.Database.Note;

import java.util.List;
import java.util.Random;

public class MySimpleArrayAdapter extends ArrayAdapter<Note> {
    private final Context context;

    String color[]={"#64B5F6","#4DD0E1","#AED581","#DCE775","#FFB74D","#FFD54F","#FFF176","#E0E0E0","#FFAB91","#90A4AE","#64B5F6"};
    List<Note> li;

    public MySimpleArrayAdapter(Context context, List<Note> li1) {
        super(context, R.layout.rowlayout_week, li1);
        this.context = context;
        this.li = li1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout_week, parent, false);
        Random ran=new Random();
        int i=ran.nextInt(10);
        TextView title = (TextView) rowView.findViewById(R.id.titlelist);
        TextView time = (TextView) rowView.findViewById(R.id.timelist);
        TextView date = (TextView) rowView.findViewById(R.id.datelist);
        LinearLayout ll=(LinearLayout) rowView.findViewById(R.id.ll);
        Note n=li.get(position);
        title.setText(n.getTitle());
        time.setText(n.getTime());
        date.setText(n.getDate());
        String c=color[position%10];
        ll.setBackgroundColor(Color.parseColor(c));
        return rowView;
    }
}
