package com.example.todolistretrofit.mvpconcept.views.result;


import com.example.todolistretrofit.mvpconcept.views.base.View;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface ResultView extends View {
    void onShowResult(String text);
}