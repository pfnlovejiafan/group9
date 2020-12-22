package com.example.p7mvp.mvp.presenter;

import com.example.p7mvp.base.BasePresenter;
import com.example.p7mvp.mvp.model.Model;
import com.example.p7mvp.mvp.ui.fragment.BlankFragment;

public class FrgamentPresenter extends BasePresenter {
    private BlankFragment blankFragment;
    private Model model;

    public FrgamentPresenter(BlankFragment blankFragment) {
        this.blankFragment = blankFragment;
        model = new Model();

    }

}
