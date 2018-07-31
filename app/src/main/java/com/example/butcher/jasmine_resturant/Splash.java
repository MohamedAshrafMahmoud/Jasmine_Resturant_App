package com.example.butcher.jasmine_resturant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Butcher on 28/08/2017.
 */

public class Splash extends Activity {
    int timeSec;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //  new Handler().postDelayed(new Runnable() {
        Thread splash_screen=new Thread(){
            //for not moving to any activity after 3000
            @Override
            public void run() {
                // This method will be executed once the timer is over
                timeSec=2000;
                // Start your app main activity
                try {
                    sleep(timeSec);

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(Splash.this, MainActivity.class);

                    startActivity(intent);
                    finish();
                    //finsh to apper one time not for back

                }
            }  };
        splash_screen.start();


    }

}
