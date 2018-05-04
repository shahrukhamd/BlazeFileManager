package com.sasiddiqui.blazefilemanager.action.implementation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.sasiddiqui.blazefilemanager.action.PermissionAction;
import com.sasiddiqui.blazefilemanager.action.PermissionActionHelper;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * Created by shahrukhamd on 04/05/18.
 */

public class PermissionActionImpl implements PermissionAction {

    private Activity mActivity;

    public PermissionActionImpl(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public boolean hasSelfPermission(PermissionActionHelper permissionAction) {
        return ContextCompat.checkSelfPermission(mActivity, permissionAction.getPermission()) == PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(PermissionActionHelper permissionAction) {
        ActivityCompat.requestPermissions(
                mActivity,
                new String[]{permissionAction.getPermission()},
                permissionAction.getCode()
        );
    }

    @Override
    public boolean shouldShowPermissionRationale(PermissionActionHelper permissionAction) {
        return ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permissionAction.getPermission());
    }

    @Override
    public boolean checkIfPermissionGranted(int result) {
        return result == PERMISSION_GRANTED;
    }
}
