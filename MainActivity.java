package com.example.dicejava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int player1Score = 0;
    private int player2Score = 0;
    private int rollCount = 0;
    private final int maxRolls = 10;  // Game ends after 10 rolls

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView diceImage = findViewById(R.id.imageview);
        TextView tvPlayer1Score = findViewById(R.id.tvPlayer1Score);
        TextView tvPlayer2Score = findViewById(R.id.tvPlayer2Score);
        TextView tvWinner = findViewById(R.id.tvWinner);
        Button btnRollDice = findViewById(R.id.btnRollDice);

        btnRollDice.setOnClickListener(view -> {
            int diceNo = new Random().nextInt(6) + 1;
            int drawResource;

            // Set the drawable resource based on dice roll
            switch (diceNo) {
                case 1:
                    drawResource = R.drawable.one;
                    break;
                case 2:
                    drawResource = R.drawable.two;
                    break;
                case 3:
                    drawResource = R.drawable.three;
                    break;
                case 4:
                    drawResource = R.drawable.four;
                    break;
                case 5:
                    drawResource = R.drawable.five;
                    break;
                case 6:
                    drawResource = R.drawable.six;
                    break;
                default:
                    throw new IllegalArgumentException("Illegal Input: " + diceNo);
            }
            diceImage.setImageResource(drawResource);

            // Roll dice and update scores if rollCount is less than maxRolls
            if (rollCount < maxRolls) {
                int player1Roll = new Random().nextInt(6) + 1;
                int player2Roll = new Random().nextInt(6) + 1;

                player1Score += player1Roll;
                player2Score += player2Roll;
                rollCount++;

                // Update score display
                tvPlayer1Score.setText("Player 1 Score: " + player1Score);
                tvPlayer2Score.setText("Player 2 Score: " + player2Score);
            }

            // Check if maximum rolls reached
            if (rollCount == maxRolls) {
                btnRollDice.setEnabled(false);  // Disable button

                // Determine winner
                tvWinner.setVisibility(TextView.VISIBLE);
                if (player1Score > player2Score) {
                    tvWinner.setText("Winner: Player 1!");
                } else if (player2Score > player1Score) {
                    tvWinner.setText("Winner: Player 2!");
                } else {
                    tvWinner.setText("It's a Tie!");
                }
            }
        });
    }
}