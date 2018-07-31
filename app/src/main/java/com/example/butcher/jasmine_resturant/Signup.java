package com.example.butcher.jasmine_resturant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Butcher on 20/08/2017.
 */

public class Signup extends Activity {
    Spinner sp;
    ImageView imgSignUp;

    EditText firstname;
    EditText lastname;
    EditText username;
    EditText password;
    EditText repassword;
    EditText adress;
    EditText state;
    EditText zipcode;
    EditText mobilenumber;

    ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        initalize();

        arr = new ArrayList<String>();
        arr.add("cairo");
        arr.add("alex");
        arr.add("menofia");

        ArrayAdapter<String> arrad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        arrad.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        sp.setAdapter(arrad);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        action();

    }


    public void initalize() {
        sp = (Spinner) findViewById(R.id.city_s);
        imgSignUp = (ImageView) findViewById(R.id.signup_I2);

        firstname = (EditText) findViewById(R.id.firstname_E);
        lastname = (EditText) findViewById(R.id.lastname_E);
        username = (EditText) findViewById(R.id.enteremail_E);
        password = (EditText) findViewById(R.id.password_E);
        repassword = (EditText) findViewById(R.id.repassword_E);
        adress = (EditText) findViewById(R.id.streetadress_E2);
        state = (EditText) findViewById(R.id.state_S);
        zipcode = (EditText) findViewById(R.id.zipcode_E);
        mobilenumber = (EditText) findViewById(R.id.mobile_E);
    }


    public void action() {
        imgSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstname.getText().toString().length() == 0) {
                    firstname.setError("first name not entered");
                    firstname.requestFocus();
                }
                if (lastname.getText().toString().length() == 0) {
                    lastname.setError("last name not entered");
                    lastname.requestFocus();
                }
                if (username.getText().toString().length() == 0) {
                    username.setError("user name not entered");
                    username.requestFocus();
                }
                if (password.getText().toString().length() == 0) {
                    password.setError("password not entered");
                    password.requestFocus();
                }
                if (repassword.getText().toString().length() == 0) {
                    repassword.setError("repassword not entered");
                    repassword.requestFocus();
                }
                if (adress.getText().toString().length() == 0) {
                    adress.setError("adress not entered");
                    adress.requestFocus();
                }
                if (state.getText().toString().length() == 0) {
                    state.setError("state not entered");
                    state.requestFocus();
                }
                if (zipcode.getText().toString().length() == 0) {
                    zipcode.setError("zipcode not entered");
                    zipcode.requestFocus();
                }
                if (mobilenumber.getText().toString().length() == 0) {
                    mobilenumber.setError("mobilenumber not entered");
                    mobilenumber.requestFocus();
                }

                SharedPreferences.Editor shared = getSharedPreferences("Reg", MODE_PRIVATE).edit();
                shared.putString("First name", firstname.getText().toString());
                shared.putString("Last name", lastname.getText().toString());
                shared.putString("User name", username.getText().toString());
                shared.putString("Password", password.getText().toString());
                shared.putString("Re password", repassword.getText().toString());
                shared.putString("Adress", adress.getText().toString());
                shared.putString("State", state.getText().toString());
                shared.putString("Zip code", zipcode.getText().toString());
                shared.putString("Mobile number", mobilenumber.getText().toString());
                shared.commit();


                if (!firstname.getText().toString().isEmpty() && !lastname.getText().toString().isEmpty() && !username.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !repassword.getText().toString().isEmpty() && !adress.getText().toString().isEmpty() && !state.getText().toString().isEmpty() && !zipcode.getText().toString().isEmpty() && !mobilenumber.getText().toString().isEmpty()) {
                    Toast.makeText(null, "error", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(Signup.this, Update.class);
                    startActivity(i);
                }
            }
        });


    }


}
