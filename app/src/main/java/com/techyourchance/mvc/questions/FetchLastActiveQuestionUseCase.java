package com.techyourchance.mvc.questions;

import com.techyourchance.mvc.common.Constants;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.QuestionsListResponseSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchLastActiveQuestionUseCase extends BaseObservableViewMvc<FetchLastActiveQuestionUseCase.Listener> {

    public interface Listener {
        void onFetchLastActiveQuestionsFailed();

        void onFetchLastActiveQuestionSuccess(List<QuestionSchema> questions);
    }

    private final StackoverflowApi stackoverflowApi;

    public FetchLastActiveQuestionUseCase(StackoverflowApi stackoverflowApi) {
        this.stackoverflowApi = stackoverflowApi;
    }

    public void fetchLastActiveQuestionsAndNotify() {
        stackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                        if(response.isSuccessful()){
                            notifySuccess(response.body().getQuestions());
                        }
                        else
                        {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {

                    }
                });

    }

    private void notifyFailure() {

        for(Listener listener:getListeners()){
            listener.onFetchLastActiveQuestionsFailed();
        }
    }

    private void notifySuccess(List<QuestionSchema> questions) {
        for(Listener listener:getListeners()){
            listener.onFetchLastActiveQuestionSuccess(questions);
        }
    }

}
