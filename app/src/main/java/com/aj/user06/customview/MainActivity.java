package com.aj.user06.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RevealView revealView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        revealView = findViewById(R.id.reveal_view);
        revealView.setTitle("RevealView");
        revealView.setMessage("its a message contain reveal view");


    }
}
