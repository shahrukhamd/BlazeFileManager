package com.sasiddiqui.blazefilemanager.domain.interactor.base;

/**
 * Created by shahrukhamd on 29/04/18.
 *
 * Basic interface for all interactors to implement.
 */

public interface Interactor {

    /**
     * This method will make sure that all the interactors run on background thread.
     */
    void execute();
}
