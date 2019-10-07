package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    private int id;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getValues();
    }

    public void confirmInput(View view) {
        Intent i = new Intent();
        EditText et = findViewById(R.id.editEditText);
        String newName = et.getText().toString();
        i.putExtra("EDIT_ID", id);
        i.putExtra("EDIT_NAME", newName);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    public void rejectInput(View view) {
        Intent i = new Intent();
        setResult(Activity.RESULT_CANCELED, i);
        finish();
    }

    private void getValues() {
        EditText et = (EditText)findViewById(R.id.editEditText);
        Intent i = getIntent();
        name = i.getStringExtra("EDIT_NAME");
        id =  Integer.parseInt(i.getStringExtra("EDIT_ID"));
        et.setText(name);
    }
}
