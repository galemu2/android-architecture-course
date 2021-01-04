package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseActivity;
import com.techyourchance.mvc.screens.common.MessagesDisplayer;

public class QuestionDetailsActivity extends BaseActivity {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private static final String TAG = "TAG";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private QuestionDetailController mQuestionDetailController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QuestionDetailsMvc mQuestionDetailsMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsMvc(null);
        mQuestionDetailController = getCompositionRoot().getQuestionDetailController();
        mQuestionDetailController.bind(mQuestionDetailsMvc);

        setContentView(mQuestionDetailsMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQuestionDetailController.onStart();
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mQuestionDetailController.onStop();
    }
}
