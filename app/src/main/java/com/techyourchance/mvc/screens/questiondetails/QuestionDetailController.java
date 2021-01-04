package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.MessagesDisplayer;

public class QuestionDetailController implements FetchQuestionDetailsUseCase.Listener {

    private final FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;
    private final QuestionIdentifyer questionIdentifyer;
    private QuestionDetailsMvc mQuestionDetailsMvc;
    private final MessagesDisplayer mMessagesDisplayer;

    public QuestionDetailController(
            FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase,
            MessagesDisplayer mMessagesDisplayer,
            QuestionIdentifyer questionIdentifyer) {
        this.fetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
        this.mMessagesDisplayer = mMessagesDisplayer;
        this.questionIdentifyer = questionIdentifyer;
    }

    public void bind(QuestionDetailsMvc mQuestionDetailsMvc) {
        this.mQuestionDetailsMvc = mQuestionDetailsMvc;
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

        mMessagesDisplayer.showUseCaseError();

    }

    public void onStart() {
        fetchQuestionDetailsUseCase.registerListener(this);
        mQuestionDetailsMvc.fetchStarting();
        fetchQuestionDetailsUseCase.fetchQuestionDetailAndNotify(questionIdentifyer.getQuestionId());
    }

    public void onStop() {
        fetchQuestionDetailsUseCase.unregisterListener(this);
    }
}
