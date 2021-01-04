package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.FetchLastActiveQuestionUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.MessagesDisplayer;
import com.techyourchance.mvc.screens.common.ScreensNavigator;

import java.util.List;

public class QuestionsListController
        implements QuestionsListViewMvcImpl.Listener, FetchLastActiveQuestionUseCase.Listener {

    private final FetchLastActiveQuestionUseCase mFetchLastActiveQuestionUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final MessagesDisplayer mMessagesDisplayer;

    private QuestionsListViewMvc mViewMvc;

    public QuestionsListController(FetchLastActiveQuestionUseCase mFetchLastActiveQuestionUseCase, ScreensNavigator mScreensNavigator, MessagesDisplayer mMessagesDisplayer) {
        this.mFetchLastActiveQuestionUseCase = mFetchLastActiveQuestionUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mMessagesDisplayer = mMessagesDisplayer;
    }

    public void bindView(QuestionsListViewMvc viewMvc) {
        this.mViewMvc = viewMvc;
        this.mViewMvc.registerListener(this);
    }

    public void onStart() {
        mFetchLastActiveQuestionUseCase.registerListener(this);
        mFetchLastActiveQuestionUseCase.fetchLastActiveQuestionsAndNotify();
    }

    public void onStop() {
        mFetchLastActiveQuestionUseCase.unregisterListener(this);

    }

    @Override
    public void onQuestionClicked(Question question) {
        mScreensNavigator.toDialogDetails(question.getId());
    }

    @Override
    public void onFetchLastActiveQuestionsFailed() {
        mMessagesDisplayer.showUseCaseError();
    }

    @Override
    public void onFetchLastActiveQuestionSuccess(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
    }
}
