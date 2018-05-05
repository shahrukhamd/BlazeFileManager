package com.sasiddiqui.blazefilemanager.domain.executor;

import com.sasiddiqui.blazefilemanager.domain.interactor.base.AbstractInteractor;

/**
 * Created by shahrukhamd on 29/04/18.
 *
 * This interface will be responsible for executing different threads in background.
 */

public interface Executor {

    /**
     * This method will call the run() method of the interactors and will execute them in a separate
     * background thread.
     */
    void execute(final AbstractInteractor interactor);
}
