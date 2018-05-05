package com.sasiddiqui.blazefilemanager.domain.interactor.base;

import com.sasiddiqui.blazefilemanager.domain.executor.Executor;

/**
 * Created by shahrukhamd on 29/04/18.
 *
 * Basic interface for all interactors to implement.
 */

public interface Interactor {

    /**
     * This method will make sure that all the interactors run on background thread. It will call the
     * {@link Executor#execute()} which will take it as an argument and post it to be executed on a
     * seperate thread.
     */
    void execute();
}
