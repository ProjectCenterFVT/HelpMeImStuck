package com.projectcenterfvt.helpmeimstuck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView test = findViewById(R.id.testText);
        //final Position Pos = new Position(this);
        final Position2 Pos = new Position2(this, test);
        Pos.setUp();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Pos.execute();
//                try {
//                    test.setText("Определилось местоположение широта =  " + Pos.get().getLatitude()+" долгота = "+Pos.get().getLongitude());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }


    public void getHelp(View view) {
        Intent intent = new Intent(this, GetHelpActivity.class);
        startActivity(intent);
    }

    public void giveHelp(View view) {
        Intent intent = new Intent(this, GiveHelpActivity.class);
        startActivity(intent);
    }

}
