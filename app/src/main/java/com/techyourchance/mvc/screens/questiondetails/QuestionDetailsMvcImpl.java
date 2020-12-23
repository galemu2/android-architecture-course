package com.techyourchance.mvc.screens.questiondetails;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseViewMvc;

public class QuestionDetailsMvcImpl extends BaseViewMvc implements QuestionDetailsMvc {


    private TextView textTitle, textBody;
    private ProgressBar mProgressBar;
    private LinearLayout layoutContainer;
    private ImageView mImageView;

    public QuestionDetailsMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_details_activity, parent, false));
        textTitle = findViewById(R.id.text_title);
        textBody = findViewById(R.id.text_body);

        mProgressBar = findViewById(R.id.progress);
        layoutContainer = findViewById(R.id.details_container);
        mImageView = findViewById(R.id.image_error);
    }

    private void setDetailsTitle(String title) {
        textTitle.setText(title);
    }


    private void setDetailsBody(String body) {
        textBody.setText(body);
    }

    @Override
    public void fetchStarting() {
        hideDetails();
        hideError();
        showProgressbar();

    }

    @Override
    public void fetchingSuccess(QuestionDetails questionDetails) {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hideProgressbar();
        hideError();
        showDetails();

        setDetailsTitle(questionDetails.getTitle());
        setDetailsBody(questionDetails.getBody());
    }

    @Override
    public void fetchingFail() {
        hideProgressbar();
        hideDetails();
        showError();
    }

    public void showProgressbar() {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressbar() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public void showDetails() {
        if (layoutContainer.getVisibility() == View.GONE) {
            layoutContainer.setVisibility(View.VISIBLE);
        }
    }

    public void hideDetails() {
        if (layoutContainer.getVisibility() == View.VISIBLE) {
            layoutContainer.setVisibility(View.GONE);
        }

    }

    public void showError() {
        if (mImageView.getVisibility() == View.GONE) {
            mImageView.setVisibility(View.VISIBLE);
        }

    }

    public void hideError() {
        if (mImageView.getVisibility() == View.VISIBLE) {
            mImageView.setVisibility(View.GONE);
        }
    }
}