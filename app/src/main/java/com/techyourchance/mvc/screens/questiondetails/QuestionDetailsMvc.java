package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ViewMvc;

public interface QuestionDetailsMvc extends ViewMvc {


    void fetchStarting();

    void fetchingSuccess(QuestionDetails questionDetails);

    void fetchingFail();
}
