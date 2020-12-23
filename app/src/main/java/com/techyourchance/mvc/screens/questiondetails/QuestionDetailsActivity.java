package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
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
    private String questionId;
    private QuestionDetailsMvc mQuestionDetailsMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStackoverflowApi = getCompositionRoot().getStackoverflowApi();
        questionId = getIntent().getStringExtra(EXTRA_QUESTION_ID);
        mQuestionDetailsMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsMvc(null);
        setContentView(mQuestionDetailsMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();

        fetchQuestionDetail();
    }

    private void fetchQuestionDetail() {
        mStackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if (response.isSuccessful()) {
                            QuestionSchema schema = response.body().getQuestion();
                            String title = schema.getTitle();
                            String body = schema.getBody();

                            Log.d(TAG, title);
                            Log.d(TAG, body);
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        Log.e(TAG, "Something is wrong");
                    }
                });
    }


}
