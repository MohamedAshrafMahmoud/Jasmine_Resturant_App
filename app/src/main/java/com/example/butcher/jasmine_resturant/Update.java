package com.example.butcher.jasmine_resturant;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by Butcher on 21/08/2017.
 */

public class Update extends Activity {
    EditText firstname;
    EditText lastname;
    EditText username;
    EditText password;
    EditText repassword;
    EditText adress;
    EditText state;
    EditText zipcode;
    EditText mobilenumber;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        firstname = (EditText) findViewById(R.id.firstname_E);
        lastname = (EditText) findViewById(R.id.lastname_E);
        username = (EditText) findViewById(R.id.enteremail_E);
        password = (EditText) findViewById(R.id.password_E);
        repassword = (EditText) findViewById(R.id.repassword_E);
        adress = (EditText) findViewById(R.id.streetadress_E2);
        state = (EditText) findViewById(R.id.state_S);
        zipcode = (EditText) findViewById(R.id.zipcode_E);
        mobilenumber = (EditText) findViewById(R.id.mobile_E);


        SharedPreferences shared = getSharedPreferences("Reg", MODE_PRIVATE);
        String fname = shared.getString("First name", "");
        String lname = shared.getString("Last name", "");
        String usernamee = shared.getString("User name", "");
        String passwordd = shared.getString("Password", "");
        String repasswordd = shared.getString("Re password", "");
        String adresss = shared.getString("Adress", "");
        String statee = shared.getString("State", "");
        String zipcodee = shared.getString("Zip code", "");
        String mobile = shared.getString("Mobile number", "");

        firstname.setText(fname);
        lastname.setText(lname);
        username.setText(usernamee);
        password.setText(passwordd);
        repassword.setText(repasswordd);
        adress.setText(adresss);
        state.setText(statee);
        zipcode.setText(zipcodee);
        mobilenumber.setText(mobile);



    }


}
