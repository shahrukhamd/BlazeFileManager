package com.sasiddiqui.blazefilemanager.domain.interactor.implementation;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;
import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;
import com.sasiddiqui.blazefilemanager.domain.interactor.DeleteInteractor;
import com.sasiddiqui.blazefilemanager.domain.interactor.base.AbstractInteractor;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class DeleteInteractorImpl extends AbstractInteractor implements DeleteInteractor {

    private SystemRepository mRepository;
    private DeleteInteractor.Callback mCallback;

    public DeleteInteractorImpl(Executor mExecutor, MainThread mMainThread,
                                SystemRepository mRepository, DeleteInteractor.Callback mCallback) {
        super(mExecutor, mMainThread);

        this.mRepository = mRepository;
        this.mCallback = mCallback;
    }

    @Override
    public void run() {

    }
}
