package com.aliberkaygdk.rickandmorty.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aliberkaygdk.rickandmorty.R;


public class SplashActivity extends AppCompatActivity {

    TextView text;
    SharedPreferences sharedPreferences;
    String sText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        text = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.aliberkaygdk.rickandmorty", Context.MODE_PRIVATE);
        sText = sharedPreferences.getString("text", "Welcome!");
        text.setText(sText);

        sharedPreferences.edit().putString("text","Hello!").apply();

        animation();

    }


    private void animation() {

        Thread logoAnimation = new Thread() {
            @Override
            public void run() {
                ImageView logo = findViewById(R.id.imageView2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_logo);
                logo.startAnimation(animation);

            }
        };
        logoAnimation.start();

        Thread textAnimation = new Thread() {
            @Override
            public void run() {


                TextView text = findViewById(R.id.textView);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_text);
                text.startAnimation(animation2);
            }
        };
        textAnimation.start();
        Thread redirect = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        redirect.start();
    }

}
