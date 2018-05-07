package com.sasiddiqui.blazefilemanager.presentation.presenter;

import android.support.annotation.NonNull;

import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.presentation.presenter.base.BasePresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.model.MainPresenterData;
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

        /**
         * This method will be called when there was some error retrieving the content or it's empty.
         */
        void onContentRetrievalFailedOrEmpty();

        /**
         * This method should be used to open a file with appropriate activity.
         *
         * @param fileDir The file to open.
         */
        void openFile(FileDir fileDir);
    }

    /**
     * This method get the content of the directory respective of the given path through the {@link View}.
     *
     * @param path The path to the directory.
     */
    void getDirectoryContent(String path);

    /**
     * This method will be called when an item has been clicked in a content list.
     *
     * @param fileDir The instance of {@link FileDir} that has been clicked.
     */
    void onClickContentListItem(@NonNull FileDir fileDir);

    /**
     * This method will take you back to the previous directory (if available).
     *
     * @return If or not a parent directory is available.
     */
    boolean goToPreviousDirectory();

    MainPresenterData getStateData();
}