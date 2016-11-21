package net.androidbootcamp.ivytechapp;

/**
 * Created by jamesdrewery on 11/15/16.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private Context mycontext;

    private String DB_PATH = mycontext.getApplicationContext().getPackageName() + "/databases/";
    private static final String DATABASE_NAME = "classrooms.sqlite";
    private static final String CLASSROOMS = "classrooms";
    //private static final int DATABASE_VERSION = 1;
    public SQLiteDatabase myDataBase;

    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        this.mycontext = context;
        boolean dbexist = checkdatabase();
        if (dbexist){
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }

    public void createdatabase(){
        boolean dbexist = checkdatabase();
        if (dbexist){
            //System.out.println("Database exists.");
        } else {
            this.getReadableDatabase();
            try{
                copydatabase();
            } catch(IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase(){
        boolean checkdb = false;
        try{
            String myPath = DB_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e){
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException{
        InputStream myinput = mycontext.getAssets().open(DATABASE_NAME);
        String outfilename = DB_PATH + DATABASE_NAME;
        OutputStream myoutput = new FileOutputStream("/data/data/net.androidbootcamp.ivytechapp/databases");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0){
            myoutput.write(buffer, 0 ,length);
        }
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException{
        String mypath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close(){
        if (myDataBase != null){
            myDataBase.close();
        }
        super.close();
    }

    // Getting single classroom
    public Classroom getClassroom(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectLatitude = "SELECT KEY_LATITUDE FROM" + CLASSROOMS + "WHERE KEY_ID =" + id;
        String selectLongitude = "SELECT KEY_LONGITUDE FROM" + CLASSROOMS + "WHERE KEY_ID =" + id;

        /*Cursor cursor = db.query(CLASSROOMS, new String[] { KEY_ID,
                        KEY_LATITUDE, KEY_LONGITUDE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();*/

        /*Classroom classroom = new Classroom(cursor.getString(0), Float.parseFloat(cursor.getString(1)),
                                            Float.parseFloat(cursor.getString(2)));*/
        /*Classroom classroom = new Classroom(cursor.getString(0), cursor.getString(1),
                cursor.getString(2));*/
        Classroom classroom = new Classroom(Float.parseFloat(selectLatitude), Float.parseFloat(selectLongitude));
        // return classroom
        return classroom;
    }
}
