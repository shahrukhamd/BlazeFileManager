package com.sasiddiqui.blazefilemanager.presentation.presenter.implementation;

import com.sasiddiqui.blazefilemanager.action.PermissionAction;
import com.sasiddiqui.blazefilemanager.action.PermissionActionHelper;
import com.sasiddiqui.blazefilemanager.presentation.presenter.PermissionPresenter;

/**
 * Created by shahrukhamd on 04/05/18.
 */

public class PermissionPresenterImpl implements PermissionPresenter {

    PermissionAction mPermissionAction;
    PermissionPresenter.View mCallback;

    public PermissionPresenterImpl(PermissionAction mPermissionAction, PermissionPresenter.View mCallback) {
        this.mPermissionAction = mPermissionAction;
        this.mCallback = mCallback;
    }

    @Override
    public void checkAndRequestPermission(PermissionActionHelper permissionActionHelper) {
        if (mPermissionAction.hasSelfPermission(permissionActionHelper)) {
            mCallback.permissionRequestAccepted(permissionActionHelper.getCode());
        } else if (mPermissionAction.shouldShowPermissionRationale(permissionActionHelper)) {
            mCallback.showRationale(permissionActionHelper.getCode());
        } else {
            mPermissionAction.requestPermission(permissionActionHelper);
        }
    }

    @Override
    public void checkPermission(int requestCode, int[] grantResults) {
        if (mPermissionAction.checkIfPermissionGranted(grantResults[0])) {
            mCallback.permissionRequestAccepted(requestCode);
        } else {
            mCallback.permissionRequestDenied(requestCode);
        }
    }
}
