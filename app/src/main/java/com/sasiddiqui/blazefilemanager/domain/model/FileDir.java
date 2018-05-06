package com.sasiddiqui.blazefilemanager.domain.model;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by shahrukhamd on 01/05/18.
 */

public class FileDir implements Comparable<FileDir> {

    @IntDef({TYPE_FILE, TYPE_FOLDER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeDef {}

    public static final int TYPE_FILE = 0;
    public static final int TYPE_FOLDER = 1;

    private int mType;
    private String mName;
    private String mPath;

    public FileDir(@TypeDef int mType, @NonNull String mName, @NonNull String mPath) {
        this.mType = mType;
        this.mName = mName;
        this.mPath = mPath;
    }

    public FileDir(File file) {
        mName = file.getName();
        mType = file.isDirectory() ? TYPE_FOLDER : TYPE_FILE;
        mPath = file.getPath();
    }

    public int getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    public String getPath() {
        return mPath;
    }

    @Override
    public int compareTo(@NonNull FileDir o) {
        // This will compare the same item type only. If they are different, the folder will take precedence in list.
        if (mType == o.getType()) {
            return mName.compareTo(o.getName());
        } else {
            return TYPE_FILE;
        }
    }
}