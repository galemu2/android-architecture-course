package com.techyourchance.mvc.screens.questionslist;

import android.os.Bundle;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.common.Constants;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.QuestionsListResponseSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchLastActiveQuestionUseCase;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.BaseActivity;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsListActivity extends BaseActivity implements QuestionsListViewMvcImpl.Listener, FetchLastActiveQuestionUseCase.Listener {



    private QuestionsListViewMvc mViewMvc;
    private FetchLastActiveQuestionUseCase mFetchLastActiveQuestionUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        mViewMvc.registerListener(this);

        mFetchLastActiveQuestionUseCase = getCompositionRoot().getFetchLastActiveQuestionUseCase();


        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFetchLastActiveQuestionUseCase.registerListener(this);
        mFetchLastActiveQuestionUseCase.fetchLastActiveQuestionsAndNotify();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mFetchLastActiveQuestionUseCase.unregisterListener(this);
    }




    private void bindQuestions(List<QuestionSchema> questionSchemas) {
        List<Question> questions = new ArrayList<>(questionSchemas.size());
        for (QuestionSchema questionSchema : questionSchemas) {
            questions.add(new Question(questionSchema.getId(), questionSchema.getTitle()));
        }
        mViewMvc.bindQuestions(questions);
    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(this, question.getId());
    }

    @Override
    public void onFetchLastActiveQuestionsFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFetchLastActiveQuestionSuccess(List<QuestionSchema> questions) {
        bindQuestions(questions);
    }
}
