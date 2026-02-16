package db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import models.Notes;

public class MyNotesDB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public  static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME="MyNote";
    //db constructor
    public MyNotesDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db =getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Notes.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Notes.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    // insert name
    public boolean insertNote(String name){
        ContentValues contentValues= new ContentValues();
        contentValues.put(Notes.NAME, name);
        long count = db.insert(Notes.TABLE_NAME,null,contentValues);
        return count > 0;
    }
    //get all notes
    @SuppressLint("Range")
    public ArrayList<Notes> getAllNotes(){
        ArrayList<Notes> notes = new ArrayList<>();
        String sqlquery ="select * from "+Notes.TABLE_NAME +" order by "+Notes.ID;
        Cursor c =db.rawQuery(sqlquery,null);
        if (c.moveToFirst()){
            do {
                Notes notes1 = new Notes();
                notes1.setId(c.getColumnIndex(Notes.ID));
                notes1.setName(c.getString(c.getColumnIndex(Notes.NAME)));
                notes1.setImage(c.getInt(c.getColumnIndex(Notes.IMAGE)));
                notes.add(notes1);
            }while (c.moveToNext());
        }
        c.close();
        return notes;
    }
    // delete method
    public boolean deleteNote(int id){
        int count =db.delete(Notes.TABLE_NAME,Notes.ID+"=?",new String[]{String.valueOf(id)});
        return count >0;
    }
    //update methods
    public boolean updateNote (int id , String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notes.NAME,name);
        int count = db.update(Notes.TABLE_NAME,contentValues,Notes.ID +"=?", new String[]{String.valueOf(id)});
        return count >0 ;
    }

    // close db
    public void closeDB(){
        SQLiteDatabase db =this.getReadableDatabase();
        if (db !=null && db.isOpen());
        db.close();

    }

}




