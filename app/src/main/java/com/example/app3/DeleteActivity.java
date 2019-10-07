package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {
    private int id;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        getValues();
    }

    public void confirmInput(View view) {
        Intent i = new Intent();
        i.putExtra("DELETE_ID", id);
        i.putExtra("DELETE_NAME", name);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    public void rejectInput(View view) {
        Intent i = new Intent();
        setResult(Activity.RESULT_CANCELED, i);
        finish();
    }


    private void getValues() {
        TextView tv = (TextView)findViewById(R.id.deleteText);
        Intent i = getIntent();
        name = i.getStringExtra("DELETE_NAME");
        id =  Integer.parseInt(i.getStringExtra("DELETE_ID"));
        tv.setText("Student: " + name + "\n Id:" + id);
    }
}
