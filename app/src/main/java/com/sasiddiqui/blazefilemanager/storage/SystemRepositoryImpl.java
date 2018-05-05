package com.sasiddiqui.blazefilemanager.storage;

import android.os.Environment;
import android.util.Log;

import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.domain.repository.SystemRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shahrukhamd on 05/05/18.
 */

public class SystemRepositoryImpl implements SystemRepository {

    @Override
    public List<FileDir> getDirectoryContent(String path) {
        List<FileDir> folderContentList = new ArrayList<>();

        File directory = new File(path);
        String[] content = directory.list();

        if (content != null) {
            for (String s : content) {
                if (!s.startsWith(path)) s = path + "/" + s;

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

        boolean result = file.delete();
    }
}
