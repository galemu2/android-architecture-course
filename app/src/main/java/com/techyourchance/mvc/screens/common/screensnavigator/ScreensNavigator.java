package com.techyourchance.mvc.screens.common.screensnavigator;

import android.content.Context;

import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity;

public class ScreensNavigator {

    private final Context mContext;

    public ScreensNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toDialogDetails(String questionId) {
        QuestionDetailsActivity.start(mContext, questionId);
    }


}
