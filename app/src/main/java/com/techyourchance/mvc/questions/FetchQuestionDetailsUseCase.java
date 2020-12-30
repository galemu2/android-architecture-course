package com.techyourchance.mvc.questions;

import android.widget.Toast;

import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Encapsulate application flows and processes
public class FetchQuestionDetailsUseCase extends BaseObservableViewMvc<FetchQuestionDetailsUseCase.Listener> {

    public interface Listener {
        void onQuestionDetailsFetched(QuestionDetails questionDetails);

        void onQuestionDetailsFetchFailed();
    }

    private final StackoverflowApi mStackoverflowApi;

    public FetchQuestionDetailsUseCase(StackoverflowApi mStackoverflowApi) {
        this.mStackoverflowApi = mStackoverflowApi;
    }

    public void fetchQuestionDetailAndNotify(String questionId) {
        mStackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            notifySuccess(response.body().getQuestion());
                        } else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        notifyFailure();
                    }
                });
    }

    private void notifySuccess(QuestionSchema schema) {
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailsFetched(
                    new QuestionDetails(schema.getId(), schema.getTitle(), schema.getBody())
            );


        }
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailsFetchFailed();
        }
    }
}
