package com.sasiddiqui.blazefilemanager.presentation.presenter.model;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by shahrukhamd on 07/05/18.
 *
 * This is a model class used solely for the {@link com.sasiddiqui.blazefilemanager.presentation.presenter.MainPresenter}
 * data that needs to be saved.
 */

public class MainPresenterData implements Serializable {

    public static final String TAG = MainPresenterData.class.getSimpleName();

    private Stack<String> directoryStack;
    private String currentPath;

    public Stack<String> getDirectoryStack() {
        return directoryStack;
    }

    public MainPresenterData setDirectoryStack(Stack<String> directoryStack) {
        this.directoryStack = directoryStack;
        return this;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public MainPresenterData setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
        return this;
    }
}
