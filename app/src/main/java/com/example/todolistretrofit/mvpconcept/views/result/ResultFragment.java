package com.example.todolistretrofit.mvpconcept.views.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todolistretrofit.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ResultFragment extends Fragment implements ResultView {
    ResultPresenter presenter;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
        onAttachView();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unchecked, container, false);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
        setResult();
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void onShowResult(final String text) {
        final TextView textView = (TextView) getActivity().findViewById(R.id.tv_status);
        textView.setText(text);
    }

    @Override
    public void onDestroyView() {
        onDetachView();
        super.onDestroyView();
    }

    private void initPresenter() {
        presenter = new ResultPresenter();
    }

    private void setResult() {
        final Bundle bundle = getArguments();
        final String text = bundle.getString("data");

        presenter.showResult(text);
    }
}