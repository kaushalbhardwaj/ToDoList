package com.example.khome.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khome.todolist.Database.Note;

import java.util.List;

public class MySimpleArrayAdapter extends ArrayAdapter<Note> {
    private final Context context;

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
        TextView title = (TextView) rowView.findViewById(R.id.titlelist);
        TextView time = (TextView) rowView.findViewById(R.id.timelist);
        TextView date = (TextView) rowView.findViewById(R.id.datelist);
        Note n=li.get(position);
        title.setText(n.getTitle());
        time.setText(n.getTime());
        date.setText(n.getDate());

        return rowView;
    }
}
