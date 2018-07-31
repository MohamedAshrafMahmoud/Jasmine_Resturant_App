package com.example.butcher.jasmine_resturant;

/**
 * Created by Butcher on 12/09/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Menu_levelThree extends Activity {

    DatabaseHandler db;

    ImageView plus, minus;
    TextView prc;

    String name;
    String id;
    String img;
    String description;
    String price;


    TextView quant;
    TextView tot;

    int q = 1;

    // URL to get contacts JSON
    private static String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_level_three);

        db = new DatabaseHandler(Menu_levelThree.this);

        SharedPreferences share = getSharedPreferences("mmm", Context.MODE_PRIVATE);
        String id = share.getString("idcat", "");

        url = "http://54.191.156.65/RestaurantServices/rest/item/getItemDetails/35/" + id + "/1";

        new menuParent().execute();

        plus = (ImageView) findViewById(R.id.imag2);
        minus = (ImageView) findViewById(R.id.img3);
        quant = (TextView) findViewById(R.id.txt1);
        tot = (TextView) findViewById(R.id.textView6);
        prc = (TextView) findViewById(R.id.imageView);




        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                q++;

                quant.setText(String.valueOf(q));
                tot.setText(String.valueOf(Double.parseDouble(prc.getText().toString()) * q));

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (q <= 1) {
                    Toast.makeText(getApplicationContext(), "sorry...", Toast.LENGTH_SHORT).show();
                } else {
                    q--;
                    quant.setText(String.valueOf(q));
                    tot.setText(String.valueOf(Double.parseDouble(prc.getText().toString()) * q));
                }
            }
        });


    }


    //////////////////////////////////////////////////


    public void goside(View v) {


        Intent i = new Intent(Menu_levelThree.this, Menu_levelFour.class);
        startActivity(i);

    }


    public void tonext(View view) {

        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contacts(Integer.parseInt(id), name, description, quant.getText().toString(), price, tot.getText().toString()));


        Intent intent = new Intent(Menu_levelThree.this, Menu_levelFive.class);
        startActivity(intent);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

                    JSONObject J = jsonArray.getJSONObject(0);

                    name = J.getString("name");
                    id = J.getString("id");
                    img = J.getString("mainImage");
                    description = J.getString("description");
                    price = J.getString("price");


                } catch (final JSONException e) {


                    Menu_levelThree.this.runOnUiThread(new Runnable() {
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


                Menu_levelThree.this.runOnUiThread(new Runnable() {
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

            TextView title, idtxt;
            ImageView imglink;
            TextView ptxt;
            TextView desc;

            // Get the posirtion


            // Locate the TextViews in listview_item.xml
            title = (TextView) findViewById(R.id.Titlen);
            idtxt = (TextView) findViewById(R.id.textView8);
            // Locate the ImageView in listview_item.xml
            imglink = (ImageView) findViewById(R.id.img);
            ptxt = (TextView) findViewById(R.id.imageView);
            desc = (TextView) findViewById(R.id.desc);

            // Capture position and set results to the TextViews
            title.setText(name);
            idtxt.setText(id);
            ptxt.setText(price);
            tot.setText(price);
            desc.setText(description);
            Glide.with(Menu_levelThree.this).load("http://54.191.156.65/restaurant/uploadedFiles/" + img).into(imglink);

        }

    }


}