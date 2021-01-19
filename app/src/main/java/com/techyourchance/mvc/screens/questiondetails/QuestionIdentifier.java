package com.techyourchance.mvc.screens.questiondetails;

import android.app.Activity;

import static com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity.EXTRA_QUESTION_ID;

public class QuestionIdentifier {
    private final QuestionDetailsActivity mContxt;

    public QuestionIdentifier(Activity context) {
        this.mContxt = (QuestionDetailsActivity) context;
    }

    public String getQuestionId() {
        return mContxt.getIntent().getStringExtra(EXTRA_QUESTION_ID );
    }
}
