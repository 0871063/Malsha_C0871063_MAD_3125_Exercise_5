package com.example.exercise_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView playerOneScoreTV;
    private TextView playerTwoScoreTV;
    private ImageView playerOneScoreIV;
    private ImageView playerTwoScoreIV;
    private Button throwDiceBtn;
    private Button refreshBtn;

    private int player1Score = 0;
    private int player2Score = 0;

    int[] intArray = new int[]{ 1,2,3,4,5,6 };
    int[] dices = new int[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dices[0] = R.drawable.dice1;
        dices[1] = R.drawable.dice2;
        dices[2] = R.drawable.dice3;
        dices[3] = R.drawable.dice4;
        dices[4] = R.drawable.dice5;
        dices[5] = R.drawable.dice6;

        throwDiceBtn = (Button) findViewById(R.id.throwDiceBtn);
        refreshBtn = (Button) findViewById(R.id.refreshBtn);
        playerOneScoreTV = (TextView) findViewById(R.id.playerOneScoreTV);
        playerTwoScoreTV = (TextView) findViewById(R.id.playerTwoScoreTV);

        playerOneScoreIV = (ImageView) findViewById(R.id.playerOneIV);
        playerTwoScoreIV = (ImageView) findViewById(R.id.playerTwoIV);
        refreshBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                player1Score = 0;
                player2Score = 0;

                playerOneScoreTV.setText(String.valueOf(player1Score));
                playerTwoScoreTV.setText(String.valueOf(player2Score));
                playerOneScoreIV.setImageResource(R.drawable.dice1);
                playerTwoScoreIV.setImageResource(R.drawable.dice1);
                throwDiceBtn.setVisibility(View.VISIBLE);
                refreshBtn.setVisibility(View.GONE);
            }
        });

        throwDiceBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                int playerOneValue = getRandom(intArray);
                int playerTwoValue = getRandom(intArray);
                int playerOneImage = dices[playerOneValue-1];
                int playerTwoImage = dices[playerTwoValue-1];
                playerOneScoreIV.setImageResource(playerOneImage);
                playerTwoScoreIV.setImageResource(playerTwoImage);

                if(playerOneValue > playerTwoValue){
                    player1Score = player1Score + (playerOneValue - playerTwoValue);
                }else if (playerTwoValue > playerOneValue){
                    player2Score = player2Score + (playerTwoValue - playerOneValue);
                }
                playerOneScoreTV.setText(String.valueOf(player1Score));
                playerTwoScoreTV.setText(String.valueOf(player2Score));

                if (player1Score >= 20){
                    String text = "Winner is Player One";
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.ic_baseline_back_hand_24)
                            .setPositiveButton("Ok", (dialog, which) -> Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show())
                            .setTitle(text)
                            .setNegativeButton("Cancel", null)
                            .create()
                            .show();

                    throwDiceBtn.setVisibility(View.GONE);
                    refreshBtn.setVisibility(View.VISIBLE);

                }else if(player2Score >= 20){
                    String text = "Winner is Player Two";
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.ic_baseline_back_hand_24)
                            .setPositiveButton("Ok", (dialog, which) -> Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show())
                            .setTitle(text)
                            .setNegativeButton("Cancel", null)
                            .create()
                            .show();
                    throwDiceBtn.setVisibility(View.GONE);
                    refreshBtn.setVisibility(View.VISIBLE);
                }else {

                }
            }
        });
    }

    public void refreshGame(View view) {

    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
