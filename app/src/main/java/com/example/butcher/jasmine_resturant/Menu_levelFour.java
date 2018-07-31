package com.example.butcher.jasmine_resturant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu_levelFour extends AppCompatActivity {


    private ListView lv;

    public static String ID = "id";
    public static String NAME = "name";
    public static String IMG = "image";
    public static String DESCRIPTION = "descerption";


    // URL to get contacts JSON
    private static String url;

    ArrayList<HashMap<String, String>> menuArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_level_four);

       
//
//
       url = "http://54.191.156.65/RestaurantServices/rest/sideDish/getAllSideDishes/35/1";
//
//
//
        menuArray = new ArrayList<HashMap<String, String>>();
        lv = (ListView) findViewById(R.id.list3);


        new menuParent().execute();


    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class menuParent extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        ////////////////////////////////////////////////////
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHelper = new HttpHandler();

            String JASON = httpHelper.serviceCall(url);       //take url and return  string
            if (JASON != null) {
                try {
                    JSONObject jsonObject = new JSONObject(JASON);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject J = jsonArray.getJSONObject(i);

                        String id = J.getString("id");
                        String name = J.getString("name");
                        String img = J.getString("image");
                        String desc = J.getString("description");


                        HashMap<String, String> thedatafromjson = new HashMap<String, String>();
                        thedatafromjson.put(ID, id);
                        thedatafromjson.put(NAME, name);
                        thedatafromjson.put(IMG, img);
                        thedatafromjson.put(DESCRIPTION, desc);

                        menuArray.add(thedatafromjson);

                    }
                } catch (final JSONException e) {


                    Menu_levelFour.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });


                }
            } else {


                Menu_levelFour.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn'tot get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        ////////////////////////////////////////////////////////////
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


            lv.setAdapter(new MenuAdapter_levelFour(Menu_levelFour.this, menuArray));


        }

    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
