package com.sasiddiqui.blazefilemanager.domain.interactor.implementation;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;
import com.sasiddiqui.blazefilemanager.domain.interactor.GetAllFilesInteractor;
import com.sasiddiqui.blazefilemanager.domain.interactor.base.AbstractInteractor;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class GetAllFilesInteractorImpl extends AbstractInteractor implements GetAllFilesInteractor {

    private SystemRepository mSystemRepository;
    private GetAllFilesInteractor.Callback mCallback;

    public GetAllFilesInteractorImpl(Executor mExecutor, MainThread mMainThread,
                                     GetAllFilesInteractor.Callback mCallback, SystemRepository mSystemRepository) {
        super(mExecutor, mMainThread);

        this.mSystemRepository = mSystemRepository;
        this.mCallback = mCallback;
    }

    @Override
    public void run() {

    }
}
