package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailsActivity extends BaseActivity {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private static final String TAG = "TAG";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private StackoverflowApi mStackoverflowApi;
    private QuestionDetailsMvc mQuestionDetailsMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStackoverflowApi = getCompositionRoot().getStackoverflowApi();

        mQuestionDetailsMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsMvc(null);
        setContentView(mQuestionDetailsMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQuestionDetailsMvc.fetchStarting();
        fetchQuestionDetail();
    }

    private void fetchQuestionDetail() {
        String questionId = getIntent().getStringExtra(EXTRA_QUESTION_ID);
        mStackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if (response.isSuccessful() && response.body() != null) {


                            bindQuestionDetails(response.body().getQuestion());

                        } else {
                            networkFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        networkFail();
                    }
                });
    }

    private void bindQuestionDetails(QuestionSchema schema) {
        mQuestionDetailsMvc.fetchingSuccess();
        QuestionDetails mQuestionDetails = new QuestionDetails(schema.getId(), schema.getTitle(), schema.getBody());
        mQuestionDetailsMvc.bindQuestionDetails(mQuestionDetails);
    }

    private void networkFail() {
        mQuestionDetailsMvc.fetchingFail();
        Toast.makeText(this, "network call fail", Toast.LENGTH_SHORT).show();
    }


}
