package com.sasiddiqui.blazefilemanager.domain.interactor;

import com.sasiddiqui.blazefilemanager.domain.interactor.base.Interactor;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;

import java.util.List;

/**
 * Created by shahrukhamd on 01/05/18.
 *
 * This interactor will retrieve all the files and folders in a particular directory.
 */

public interface GetAllFilesInteractor extends Interactor {

    interface Callback {
        /**
         * This callback is to notify the listener with a list of files and folders list.
         */
        void onFilesDirRetrieved(List<FileDir> fileDirList);

        /**
         * This callback is used to notify the listener about an error occurred while retrieving.
         *
         * @param errorMessage The message related to that particular error.
         */
        void onRetrievalError(String errorMessage);
    }
}
