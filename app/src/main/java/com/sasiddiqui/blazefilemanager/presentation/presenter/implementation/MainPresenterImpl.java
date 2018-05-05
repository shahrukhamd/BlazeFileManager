package com.sasiddiqui.blazefilemanager.presentation.presenter.implementation;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;
import com.sasiddiqui.blazefilemanager.domain.interactor.GetAllFilesInteractor;
import com.sasiddiqui.blazefilemanager.domain.interactor.implementation.GetAllFilesInteractorImpl;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;
import com.sasiddiqui.blazefilemanager.presentation.presenter.MainPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.base.AbstractPresentor;

import java.util.List;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class MainPresenterImpl extends AbstractPresentor implements
        MainPresenter,
        GetAllFilesInteractor.Callback {

    private MainPresenter.View callback;
    private SystemRepository systemRepository;

    public MainPresenterImpl(Executor mExecutor, MainThread mMainThread, MainPresenter.View callback,
                             SystemRepository systemRepository) {
        super(mExecutor, mMainThread);

        this.callback = callback;
        this.systemRepository = systemRepository;
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void stop() {}

    @Override
    public void destroy() {}

    @Override
    public void onError(String message) {}

    @Override
    public void getDirectoryContent(String path) {
        GetAllFilesInteractor filesInteractor = new GetAllFilesInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                systemRepository,
                path
        );

        filesInteractor.execute();
    }

    @Override
    public void onContentRetrieved(List<FileDir> fileDirList) {
        callback.onDirectoryContentRetrieved(fileDirList);
    }

    @Override
    public void onRetrievalError(String errorMessage) {

    }
}
