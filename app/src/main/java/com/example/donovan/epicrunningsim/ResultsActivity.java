package com.example.donovan.epicrunningsim;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static java.lang.Integer.parseInt;

public class ResultsActivity extends AppCompatActivity {
    //create variable for back button
    private Button backButton;
    private TextView totalCoins;
    private int numOfCoins;
    int coinsPerRun = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //attach button variable to button in layout
        backButton = (Button) findViewById(R.id.btnBack);
        totalCoins = (TextView) findViewById(R.id.txtTotalCoins);
        //totalCoins.setText("Total Coins: " + numOfCoins);

        /*FileInputStream inputStream;

        try {
            inputStream = openFileInput("numberOfCoins");
            numOfCoins = inputStream.read();
        } catch(Exception e) {

        }
        numOfCoins = numOfCoins + 10;
        totalCoins.setText("Total Coins: " + numOfCoins);

        String filename = "numberOfCoins";
        String fileContents = numOfCoins + "";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/



        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score_key), newHighScore);*/



        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                "numberOfCoins", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        numOfCoins = sharedPref.getInt("numberOfCoins", 0);

        numOfCoins = numOfCoins + coinsPerRun;

        editor.putInt("numberOfCoins", numOfCoins);
        editor.apply();

        totalCoins.setText(getString(R.string.total_coins) + sharedPref.getInt("numberOfCoins", 0));










        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.number_of_coins), 5);
        editor.apply();
        editor.putInt(getString(R.string.number_of_coins), parseInt(getString(R.string.number_of_coins)) + 1);
        editor.apply();

        int test = sharedPref.getInt(getString(R.string.number_of_coins), 6);





        totalCoins.setText("" + test);*/










        //go back to main activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
