package com.example.app3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity  {
    private static final String TAG="AddActivity:";
    private static EditText textInputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        textInputName = findViewById(R.id.addEditText);
    }

    public static boolean vaildateName() {
        String nameInput = getName().trim();
        if (nameInput.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return false;}
        else {
            textInputName.setError(null);
                return true;
             }
    }

    public void confirmInput(View view) {
        if (vaildateName()) {
            Intent i = new Intent();
            i.putExtra("ADD_MESSAGE", getName());
            setResult(Activity.RESULT_OK, i);
            finish();
        }
    }

    private static String getName(){
        return textInputName.getText().toString();
    }
}
