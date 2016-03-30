package com.example.kenny.dicegame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView playerScoreTextView;
    TextView computerScoreTextView;
    TextView playerTurnTextView;
    ImageView diceImageView;

    int playerScore = 0;
    int computerScore = 0;
    int currentPlayer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playerScoreTextView = (TextView) findViewById(R.id.playerScoreTextView);
        computerScoreTextView = (TextView) findViewById(R.id.computerScoreTextView);
        playerTurnTextView = (TextView) findViewById(R.id.playerTurnTextView);
        diceImageView = (ImageView) findViewById(R.id.diceImageView);

        Button rollButton = (Button) findViewById(R.id.rollButton);
        assert rollButton != null;
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int diceValue = rand.nextInt(6) + 1;
                switch (currentPlayer) {
                    case 1:
                        if (diceValue < 3) {
                            playerScore = 0;
                            playerScoreTextView.setText("Player Score: 00");
                            currentPlayer = 2;
                            break;
                        } else {
                            playerScore += diceValue;
                            playerScoreTextView.setText("Player Score: " + playerScore);
                        }
                        break;
                    case 2:
                        if (diceValue < 3) {
                            computerScore = 0;
                            computerScoreTextView.setText("Computer Score: 00");
                            currentPlayer = 1;
                            break;
                        } else {
                            computerScore += diceValue;
                            computerScoreTextView.setText("Computer Score: " + computerScore);
                        }
                        break;
                    default:
                        break;
                }
                playerTurnTextView.setText("Player - " + currentPlayer + "'s turn.");

                // update dice image
                switch (diceValue) {
                    case 1:
                        diceImageView.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        diceImageView.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        diceImageView.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        diceImageView.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        diceImageView.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        diceImageView.setImageResource(R.drawable.dice6);
                        break;
                }

                if (playerScore > 49 || computerScore > 49) {
                    playerTurnTextView.setText("Player - " + currentPlayer + " wins the game.");
                }
            }
        });

        Button holdButton = (Button) findViewById(R.id.holdButton);
        assert holdButton != null;
        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //currentPlayer == 1 ? currentPlayer = 2 : currentPlayer = 1;
                if(currentPlayer == 1) {
                    currentPlayer = 2;
                } else {
                    currentPlayer = 1;
                }
                playerTurnTextView.setText("Player " + currentPlayer + "'s turn");
            }
        });

        Button resetbutton = (Button) findViewById(R.id.resetButton);
        assert resetbutton != null;
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerScore = computerScore = 0;
                currentPlayer = 1;
                playerScoreTextView.setText("Player Score: " + playerScore);
                computerScoreTextView.setText("Computer Score: " + computerScore);
                playerTurnTextView.setText("Player - 1's turn");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
