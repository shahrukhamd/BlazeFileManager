package com.sasiddiqui.blazefilemanager.presentation.presenter.implementation;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;
import com.sasiddiqui.blazefilemanager.domain.interactor.GetAllFilesInteractor;
import com.sasiddiqui.blazefilemanager.domain.interactor.implementation.GetAllFilesInteractorImpl;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;
import com.sasiddiqui.blazefilemanager.presentation.presenter.MainPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.base.AbstractPresentor;

import java.util.List;
import java.util.Stack;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class MainPresenterImpl extends AbstractPresentor implements
        MainPresenter,
        GetAllFilesInteractor.Callback {

    private MainPresenter.View callback;
    private SystemRepository systemRepository;

    private Stack<String> directoryStack;
    private String currentPath;

    public MainPresenterImpl(Executor mExecutor, MainThread mMainThread, MainPresenter.View callback,
                             SystemRepository systemRepository) {
        super(mExecutor, mMainThread);

        this.callback = callback;
        this.systemRepository = systemRepository;

        directoryStack = new Stack<>();
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
        // Save the current directory in stack so that we can come back to it should a user wants to.
        if (currentPath != null) directoryStack.push(currentPath);
        currentPath = path;

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
    public void onClickContentListItem(@NonNull FileDir fileDir) {
        if (fileDir.getType() == FileDir.TYPE_FOLDER) getDirectoryContent(fileDir.getPath());
        else callback.openFile(fileDir);
    }

    @Override
    public void onContentRetrieved(List<FileDir> fileDirList) {
        if (fileDirList.size() > 0) callback.onDirectoryContentRetrieved(fileDirList);
        else callback.onContentRetrievalFailedOrEmpty();
    }

    @Override
    public boolean goToPreviousDirectory() {
        if (directoryStack.empty()) {
            return false;
        } else {
            currentPath = null; // Setting it null will prevent a loop
            getDirectoryContent(directoryStack.pop());
            return true;
        }
    }

    @Override
    public void onRetrievalError(String errorMessage) {

    }
}
