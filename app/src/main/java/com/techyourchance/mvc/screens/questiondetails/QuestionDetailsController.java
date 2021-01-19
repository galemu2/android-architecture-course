package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.toastshelper.ToastHelper;

public class QuestionDetailsController implements FetchQuestionDetailsUseCase.Listener {

    private final FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;
    private final QuestionIdentifier questionIdentifier;
    private QuestionDetailsMvc mQuestionDetailsMvc;
    private final ToastHelper mToastHelper;

    public QuestionDetailsController(
            FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase,
            ToastHelper mToastHelper,
            QuestionIdentifier questionIdentifier) {
        this.fetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
        this.mToastHelper = mToastHelper;
        this.questionIdentifier = questionIdentifier;
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

        mToastHelper.showUseCaseError();

    }

    public void onStart() {
        fetchQuestionDetailsUseCase.registerListener(this);
        mQuestionDetailsMvc.fetchStarting();
        fetchQuestionDetailsUseCase.fetchQuestionDetailAndNotify(questionIdentifier.getQuestionId());
    }

    public void onStop() {
        fetchQuestionDetailsUseCase.unregisterListener(this);
    }
}
