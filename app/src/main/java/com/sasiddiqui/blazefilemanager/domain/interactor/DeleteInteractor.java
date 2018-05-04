package com.sasiddiqui.blazefilemanager.domain.interactor;

import com.sasiddiqui.blazefilemanager.domain.interactor.base.Interactor;

/**
 * Created by shahrukhamd on 29/04/18.
 *
 * This interactor is used to delete the file or directory.
 */

public interface DeleteInteractor extends Interactor {

    interface Callback {
        /**
         * This callback interface is used to notify the listener about the deletion process completion.
         */
        void onFileDirDeleted();

        /**
         * This callback is used to notify the listener about an error occurred while deletion.
         *
         * @param errorMessage The message related to that particular error.
         */
        void onDeletionError(String errorMessage);
    }
}
