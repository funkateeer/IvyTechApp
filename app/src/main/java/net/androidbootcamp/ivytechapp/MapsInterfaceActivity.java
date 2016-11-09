package net.androidbootcamp.ivytechapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MapsInterfaceActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_interface);
        editText = (EditText)findViewById(R.id.editText);
        editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MapsInterfaceActivity.this, MapsActivity.class));
            }
        });
    }
}
