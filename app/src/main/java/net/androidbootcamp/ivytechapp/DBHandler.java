package net.androidbootcamp.ivytechapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by jamesdrewery on 10/26/16.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
    {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "";
    // Database Name
    //private static final String DB_NAME = "CLASSROOMS";
    private static final String DB_NAME = "CLASSROOMS";
    // Classroom table name
    private static final String CLASSROOMS = "CLASSROOMS";
    // Classroom Table Columns names
    private static final String KEY_ID = "roomNumber";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";

    private final Context context;
    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);

        if (android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            //opendatabase();
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases";
        }
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CLASSROOM_TABLE = "CREATE TABLE " + CLASSROOMS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LATITUDE + " FLOAT,"
        + KEY_LONGITUDE + " FLOAT" + ")";
        db.execSQL(CREATE_CLASSROOM_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CLASSROOMS);
        // Creating tables again
        onCreate(db);
    }

    public void opendatabase() throws SQLException{
        String mypath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    // Adding new classroom
    public void addClassroom(Classroom classroom) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LATITUDE, classroom.getLatitude());
        values.put(KEY_LONGITUDE, classroom.getLongitude());

        // Inserting Row
        db.insert(CLASSROOMS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single classroom
    public Classroom getClassroom(String id)
    {
        float latitude = (float)0.0;
        float longitude = (float)0.0;
        SQLiteDatabase db = this.getReadableDatabase();

        String selectLatLong = "SELECT LATITUDE, LONGITUDE FROM " + CLASSROOMS + " WHERE ROOM = " + id + ";";
        //String selectLongitude = "SELECT LONGITUDE FROM" + CLASSROOMS + "WHERE KEY_ID = " + id + ";";

        /*Cursor cursor = db.query(CLASSROOMS, new String[] { KEY_ID,
                        KEY_LATITUDE, KEY_LONGITUDE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);*/
        Cursor cursor = db.rawQuery(selectLatLong, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            latitude = Float.parseFloat(cursor.getString(0));
            longitude = Float.parseFloat(cursor.getString(1));
        /*Classroom classroom = new Classroom(cursor.getString(0), Float.parseFloat(cursor.getString(1)),
                                            Float.parseFloat(cursor.getString(2)));*/
        /*Classroom classroom = new Classroom(cursor.getString(0), cursor.getString(1),
                cursor.getString(2));*/
            // return classroom
        }
        Classroom classroom = new Classroom(latitude, longitude);
        return classroom;
    }

    // Getting All classrooms
    public List<Classroom> getAllClassrooms()
    {
        List<Classroom> classroomList = new ArrayList<Classroom>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + CLASSROOMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Classroom classroom = new Classroom();
                classroom.setRoomNumber(cursor.getString(0));
                classroom.setLatitude(Float.parseFloat(cursor.getString(1)));
                classroom.setLongitude(Float.parseFloat(cursor.getString(2)));
                // Adding contact to list
                classroomList.add(classroom);
            } while (cursor.moveToNext());
        }
        // return classroom list
        return classroomList;
    }

    // Getting classrooms Count
    public int getClassroomCount() {
        String countQuery = "SELECT  * FROM " + CLASSROOMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single classroom
    public int updateContact(Classroom classroom) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LATITUDE, classroom.getLatitude());
        values.put(KEY_LONGITUDE, classroom.getLongitude());

        // updating row
        return db.update(CLASSROOMS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(classroom.getRoomNumber()) });
    }

    // Deleting single classroom
    public void deleteClassroom(Classroom classroom) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CLASSROOMS, KEY_ID + " =?",
                new String[] {String.valueOf(classroom.getRoomNumber())});
        db.close();
    }
}


