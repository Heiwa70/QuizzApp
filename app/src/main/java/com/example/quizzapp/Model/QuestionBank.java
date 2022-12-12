package com.example.quizzapp.Model;

import com.example.quizzapp.Model.Question;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionBank{
    private final List<Question> mQuestionList;
    private int mQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList,new Random(3));
    }

    public Question getCurrentQuestion() {
        return mQuestionList.get(mQuestionIndex);
    }

    public Question getNextQuestion() {
        mQuestionIndex++;
        return getCurrentQuestion();
    }
}
