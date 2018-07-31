package com.example.butcher.jasmine_resturant;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu_levelFive extends Activity {

    DatabaseHandler db ;
    MenuAdapter_LevelFive listViewAdapter;

     ListView lv;

    ArrayList<HashMap<String, String>> menuArray= new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_level_five);

        db = new DatabaseHandler(this);

        lv = (ListView) findViewById(R.id.list5);



      //  Log.d("Reading: ", "Reading all contacts..");
        ArrayList<Contacts> contacts = db.getAllContacts();


        listViewAdapter = new MenuAdapter_LevelFive(Menu_levelFive.this,contacts);
        lv.setAdapter(listViewAdapter);

    }
}
