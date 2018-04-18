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
    private Button upgradeButton;
    private TextView coinsCollected;
    private TextView totalCoins;
    private TextView upgrades;
    private int numOfCoins;
    private int numOfUpgrades;
    int coinsPerRun = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //attach button variable to button in layout
        backButton = (Button) findViewById(R.id.btnBack);
        upgradeButton = (Button) findViewById(R.id.btnUpgrade);
        totalCoins = (TextView) findViewById(R.id.txtTotalCoins);
        coinsCollected = (TextView) findViewById(R.id.txtCoinsCollected);
        upgrades = (TextView) findViewById(R.id.txtNumberOfUpgrades);


        coinsCollected.setText(getString(R.string.coins_collected) + " " + coinsPerRun);


        //create file to store coins and upgrades
        final SharedPreferences sharedPrefCoins = getApplicationContext().getSharedPreferences(
                "numberOfCoins", Context.MODE_PRIVATE);
        final SharedPreferences sharedPrefUpgrades = getApplicationContext().getSharedPreferences(
                "numberOfUpgrades", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorCoins = sharedPrefCoins.edit();
        final SharedPreferences.Editor editorUpgrades = sharedPrefUpgrades.edit();

        //get the number of coins and upgrades the user currently has
        numOfCoins = sharedPrefCoins.getInt("numberOfCoins", 0);
        numOfUpgrades = sharedPrefUpgrades.getInt("numberOfUpgrades", 0);

        //take the coins from this run and add it to the total coins
        numOfCoins = numOfCoins + coinsPerRun;

        //update the number of coins in the shared preferences file
        editorCoins.putInt("numberOfCoins", numOfCoins);
        editorCoins.apply();

        //update the display for the coins
        totalCoins.setText(getString(R.string.total_coins) + " " + sharedPrefCoins.getInt("numberOfCoins", 0));

        //buy an upgrade
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //subtract coins to purchase an upgrade
                numOfCoins -= 100;
                numOfUpgrades += 1;

                //update the number of coins and the number of upgrades
                editorUpgrades.putInt("numberOfUpgrades", numOfUpgrades);
                editorUpgrades.apply();
                editorCoins.putInt("numberOfCoins", numOfCoins);
                editorCoins.apply();

                //update the display for coins and upgrades
                upgrades.setText("Number of Upgrades: " + sharedPrefUpgrades.getInt("numberOfUpgrades", 0));
                totalCoins.setText(getString(R.string.total_coins) + " " + sharedPrefCoins.getInt("numberOfCoins", 0));
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
