package com.sasiddiqui.blazefilemanager.presentation.presenter;

import com.sasiddiqui.blazefilemanager.action.PermissionActionHelper;
import com.sasiddiqui.blazefilemanager.presentation.ui.BaseView;

/**
 * Created by shahrukhamd on 01/05/18.
 *
 * The sole responsibility of this presenter will be to manage the permissions.
 */

public interface PermissionPresenter {

    interface View extends BaseView {

        /**
         * This method will be called if the permission request has been accepted by the user.
         *
         * @param  requestCode The request code used to ask the permission form the user.
         */
        void permissionRequestAccepted(int requestCode);

        /**
         * This method will be called if the permission request has been denied by the user.
         *
         * @param  requestCode The request code used to ask the permission form the user.
         */
        void permissionRequestDenied(int requestCode);

        /**
         * This method will be called if there's a need to show some rationale information to the
         * user before asking for a particular permission.
         *
         * @param  requestCode The request code used to ask the permission form the user.
         */
        void showRationale(int requestCode);
    }

    /**
     * This is a helper method that will be used to check a particular permission and request the
     * same if not found granted already.
     *
     * @param  permissionAction An instance of {@link PermissionActionHelper} which has the permission
     *                          you want to check.
     */
    void checkAndRequestPermission(PermissionActionHelper permissionAction);

    /**
     * This method will be used to check the result of permission dialog prompted to the user.
     *
     * @param requestCode The request code used to ask permission form the user.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
     */
    void checkPermission(int requestCode, int[] grantResults);
}