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

    @Override
    public void setDetailsTitle(String title) {
        textTitle.setText(title);
    }


    @Override
    public void setDetailsBody(String body) {
        textBody.setText(body);
    }

    @Override
    public void fetchingInProgress() {
        if (mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void fetchingDone() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showDetails() {
        if (layoutContainer.getVisibility() == View.INVISIBLE) {
            layoutContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideDetails() {
        if (layoutContainer.getVisibility() == View.VISIBLE) {
            layoutContainer.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void showError() {
        if (mImageView.getVisibility() == View.INVISIBLE) {
            mImageView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void hideErrror() {
        if (mImageView.getVisibility() == View.VISIBLE) {
            mImageView.setVisibility(View.INVISIBLE);
        }
    }


}
