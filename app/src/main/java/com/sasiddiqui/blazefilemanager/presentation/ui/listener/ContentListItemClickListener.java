package com.sasiddiqui.blazefilemanager.presentation.ui.listener;

import com.sasiddiqui.blazefilemanager.domain.model.FileDir;

/**
 * Created by shahrukhamd on 06/05/18.
 */

public interface ContentListItemClickListener {

    /**
     * This method will be called if the item in the content list is clicked.
     *
     * @param position The position of {@link FileDir}, in the list, that has been clicked.
     */
    void onContentItemClicked(int position);
}
