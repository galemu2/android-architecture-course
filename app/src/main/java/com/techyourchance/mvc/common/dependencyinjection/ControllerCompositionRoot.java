package com.techyourchance.mvc.common.dependencyinjection;

import android.app.Activity;
import android.view.LayoutInflater;

import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchLastActiveQuestionUseCase;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.techyourchance.mvc.screens.common.toastshelper.ToastHelper;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsController;
import com.techyourchance.mvc.screens.questiondetails.QuestionIdentifier;
import com.techyourchance.mvc.screens.questionslist.QuestionsListController;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final Activity mActivity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        mCompositionRoot = compositionRoot;
        mActivity = activity;
    }

    private StackoverflowApi getStackoverflowApi() {
        return mCompositionRoot.getStackoverflowApi();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(getStackoverflowApi());
    }

    public FetchLastActiveQuestionUseCase getFetchLastActiveQuestionUseCase() {
        return new FetchLastActiveQuestionUseCase(getStackoverflowApi());
    }

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(getFetchLastActiveQuestionUseCase(), getScreenNavigator(), getMessageDisplayer());
    }

    private ScreensNavigator getScreenNavigator() {
        return new ScreensNavigator(getContext());
    }

    public ToastHelper getMessageDisplayer() {
        return new ToastHelper(getContext());
    }

    private Activity getContext() {
        return mActivity;
    }

    private QuestionIdentifier getQuestionIdentifyer(){
        return new QuestionIdentifier(getContext());
    }
    public QuestionDetailsController getQuestionDetailController() {

        return new QuestionDetailsController(getFetchQuestionDetailsUseCase(), getMessageDisplayer(), getQuestionIdentifyer());
    }
}
