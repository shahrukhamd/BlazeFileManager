package com.sasiddiqui.blazefilemanager.presentation.presenter.base;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public abstract class AbstractPresenter {

    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor mExecutor, MainThread mMainThread) {
        this.mExecutor = mExecutor;
        this.mMainThread = mMainThread;
    }
}
