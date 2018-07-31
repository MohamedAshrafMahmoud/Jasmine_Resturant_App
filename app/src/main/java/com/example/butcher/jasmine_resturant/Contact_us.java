package com.example.butcher.jasmine_resturant;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Butcher on 21/08/2017.
 */

public class Contact_us extends Activity {
    Button B;
    Button B2;
    TextView et2;
    TextView et;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        initalize();
        action();

    }

    public void initalize() {
        B = (Button) findViewById(R.id.call_B);
        B2 = (Button) findViewById(R.id.emailus_B);
        et2 = (TextView) findViewById(R.id.account_T);
        et = (TextView) findViewById(R.id.number_T);
        iv1 = (ImageView) findViewById(R.id.facebook_I);
        iv2 = (ImageView) findViewById(R.id.tweeter_I);
        iv3 = (ImageView) findViewById(R.id.instgram_I);
        iv4 = (ImageView) findViewById(R.id.youtube_I);
    }

    public void action() {
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = et.getText().toString();
                Intent callintent = new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:" + num));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callintent);
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String acc = et2.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent face = new Intent(Intent.ACTION_VIEW);
                face.setData(Uri.parse("https://www.facebook.com/profile.php?id=100009461159877"));
                startActivity(face);
            }
        });
        //there is a permattion in manifests
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tweet = new Intent(Intent.ACTION_VIEW);
                tweet.setData(Uri.parse("https://www.twitter.com"));
                startActivity(tweet);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta = new Intent(Intent.ACTION_VIEW);
                insta.setData(Uri.parse("https://www.instgram.com"));
                startActivity(insta);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yout = new Intent(Intent.ACTION_VIEW);
                yout.setData(Uri.parse("https://www.youtube.com"));
                startActivity(yout);
            }
        });

    }
    public void tomap(View v){
        Intent i=new Intent(Contact_us.this,Map.class);
        startActivity(i);
    }
}
