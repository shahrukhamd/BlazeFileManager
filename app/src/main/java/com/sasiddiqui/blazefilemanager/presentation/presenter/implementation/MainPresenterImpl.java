package com.sasiddiqui.blazefilemanager.presentation.presenter.implementation;

import android.support.annotation.NonNull;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;
import com.sasiddiqui.blazefilemanager.domain.interactor.GetAllFilesInteractor;
import com.sasiddiqui.blazefilemanager.domain.interactor.implementation.GetAllFilesInteractorImpl;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;
import com.sasiddiqui.blazefilemanager.presentation.presenter.MainPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.base.AbstractPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.model.MainPresenterData;

import java.util.List;
import java.util.Stack;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class MainPresenterImpl extends AbstractPresenter implements
        MainPresenter,
        GetAllFilesInteractor.Callback {

    private MainPresenter.View callback;
    private SystemRepository systemRepository;

    private Stack<String> directoryStack;
    private String currentPath;

    public MainPresenterImpl(Executor mExecutor, MainThread mMainThread, MainPresenter.View callback,
                             SystemRepository systemRepository, MainPresenterData savedData, String homeDirectory) {
        super(mExecutor, mMainThread);

        this.callback = callback;
        this.systemRepository = systemRepository;

        if (savedData == null) {
            directoryStack = new Stack<>();
            currentPath = homeDirectory;
        } else {
            directoryStack = savedData.getDirectoryStack();
            currentPath = savedData.getCurrentPath();
        }
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
    public void getDirectoryContent() {
        GetAllFilesInteractor filesInteractor = new GetAllFilesInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                systemRepository,
                currentPath
        );

        filesInteractor.execute();
    }

    @Override
    public void onClickContentListItem(@NonNull FileDir fileDir) {
        if (fileDir.getType() == FileDir.TYPE_FOLDER) {
            String filePath = fileDir.getPath();
            directoryStack.push(currentPath);
            currentPath = filePath;

            getDirectoryContent();

        } else {
            callback.openFile(fileDir);
        }
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
            currentPath = directoryStack.pop();
            getDirectoryContent();
            return true;
        }
    }

    @Override
    public void onRetrievalError(String errorMessage) {

    }

    @Override
    public MainPresenterData getStateData() {
        return new MainPresenterData()
                .setDirectoryStack(directoryStack)
                .setCurrentPath(currentPath);
    }
}
