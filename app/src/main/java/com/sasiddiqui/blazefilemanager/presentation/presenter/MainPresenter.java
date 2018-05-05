package com.sasiddiqui.blazefilemanager.presentation.presenter;

import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.presentation.presenter.base.BasePresenter;
import com.sasiddiqui.blazefilemanager.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        /**
         * This method will retrieve all the content of a directory.
         */
        void onDirectoryContentRetrieved(List<FileDir> fileDirList);
    }

    /**
     * This method get the content of the directory respective of the given path through the {@link View}.
     *
     * @param path The path to the directory.
     */
    void getDirectoryContent(String path);
}