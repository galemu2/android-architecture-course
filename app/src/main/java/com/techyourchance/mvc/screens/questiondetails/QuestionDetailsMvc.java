package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.views.ViewMvc;

public interface QuestionDetailsMvc extends ViewMvc {


    void fetchStarting();

    void fetchingSuccess();

    void fetchingFail();

    void bindQuestionDetails(QuestionDetails questionDetails);
}
