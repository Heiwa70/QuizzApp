package com.example.quizzapp.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.Model.Question;
import com.example.quizzapp.Model.QuestionBank;
import com.example.quizzapp.Model.QuizzData;
import com.example.quizzapp.Model.QuizzDataCall;
import com.example.quizzapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, QuizzDataCall.Callbacks {
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

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int NombresDeQuestions;
    private int mScores;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private boolean mEnableTouchEvents;

    private List<QuizzData> quizzData;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    public void setQuizzData(List<QuizzData> quizzData) {
        this.quizzData = quizzData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HttpRequest();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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



        mEnableTouchEvents = true;
        NombresDeQuestions = 3;
        mScores = 0;


    }
    public void HttpRequest(){
        QuizzDataCall.fetchUserFollowing(this, "3");
    }

    @Override
    public void onResponse(@Nullable List<QuizzData> users) {
        this.quizzData = users;
        Log.d("STATUS",String.valueOf(this.quizzData.get(0).getIncorrectAnswers()));
        mQuestionBank = GenerateQuestions(users);
        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        DisplayQuestion(mCurrentQuestion);
    }

    @Override
    public void onFailure() {

    }
    protected QuestionBank GenerateQuestions(List<QuizzData> data){
        int pos = RandomNumber(4);
        int y = 0;
        List<String> res1 = new ArrayList<>(5);
        for (int i = 0; i < 4; i++) {
            if (i == pos)
                res1.add(data.get(0).getCorrectAnswer());
            else
                res1.add("0");
        }
        for (int i = 0; i < 4; i++) {
            if (res1.get(i) == "0"){
                res1.set(i,data.get(0).getIncorrectAnswers().get(y));
                y++;
            }

        }
Log.d("TAB : ", String.valueOf(res1.size()));
        question1 = new Question(
                data.get(0).getQuestion(),
                Arrays.asList(
                        res1.get(0),
                        res1.get(1),
                        res1.get(2),
                        res1.get(3)
                ),
                pos
        );
        pos = RandomNumber(4);
        y = 0;
        List<String> res2 = new ArrayList<>(5);
        for (int i = 0; i < 4; i++) {
            if (i == pos)
                res2.add(data.get(1).getCorrectAnswer());
            else
                res2.add("0");
        }
        for (int i = 0; i < 4; i++) {
            if (res2.get(i) == "0"){
                res2.set(i,data.get(1).getIncorrectAnswers().get(y));
                y++;
            }

        }
        question2 = new Question(
                data.get(1).getQuestion(),
                Arrays.asList(
                        res2.get(0),
                        res2.get(1),
                        res2.get(2),
                        res2.get(3)
                ),
                pos
        );

        pos = RandomNumber(4);
        y = 0;
        List<String> res3 = new ArrayList<>(5);
        for (int i = 0; i < 4; i++) {
            if (i == pos)
                res3.add(data.get(1).getCorrectAnswer());
            else
                res3.add("0");
        }
        for (int i = 0; i < 4; i++) {
            if (res3.get(i) == "0"){
                res3.set(i,data.get(1).getIncorrectAnswers().get(y));
                y++;
            }

        }
        question3 = new Question(
                data.get(2).getQuestion(),
                Arrays.asList(
                        res3.get(0),
                        res3.get(1),
                        res3.get(2),
                        res3.get(3)
                ),
                pos
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

    private int RandomNumber(int nbReponse){
        return new Random().nextInt(nbReponse);
    }

}