package com.example.donovan.epicrunningsim;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    //create variable for back button
    private Button backButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);




        //attach button variable to button in layout
        backButton = (Button) findViewById(R.id.btnBack);
        deleteButton = (Button) findViewById(R.id.btnDelete);









        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                        "numberOfCoins", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putInt("numberOfCoins", 0);
                editor.apply();

                Toast toast = Toast.makeText(getApplicationContext(), "Your coins have been set to 0.", Toast.LENGTH_SHORT);
                toast.show();


            }
        });

        //go back to main activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
