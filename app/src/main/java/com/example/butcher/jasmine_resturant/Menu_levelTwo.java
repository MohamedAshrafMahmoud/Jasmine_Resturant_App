package com.example.butcher.jasmine_resturant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Menu_levelTwo extends Activity {


    private ProgressBar pDialog;
    private ListView lv;

    public static String NAME = "name";
    public static String IMG = "image";
    public static String ID = "id";
    public static String TYPE = "type";
    public static String DESCRIPTION = "descerption";
    public static String PRICE = "PRICE";


    // URL to get contacts JSON
    private static String url;

    ArrayList<HashMap<String, String>> menuArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_leveltwo);

        SharedPreferences share = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String idd = share.getString("idcat", "");//to get id from last layout


        url = "http://54.191.156.65/RestaurantServices/rest/content/getContents/35/" + idd + "/1";


        pDialog = (ProgressBar) findViewById(R.id.progressBar2);


        menuArray = new ArrayList<HashMap<String, String>>();
        lv = (ListView) findViewById(R.id.list2);


        new menuParent().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView txtId = (TextView) view.findViewById(R.id.idtxt2);
                String idGet = txtId.getText().toString();

                
                SharedPreferences.Editor edit = getSharedPreferences("mmm", Context.MODE_PRIVATE).edit();
                edit.putString("idcat", idGet);
                edit.commit();

                Intent intent=new Intent(Menu_levelTwo.this,Menu_levelThree.class);
                startActivity(intent);


            }
        });


    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class menuParent extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog.setVisibility(View.VISIBLE);

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

                        String name = J.getString("name");
                        String id = J.getString("id");
                        String img = J.getString("image");
                        String typ = J.getString("type");
                        String desc = J.getString("description");
                        String price = J.getString("price");


                        HashMap<String, String> thedatafromjson = new HashMap<String, String>();
                        thedatafromjson.put(NAME, name);
                        thedatafromjson.put(ID, id);
                        thedatafromjson.put(IMG, img);
                        thedatafromjson.put(TYPE, typ);
                        thedatafromjson.put(DESCRIPTION, desc);
                        thedatafromjson.put(PRICE, price);


                        menuArray.add(thedatafromjson);

                    }
                } catch (final JSONException e) {


                    Menu_levelTwo.this.runOnUiThread(new Runnable() {
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


                Menu_levelTwo.this.runOnUiThread(new Runnable() {
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


            pDialog.setVisibility(View.GONE);


            lv.setAdapter(new MenuAdapter_LevelTwo(Menu_levelTwo.this, menuArray));


        }

    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
