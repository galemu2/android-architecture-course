package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.FetchLastActiveQuestionUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.techyourchance.mvc.screens.common.toastshelper.ToastHelper;

import java.util.List;

public class QuestionsListController
        implements QuestionsListViewMvcImpl.Listener, FetchLastActiveQuestionUseCase.Listener {

    private final FetchLastActiveQuestionUseCase mFetchLastActiveQuestionUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final ToastHelper mToastHelper;

    private QuestionsListViewMvc mViewMvc;

    public QuestionsListController(
            FetchLastActiveQuestionUseCase mFetchLastActiveQuestionUseCase,
            ScreensNavigator mScreensNavigator,
            ToastHelper mToastHelper
    ) {
        this.mFetchLastActiveQuestionUseCase = mFetchLastActiveQuestionUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mToastHelper = mToastHelper;
    }

    public void bindView(QuestionsListViewMvc viewMvc) {
        this.mViewMvc = viewMvc;

    }

    public void onStart() {
        mFetchLastActiveQuestionUseCase.registerListener(this);
        mViewMvc.registerListener(this);
        mFetchLastActiveQuestionUseCase.fetchLastActiveQuestionsAndNotify();
    }

    public void onStop() {
        mViewMvc.unregisterListener(this);
        mFetchLastActiveQuestionUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        mScreensNavigator.toDialogDetails(question.getId());
    }

    @Override
    public void onFetchLastActiveQuestionsFailed() {
        mToastHelper.showUseCaseError();
    }

    @Override
    public void onFetchLastActiveQuestionSuccess(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
    }
}
