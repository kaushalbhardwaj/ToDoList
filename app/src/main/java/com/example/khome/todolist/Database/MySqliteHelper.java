package com.example.khome.todolist.Database;


import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
public class MySqliteHelper extends SQLiteOpenHelper {

    public static String DBName="Note1";
    public static int DBVersion=1;
    public static String TBName="ToDoList";
    public static String ColId="_id";
    public static String ColTitle="title";
    public static String ColNote="note";
    public static String ColTime="time";
    public static String ColDate="date";
    public static String create=
            "CREATE TABLE "+ TBName
                    +"("+ColId+" integer primary key autoincrement, "
                    +ColTitle+" text not null, "
                    +ColNote+" text not null, "
                    +ColTime+" text, "
                    + ColDate+" text);";

    public MySqliteHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TBName);
        onCreate(db);
    }
}