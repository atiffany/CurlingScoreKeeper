package com.abbytiffany.android.shotrock.scorekeeper;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int scoreA = 0;
    int scoreB = 0;
    int ends = 1;
    int overallScoreA = 0;
    int overallScoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayScoreA(scoreA);
        displayScoreB(scoreB);
        displayEnds(ends);
        displayOverallScore();
        displayResetButton();
    }

    //displays the score for team A
    public void displayScoreA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText((String.valueOf(score)));
    }
    public void incrementA(View v) {
        if (ends <= 10 && scoreA < 8) {
            displayScoreA(++scoreA);
            displayScoreB(scoreB = 0);
        }
    }
    public void decrementA(View v) {
        if (ends <= 10 && scoreA > 0) displayScoreA(--scoreA);
    }
    //displays the score for team B
    public void displayScoreB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText((String.valueOf(score)));
    }
    public void incrementB(View v) {
        if (ends <= 10 && scoreB < 8) {
            displayScoreB(++scoreB);
            displayScoreA(scoreA = 0);
        }
    }
    public void decrementB(View v) {
        if (ends <=10 && scoreB > 0) displayScoreB(--scoreB);
    }

    // displays the current end
    public void displayEnds(int end) {
        TextView endsView = findViewById(R.id.ends_number);
        endsView.setText(ends <= 10 ? String.valueOf(end) : "Game Has Concluded");
    }
    public void newEnd(View v) {
        if (ends < 10) {
            displayEnds(++ends);
            displayOverallScore();
        } else if (ends == 10) {
            displayEnds(++ends);
            displayOverallScoreTitle();
            displayOverallScore();
            displayResetButton();
        }
    }

    // change title to Final Score if the game is over
    public void displayOverallScoreTitle() {
        if (ends > 10) {
            TextView overallTitleView = findViewById(R.id.overall_score_title);
            overallTitleView.setText("Final Score");
        }
    }

    //displays the overall game score to date
    public void displayOverallScore() {
        overallScoreA += scoreA > scoreB ? scoreA-scoreB : 0;
        overallScoreB += scoreB > scoreA ? scoreB-scoreA : 0;

        displayScoreA(scoreA = 0);
        displayScoreB(scoreB = 0);

        TextView overallView = findViewById(R.id.overall_score);
        overallView.setText(overallScoreA + " : " + overallScoreB);
    }

    //sets the color of the reset button
    public void displayResetButton() {
        TextView gameResetView = findViewById(R.id.new_game_button);
        if (ends <= 10) {
            gameResetView.setBackgroundColor(Color.parseColor("#808080"));
        } else {
            gameResetView.setBackgroundColor(Color.parseColor("#FF9800"));

        }
    }


    //create a snack bar
    public void resetButton(View v) {
        final Snackbar reset = Snackbar
                .make(findViewById(R.id.myCoordinatorLayout), "Game In Progress. End It?", Snackbar.LENGTH_LONG)
                .setAction("RESET GAME", new View.OnClickListener() {
                    public void onClick(View v) {
                        resetAll();
                    }
                });
        findViewById(R.id.myCoordinatorLayout).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { reset.dismiss(); }
        });


        if (ends <= 10) {
            reset.show();
        } else {
            resetAll();
        }
    }


    //reset entire page
    public void resetAll() {
            displayScoreA(scoreA = 0);
            displayScoreB(scoreB = 0);
            displayEnds(ends = 1);
            overallScoreA = 0;
            overallScoreB = 0;
            displayOverallScore();
            displayResetButton();
    }

    public void ChangeName(View v) {
        findViewById(R.id.team_a_name).setOnClickListener(new View.OnClickListener() {
            public void OnClick(View v) { findViewById(R.id.team_a_name).setText("Team Butt"); }
        });
    }
}



