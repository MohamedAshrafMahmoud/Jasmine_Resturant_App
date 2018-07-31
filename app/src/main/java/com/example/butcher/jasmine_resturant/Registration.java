package com.example.butcher.jasmine_resturant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Butcher on 20/08/2017.
 */

public class Registration extends Activity {
    ImageView iv;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        initalize();
        action();

    }

    public void initalize()
    {
        iv = (ImageView) findViewById(R.id.signupI);
    }

    public void action() {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registration.this, Signup.class);
                startActivity(i);
            }
        });
    }
}