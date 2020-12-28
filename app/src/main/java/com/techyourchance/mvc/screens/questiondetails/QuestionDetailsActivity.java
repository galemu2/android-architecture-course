package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseActivity;

public class QuestionDetailsActivity extends BaseActivity implements FetchQuestionDetailsUseCase.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private static final String TAG = "TAG";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private QuestionDetailsMvc mQuestionDetailsMvc;
    private FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase();
        mQuestionDetailsMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsMvc(null);
        setContentView(mQuestionDetailsMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestionDetailsUseCase.registerListener(this);
        mQuestionDetailsMvc.fetchStarting();
        fetchQuestionDetailsUseCase.fetchQuestionDetailAndNotify(getQuestionId());
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fetchQuestionDetailsUseCase.unregisterListener(this);
    }

    private void bindQuestionDetails(QuestionDetails questionDetails) {
        mQuestionDetailsMvc.fetchingSuccess();
        mQuestionDetailsMvc.bindQuestionDetails(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        bindQuestionDetails(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetchFailed() {
        mQuestionDetailsMvc.fetchingFail();
        Toast.makeText(this, "network call fail", Toast.LENGTH_SHORT).show();
    }
}
