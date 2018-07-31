package com.example.butcher.jasmine_resturant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView reg;
    ImageView conus;
    ImageView men;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalize();
        action();

    }


    public void initalize() {
        reg = (ImageView) findViewById(R.id.registration_I);
        conus = (ImageView) findViewById(R.id.contactus_I);
        men = (ImageView) findViewById(R.id.menuI);

    }

    public void action() {
        conus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Contact_us.class);
                startActivity(i);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity(i);
            }
        });
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MenuParent.class);
                startActivity(i);
            }
        });


    }


}
