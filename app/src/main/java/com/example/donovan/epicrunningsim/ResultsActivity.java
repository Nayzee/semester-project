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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static java.lang.Integer.parseInt;

public class ResultsActivity extends AppCompatActivity {
    //create variable for back button
    private Button backButton;
    private Button upgradeButton;
    private Button runButton;
    private TextView coinsCollected;
    private TextView totalCoins;
    private TextView upgrades;
    private int numOfCoins;
    private int numOfUpgrades;
    int coinsPerRun;
    double upgradeMultiplier = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //attach variables to buttons and textviews in layout
        backButton = (Button) findViewById(R.id.btnBack);
        upgradeButton = (Button) findViewById(R.id.btnUpgrade);
        runButton = (Button) findViewById(R.id.btnRun);
        totalCoins = (TextView) findViewById(R.id.txtTotalCoins);
        coinsCollected = (TextView) findViewById(R.id.txtCoinsCollected);
        upgrades = (TextView) findViewById(R.id.txtNumberOfUpgrades);
        coinsPerRun = 50;



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

        upgradeMultiplier = (numOfUpgrades * 0.1) + 1;
        coinsPerRun = (int) Math.round(coinsPerRun * upgradeMultiplier);

        //update the display for the coins
        totalCoins.setText(getString(R.string.total_coins) + " " + sharedPrefCoins.getInt("numberOfCoins", 0));
        upgrades.setText(getString(R.string.upgrades) + " " + sharedPrefUpgrades.getInt("numberOfUpgrades", 0));



        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //display the number of coins collected in a run
                coinsCollected.setText(getString(R.string.coins_collected) + " " + coinsPerRun);

                //take the coins from this run and add it to the total coins
                numOfCoins = numOfCoins + coinsPerRun;

                //update the number of coins in the shared preferences file
                editorCoins.putInt("numberOfCoins", numOfCoins);
                editorCoins.apply();

                //update the display for the coins
                totalCoins.setText(getString(R.string.total_coins) + " " + sharedPrefCoins.getInt("numberOfCoins", 0));
                upgrades.setText(getString(R.string.upgrades) + " " + sharedPrefUpgrades.getInt("numberOfUpgrades", 0));

            }
        });

        //buy an upgrade
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numOfCoins >= 100) {
                    //subtract coins to purchase an upgrade
                    numOfCoins -= 100;
                    numOfUpgrades += 1;
                    upgradeMultiplier = (numOfUpgrades * 0.1) + 1;
                    coinsPerRun = (int) Math.round(50 * upgradeMultiplier);


                    //update the number of coins and the number of upgrades
                    editorUpgrades.putInt("numberOfUpgrades", numOfUpgrades);
                    editorUpgrades.apply();
                    editorCoins.putInt("numberOfCoins", numOfCoins);
                    editorCoins.apply();

                    //update the display for coins and upgrades
                    upgrades.setText(getString(R.string.upgrades) + " " + sharedPrefUpgrades.getInt("numberOfUpgrades", 0));
                    totalCoins.setText(getString(R.string.total_coins) + " " + sharedPrefCoins.getInt("numberOfCoins", 0));
                }
                else{
                    //let the user know if they do not have enough coins to buy an upgrade
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.no_coins_message), Toast.LENGTH_SHORT);
                    toast.show();
                }

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
