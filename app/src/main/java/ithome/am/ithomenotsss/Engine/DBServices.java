package ithome.am.ithomenotsss.Engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import static ithome.am.ithomenotsss.Engine.Constants.COLUMN_ID;
import static ithome.am.ithomenotsss.Engine.Constants.COLUMN_PERSON_IMAGE;
import static ithome.am.ithomenotsss.Engine.Constants.COLUMN_PERSON_NAME;
import static ithome.am.ithomenotsss.Engine.Constants.COLUMN_PERSON_OCCUPATION;
import static ithome.am.ithomenotsss.Engine.Constants.DATABASE_NAME;
import static ithome.am.ithomenotsss.Engine.Constants.TABLE_NAME;

public class DBServices extends SQLiteOpenHelper {
    public DBServices(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PERSON_NAME + " TEXT NOT NULL, " +
       //       COLUMN_PERSON_AGE + " NUMBER NOT NULL, " +
                COLUMN_PERSON_OCCUPATION + " TEXT NOT NULL, " +
                COLUMN_PERSON_IMAGE + " BLOB NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }


    public void save(Model model){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Constants.COLUMN_PERSON_NAME,model.getName());
        cv.put(Constants.COLUMN_PERSON_OCCUPATION,model.getOccupation());
        cv.put(Constants.COLUMN_PERSON_IMAGE,model.getImage());
        //  insert
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }
    public Model getData(long id){
        SQLiteDatabase db=getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor=db.rawQuery(query,null);
        Model model=new Model();
        if (cursor.getCount()>0){
            cursor.moveToFirst();

            model.setName(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PERSON_NAME)));
            model.setOccupation(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PERSON_OCCUPATION)));
            model.setImage(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PERSON_IMAGE)));

        }
        return model;
    }

    public void deletePersonRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
     //   Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();
    }
    public List<Model> peopleList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<Model> personLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Model person;

        if (cursor.moveToFirst()) {
            do {
                person = new Model();

                person.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_NAME)));
                person.setOccupation(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_OCCUPATION)));
                person.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
                personLinkedList.add(person);
            } while (cursor.moveToNext());
        }


        return personLinkedList;
    }
    public void UpdateModel(long modelID,Context context,Model model){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE  "+TABLE_NAME+" SET name ='"+ model.getName() + "', occupation ='"+ model.getOccupation() + "', image ='"+ model.getImage() + "'  WHERE _id='" + modelID + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();

    }

}
