package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.screens.common.ViewMvc;

public interface QuestionDetailsMvc extends ViewMvc {

    void setDetailsTitle(String title);

    void setDetailsBody(String body);

    void fetchingInProgress();

    void fetchingDone();


    void showDetails();

    void hideDetails();

    void showError();

    void hideErrror();
}
