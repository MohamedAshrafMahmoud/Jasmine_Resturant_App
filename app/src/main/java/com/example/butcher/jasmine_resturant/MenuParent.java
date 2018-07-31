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


public class MenuParent extends Activity {


    private ProgressBar pDialog;
    private ListView lv;
    public static String TITLE = "title"; //to use it in MenuParentAdapter which TiTLE equal variable take a value from url to use it in adapter
    public static String IMG = "image";
    public static String ID = "id";

    // URL to get contacts JSON
    private static String url ;

    ArrayList<HashMap<String, String>> menuArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_parent);



            url = "http://54.191.156.65/RestaurantServices/rest/category/getParentCategories/35/1";

        pDialog = (ProgressBar)findViewById(R.id.progressBar);

        menuArray = new ArrayList<HashMap<String, String>>();

        lv = (ListView)findViewById(R.id.list);//list view of MenuParent


         new menuParent().execute();//the class which extends asyn task


         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 TextView txtId = (TextView) view.findViewById(R.id.idtxt);//text view which carry id in menu_parent_item
                 String idGet = txtId.getText().toString();

                 SharedPreferences.Editor edit = getSharedPreferences("prefs", Context.MODE_PRIVATE).edit();
                 edit.putString("idcat", idGet);
                 edit.commit();

                 Intent intent=new Intent(MenuParent.this,Menu_levelTwo.class);
                 startActivity(intent);


             }
         });




     }




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private class  menuParent extends AsyncTask<Void, Void, Void> {

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
                if (JASON != null){
                    try {
                        JSONObject jsonObject = new JSONObject(JASON);

                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0 ; i<jsonArray.length() ; i++){

                            JSONObject J = jsonArray.getJSONObject(i);

                            String name = J.getString("name");
                            String id = J.getString("id");
                            String img = J.getString("image");

                            HashMap<String,String> thedatafromjson = new HashMap<String, String>();
                            thedatafromjson.put(TITLE,name);
                            thedatafromjson.put(ID,id);
                            thedatafromjson.put(IMG,img);

                            menuArray.add(thedatafromjson);

                        }
                    } catch (final JSONException e) {



                        MenuParent.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });


                    }
                }else {


                    MenuParent.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

                return null;
            }
/////////////////////////////////////////////
/// ///////////////
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);


                pDialog.setVisibility(View.GONE);


                lv.setAdapter(new MenuParentAdapter(MenuParent.this,menuArray));


            }

        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
