package com.sasiddiqui.blazefilemanager.action;

import android.Manifest;

/**
 * Created by shahrukhamd on 02/05/18.
 */

public interface PermissionAction {

    /**
     * This method will check if the app has the given permission or not.
     *
     * @param permissionAction An instance of {@link PermissionActionHelper} which has the
     *                         required permission to check.
     */
    boolean hasSelfPermission(PermissionActionHelper permissionAction);

    /**
     * This method will request user for a particular permission.
     *
     * @param permissionAction An instance of {@link PermissionActionHelper} which has the
     *                         required permission to check.
     */
    void requestPermission(PermissionActionHelper permissionAction);

    /**
     * This method will chech if the app has to show a helper rationale regarding a particular permission.
     *
     * @param permissionAction An instance of {@link PermissionActionHelper} which has the
     *                         required permission to check.
     */
    boolean shouldShowPermissionRationale(PermissionActionHelper permissionAction);

    /**
     * This method will check if the requested permission from the user has been granted or not.
     *
     * @param result The response result from the permission dialog prompt.
     */
    boolean checkIfPermissionGranted(int result);
}