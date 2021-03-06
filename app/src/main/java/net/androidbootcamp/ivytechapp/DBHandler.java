package net.androidbootcamp.ivytechapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


/**
 * Created by jamesdrewery on 10/26/16.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
    {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/net.androidbootcamp.ivytechapp/databases/";
    // Database Name
    private static final String DB_NAME = "classrooms.db";
    // Classroom table name
    private static final String CLASSROOMS = "classrooms";
    // Classroom Table Columns names
    private static final String _id = "_id";
    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";

    private final Context context;
    private SQLiteDatabase myDataBase;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist){
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.

            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error opening database");

            }
        }

    }

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e){

            //database does't exist yet.

        }

        if (checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open("databases/classrooms.db");

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException{

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

            super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Getting single classroom
    public Classroom getClassroom(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectLatLong = "SELECT LATITUDE, LONGITUDE FROM " + CLASSROOMS + " WHERE _id = '" + id + "'";

        Cursor cursor = db.rawQuery(selectLatLong, null);

        Classroom classroom = new Classroom();

        if (cursor != null && cursor.moveToFirst()) {

            cursor.moveToFirst();
            classroom.setLatitude(Float.parseFloat(cursor.getString(0)));
            classroom.setLongitude(Float.parseFloat(cursor.getString(1)));
            Classroom classroom1 = new Classroom(classroom.getLatitude(), classroom.getLongitude());
            return classroom1;

        } else {

            return null;

        }

    }

}


