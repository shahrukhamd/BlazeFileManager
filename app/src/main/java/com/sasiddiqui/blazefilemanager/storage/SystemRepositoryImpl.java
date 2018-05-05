package com.sasiddiqui.blazefilemanager.storage;

import android.util.Log;

import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

/**
 * Created by shahrukhamd on 05/05/18.
 */

public class SystemRepositoryImpl implements SystemRepository {

    @Override
    public List<FileDir> getDirectoryContent(String path) {
        List<FileDir> folderContentList = new ArrayList<>();

        Timber.d("%s: Directory: %s", TAG, path);

        File directory = new File(path);
        String[] content = directory.list();

        if (content != null) {
            for (String s : content) {
                if (!s.startsWith(path)) s = path + "/" + s;

                Timber.d("%s: Path: %s", TAG, s);
                File file = new File(s);

                if (!file.isHidden()) { //Not showing the hidden items
                    folderContentList.add(new FileDir(file));
                }
            }

            Collections.sort(folderContentList);
        }

        return folderContentList;
    }

    @Override
    public void deleteItem(String path) {
        deleteRecursive(new File(path));
    }

    private void deleteRecursive(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) deleteRecursive(child);
        }

        String path = file.getPath();
        boolean result = file.delete();

        if (result) Timber.d("%s: Deleted: %s", TAG, path);
        else Timber.d("%s: Error deleting: %s", TAG, path);
    }
}
