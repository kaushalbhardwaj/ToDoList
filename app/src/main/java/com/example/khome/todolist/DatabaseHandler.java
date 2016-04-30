package com.example.khome.todolist;


import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;


import com.example.khome.todolist.Database.MySqliteHelper;
import com.example.khome.todolist.Database.Note;

import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

public class DatabaseHandler {

    MySqliteHelper sh;
    SQLiteDatabase db;
    public DatabaseHandler(Context context)
    {
        sh=new MySqliteHelper(context);

    }
    public void open() throws SQLException
    {
        db=sh.getWritableDatabase();
    }
    public void close()
    {
        sh.close();
    }
    public long insert(Note con)
    {
        //System.out.println(con.getTime()+""+ con.getDate());
        ContentValues cv=new ContentValues();
        cv.put(MySqliteHelper.ColTitle,con.getTitle());
        cv.put(MySqliteHelper.ColNote,con.getNote());
        cv.put(MySqliteHelper.ColTime,con.getTime());
        cv.put(MySqliteHelper.ColDate,con.getDate());

        long i=db.insert(MySqliteHelper.TBName, null, cv);
        return i;
    }
    public void update(Note con)
    {
        ContentValues cv=new ContentValues();
        cv.put(MySqliteHelper.ColTitle, con.getTitle());
        cv.put(MySqliteHelper.ColNote,con.getNote());
        cv.put(MySqliteHelper.ColTime,con.getTime());
        cv.put(MySqliteHelper.ColDate,con.getDate());
        db.update(MySqliteHelper.TBName, cv, MySqliteHelper.ColId + "=" + con.getId(), null);
    }
    public void delete(long i)
    {
        db.delete(MySqliteHelper.TBName, MySqliteHelper.ColId + "=" + i, null);

    }
    public List<Note> getData()
    {
        Note c;
        List<Note> con=new ArrayList<Note>();
        Cursor curs=db.query(MySqliteHelper.TBName,new String[] {MySqliteHelper.ColId,MySqliteHelper.ColTitle,MySqliteHelper.ColNote,MySqliteHelper.ColTime,MySqliteHelper.ColDate}
                ,null,null,null,null,null,null);
        curs.moveToFirst();
        while(!curs.isAfterLast())
        {
            c=cursorToContact(curs);
            con.add(c);
            curs.moveToNext();
        }

        curs.close();
        return con;

    }

    public Note cursorToContact(Cursor curs)
    {
        Note c=new Note();
        c.setId(curs.getLong(0));
        c.setTitle(curs.getString(1));
        c.setNote(curs.getString(2));
        c.setTime(curs.getString(3));
        c.setDate(curs.getString(4));

        return c;

    }
}