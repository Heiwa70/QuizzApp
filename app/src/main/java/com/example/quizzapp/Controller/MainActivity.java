package com.example.quizzapp.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizzapp.Model.QuizzData;
import com.example.quizzapp.Model.QuizzDataCall;
import com.example.quizzapp.Model.User;
import com.example.quizzapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTilteTextView;
    private TextView mWelcome;
    private EditText mNameEditText;
    private Button mPlayButton;
    private User mUser;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final String SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE";

    @Override
    //On attend le resultat de l'activity GameActivity savoir le score du joeur et on
    //enregistre dans les SharedPreferences son nom avec son score
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mUser.setmScore(score);
            Log.d("Score", "Récuperer de l'intent = " + String.valueOf(score));
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putString(SHARED_PREF_USER_INFO_NAME, mUser.getmFristName())
                    .putString(SHARED_PREF_USER_INFO_SCORE, String.valueOf(mUser.getmScore()))
                    .apply();
            RealodWelcomeText();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTilteTextView = findViewById(R.id.main_textview_title);
        mWelcome = findViewById(R.id.main_welcome);
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);
        mUser = new User();

        mPlayButton.setEnabled(false);

        String mFirstName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);

        //Hidden title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayButton.setEnabled(!s.toString().isEmpty());
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser.setmFristName(mNameEditText.getText().toString());

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);

            }
        });
            RealodWelcomeText();




    }

    private void RealodWelcomeText(){
        String firstNameShared = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                .getString(SHARED_PREF_USER_INFO_NAME, null);

        String scoreShared = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                .getString(SHARED_PREF_USER_INFO_SCORE, null);
        if (firstNameShared == null)
            mWelcome.setText("Bienvenue à toi nouveau joueur 🤗");
        else {
            mWelcome.setText("Hey " + firstNameShared + " reviens tenter ta chance tu avais fait " +
                    scoreShared + " points la dernière fois !");
            mNameEditText.setText(firstNameShared);
        }
    }



}