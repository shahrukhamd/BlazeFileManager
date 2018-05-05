package com.sasiddiqui.blazefilemanager.domain.repository;

import com.sasiddiqui.blazefilemanager.domain.model.FileDir;

import java.util.List;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public interface SystemRepository {

    String TAG = SystemRepository.class.getSimpleName();

    /**
     * This method will retrieve the content of a particular directory.
     *
     * @param path The system path of a directory.
     */
    List<FileDir> getDirectoryContent(String path);

    /**
     * This method will delete the file or a directory.
     *
     * @param path The system path of a file or directory.
     */
    void deleteItem(String path);
}
