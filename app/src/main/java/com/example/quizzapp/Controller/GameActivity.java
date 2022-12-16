package com.example.quizzapp.Controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.Model.Question;
import com.example.quizzapp.Model.QuestionBank;
import com.example.quizzapp.R;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
//tes
    private TextView mQuestionView;
    private Button mAnswerOne;
    private Button mAnswerTwo;
    private Button mAnswerThree;
    private Button mAnswerFour;

    //exemple de 4 questions

    private Question question1;
    private Question question2;
    private Question question3;
    private Question question4;

    private QuestionBank mQuestionBank = GenerateQuestions();
    private Question mCurrentQuestion;

    private int NombresDeQuestions;
    private int mScores;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private boolean mEnableTouchEvents;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hidden title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionView = findViewById(R.id.game_activity_textview_question);
        mAnswerOne = findViewById(R.id.game_activity_button_1);
        mAnswerTwo = findViewById(R.id.game_activity_button_2);
        mAnswerThree = findViewById(R.id.game_activity_button_3);
        mAnswerFour = findViewById(R.id.game_activity_button_4);

        mAnswerOne.setOnClickListener(this);
        mAnswerTwo.setOnClickListener(this);
        mAnswerThree.setOnClickListener(this);
        mAnswerFour.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        DisplayQuestion(mCurrentQuestion);

        mEnableTouchEvents = true;
        NombresDeQuestions = 3;
        mScores = 0;

    }

    protected QuestionBank GenerateQuestions(){
        question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        return new QuestionBank(Arrays.asList(question1,question2,question3));
    }

    private void DisplayQuestion(final Question question){
        mQuestionView.setText(question.getmQuestion());
        mAnswerOne.setText(question.getmChoiceList().get(0));
        mAnswerTwo.setText(question.getmChoiceList().get(1));
        mAnswerThree.setText(question.getmChoiceList().get(2));
        mAnswerFour.setText(question.getmChoiceList().get(3));

    }

    @Override
    public void onClick(View v) {
        int index;

        if (v == mAnswerOne) {
            index = 0;
        } else if (v == mAnswerTwo) {
            index = 1;
        } else if (v == mAnswerThree) {
            index = 2;
        } else if (v == mAnswerFour) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view : " + v);
        }


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // If this is the last question, ends the game.
                // Else, display the next question.

                NombresDeQuestions --;
                if (NombresDeQuestions > 0){
                    mCurrentQuestion = mQuestionBank.getNextQuestion();
                    DisplayQuestion(mCurrentQuestion);
                }
                else {
                    //Quizz terminé
                    EndGame();
                }
                mEnableTouchEvents = true;

            }
        }, 2_000); // LENGTH_SHORT is usually 2 second long

        if (index == mQuestionBank.getCurrentQuestion().getmAnswerIndex()){
            Toast.makeText(this,"Réponse correct !",Toast.LENGTH_SHORT).show();
            mScores++;
        }
        else
            Toast.makeText(this,"Mince réponse incorrect !",Toast.LENGTH_SHORT).show();

        mEnableTouchEvents = false;

    }
    private void EndGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Tu as fini le quizz 🔥")
                .setMessage("Ton score est de : " + mScores+" points")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE,mScores);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}