package com.phwang.go;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("+++++Go+++++", "MainActivity");

        View continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);
        View newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.continue_button:
                Log.d("+++++aaaGo+++++", "MainActivity");
                i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.new_game_button:
                Log.d("+++++bbbGo+++++", "MainActivity");
                i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.about_button:
                Log.d("+++++cccGo+++++", "MainActivity");
                i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.exit_button:
                Log.d("+++++dddGo+++++", "MainActivity");
                i = new Intent(this, About.class);
                startActivity(i);
                break;
        }
    }
}