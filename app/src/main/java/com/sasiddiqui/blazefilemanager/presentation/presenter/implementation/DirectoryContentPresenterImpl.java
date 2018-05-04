package com.sasiddiqui.blazefilemanager.presentation.presenter.implementation;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;
import com.sasiddiqui.blazefilemanager.presentation.presenter.DirectoryContentPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.base.AbstractPresentor;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class DirectoryContentPresenterImpl extends AbstractPresentor implements DirectoryContentPresenter {

    public DirectoryContentPresenterImpl(Executor mExecutor, MainThread mMainThread) {
        super(mExecutor, mMainThread);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void getDirectoryContent(String path) {

    }
}
