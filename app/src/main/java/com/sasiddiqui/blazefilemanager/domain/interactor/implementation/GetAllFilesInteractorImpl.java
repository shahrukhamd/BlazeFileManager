package com.sasiddiqui.blazefilemanager.domain.interactor.implementation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;
import com.sasiddiqui.blazefilemanager.domain.interactor.GetAllFilesInteractor;
import com.sasiddiqui.blazefilemanager.domain.interactor.base.AbstractInteractor;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;

import java.util.List;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class GetAllFilesInteractorImpl extends AbstractInteractor implements GetAllFilesInteractor {

    private SystemRepository mSystemRepository;
    private GetAllFilesInteractor.Callback mCallback;
    private String path;

    public GetAllFilesInteractorImpl(@NonNull Executor mExecutor,
                                     @NonNull MainThread mMainThread,
                                     @NonNull GetAllFilesInteractor.Callback mCallback,
                                     @NonNull SystemRepository mSystemRepository,
                                     @Nullable String path) {

        super(mExecutor, mMainThread);

        this.mSystemRepository = mSystemRepository;
        this.mCallback = mCallback;
        this.path = path;
    }

    @Override
    public void run() {
        final List<FileDir> contentList = mSystemRepository.getDirectoryContent(path);

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onContentRetrieved(contentList);
            }
        });
    }
}
