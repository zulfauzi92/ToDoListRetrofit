package com.example.todolistretrofit.mvpconcept.views.main;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.todolistretrofit.R;
import com.example.todolistretrofit.mvpconcept.internal.model.Data;
import com.example.todolistretrofit.mvpconcept.views.result.ResultFragment;


/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class CobaActivity extends AppCompatActivity implements MainView {
    MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        onAttachView();
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
        initToolbar();
        addButtonListener();
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void onShowFragment(final Data data) {
        // Get Data
        final Bundle bundle = new Bundle();
        bundle.putString("data", data.getText());

        // Show Fragment with Data
        final String tag = ResultFragment.class.getSimpleName();
        final Fragment fragment = ResultFragment.newInstance();
        fragment.setArguments(bundle);

        // Begin Fragment Transaction
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    private void initPresenter() {
        presenter = new MainPresenter();
    }

    private void initToolbar() {
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
//        toolbar.setTitle(getTitle());
//        setSupportActionBar(toolbar);
    }

    private void addButtonListener() {
        final Button button = (Button) findViewById(R.id.button_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                button.setVisibility(View.GONE);
                presenter.showFragment();
            }
        });
    }
}