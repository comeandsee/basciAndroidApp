package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity:";
    private static final int REQUEST_CODE_1 = 1;
    private static final int REQUEST_CODE_2 = 2;
    private static final int REQUEST_CODE_3 = 3;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        list = new ArrayList<String>();
        String[] students = getResources().getStringArray(R.array.students);
        Collections.addAll(list, students);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        //button add
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, REQUEST_CODE_1);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        long id = info.id;
        String str = (String) adapter.getItem(pos);
        switch (item.getItemId()) {
            case R.id.delete:
                showToast("Delete option selected:" + id + ":" + str);
                Intent i = new Intent(MainActivity.this, DeleteActivity.class);
                i.putExtra("DELETE_NAME", str);
                i.putExtra("DELETE_ID", id+"");
                startActivityForResult(i, REQUEST_CODE_2);
                return true;
            case R.id.edit:
                showToast("Edit option selected:" + id + ":" + str);
                Intent iEdit = new Intent(MainActivity.this, EditActivity.class);
                iEdit.putExtra("EDIT_NAME", str);
                iEdit.putExtra("EDIT_ID", id+"");
                startActivityForResult(iEdit, REQUEST_CODE_3);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showToast(String value){
        Toast.makeText(getApplicationContext(),
                value,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch(requestCode){
                case REQUEST_CODE_1:
                    String msg = data.getStringExtra("ADD_MESSAGE");
                    showToast("Added: " + msg);
                    list.add(msg);
                    adapter.notifyDataSetChanged();
                    break;
                case REQUEST_CODE_2:
                    if(resultCode== Activity.RESULT_OK)
                    {
                        int id = data.getIntExtra("DELETE_ID",0);
                        String name = data.getStringExtra("DELETE_NAME");
                        showToast("Deleted: " + name + ", id:" + id);
                        list.remove(list.get(id));
                        adapter.notifyDataSetChanged();
                        break;
                    }
                case REQUEST_CODE_3:
                    if(resultCode== Activity.RESULT_OK)
                    {
                        int id = data.getIntExtra("EDIT_ID",0);
                        String newName = data.getStringExtra("EDIT_NAME");
                        showToast("Edited: " + newName + ", id:" + id);
                        list.set(id,newName);
                        adapter.notifyDataSetChanged();
                        break;
                    }

            }
        }
    }

}
