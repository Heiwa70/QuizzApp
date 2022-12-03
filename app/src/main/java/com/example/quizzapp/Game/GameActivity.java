package com.example.quizzapp.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.R;

public class GameActivity extends AppCompatActivity {

    private TextView mQuestionView;
    private Button mAnswerOne;
    private Button mAnswerTwo;
    private Button mAnswerThree;
    private Button mAnswerFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionView = findViewById(R.id.game_activity_textview_question);
        mAnswerOne = findViewById(R.id.game_activity_button_1);
        mAnswerTwo = findViewById(R.id.game_activity_button_2);
        mAnswerThree = findViewById(R.id.game_activity_button_3);
        mAnswerFour = findViewById(R.id.game_activity_button_4);

    }
}