package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.techyourchance.mvc.screens.common.controllers.BaseActivity;

public class QuestionDetailsActivity extends BaseActivity {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private static final String TAG = "TAG";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private QuestionDetailsController mQuestionDetailsController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QuestionDetailsMvc mQuestionDetailsMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsMvc(null);
        mQuestionDetailsController = getCompositionRoot().getQuestionDetailController();
        mQuestionDetailsController.bind(mQuestionDetailsMvc);

        setContentView(mQuestionDetailsMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQuestionDetailsController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mQuestionDetailsController.onStop();
    }
}
