package com.techyourchance.mvc.screens.questiondetails;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import static com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity.EXTRA_QUESTION_ID;

public class QuestionIdentifyer {
    private final QuestionDetailsActivity mContxt;

    public QuestionIdentifyer(Activity context) {
        this.mContxt = (QuestionDetailsActivity) context;
    }

    public String getQuestionId() {
        return mContxt.getIntent().getStringExtra(EXTRA_QUESTION_ID );
    }
}
