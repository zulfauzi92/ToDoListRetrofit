package com.example.todolistretrofit.mvpconcept.views.main;

import com.example.todolistretrofit.mvpconcept.internal.model.Data;
import com.example.todolistretrofit.mvpconcept.views.base.View;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface MainView extends View {
    void onAttachView();

    void onDetachView();

    void onShowFragment(Data data);
}