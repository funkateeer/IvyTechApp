package net.androidbootcamp.ivytechapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MapsInterfaceActivity extends AppCompatActivity {
    public String roomNum;
    public DBHandler db;
    public DatabaseOpenHelper data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_interface);
        db = new DBHandler(this);

        final EditText room = (EditText)findViewById(R.id.editText);
        room.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        Button button = (Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                roomNum = room.getText().toString();
                db.getClassroom(roomNum);
                startActivity(new Intent(MapsInterfaceActivity.this, MapsActivity.class));
            }
        });
    }
}
