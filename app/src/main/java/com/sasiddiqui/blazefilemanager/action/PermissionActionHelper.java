package com.sasiddiqui.blazefilemanager.action;

import android.Manifest;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by shahrukhamd on 04/05/18.
 *
 * A helper class which will be used to share a particular permission action that might be required
 * by different parts of the application.
 */

public class PermissionActionHelper {

    @IntDef({
            ACTION_GET_READ_STORAGE_PERMISSION,
            ACTION_GET_WRITE_STORAGE_PERMISSION
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionActionCode {}

    public static final int ACTION_GET_READ_STORAGE_PERMISSION = 0;
    public static final int ACTION_GET_WRITE_STORAGE_PERMISSION = 1;

    public static final PermissionActionHelper PERM_HELPER_READ_STORAGE =
            new PermissionActionHelper(ACTION_GET_READ_STORAGE_PERMISSION, Manifest.permission.READ_EXTERNAL_STORAGE);
    public static final PermissionActionHelper PERM_HELPER_WRITE_STORAGE =
            new PermissionActionHelper(ACTION_GET_WRITE_STORAGE_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    private int mCode;
    private String mPermission;

    private PermissionActionHelper(@PermissionActionCode int mCode, String mPermission) {
        this.mCode = mCode;
        this.mPermission = mPermission;
    }

    @PermissionActionCode
    public int getCode() {
        return mCode;
    }

    public String getPermission() {
        return mPermission;
    }
}