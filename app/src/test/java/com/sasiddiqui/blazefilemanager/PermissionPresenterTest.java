package com.sasiddiqui.blazefilemanager;

import android.content.pm.PackageManager;

import com.sasiddiqui.blazefilemanager.action.PermissionAction;
import com.sasiddiqui.blazefilemanager.action.PermissionActionHelper;
import com.sasiddiqui.blazefilemanager.presentation.presenter.PermissionPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.implementation.PermissionPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by shahrukhamd on 12/05/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class PermissionPresenterTest {

    PermissionPresenter permissionPresenter;
    @Mock
    PermissionAction permissionAction;
    @Mock
    PermissionPresenter.View callback;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        permissionPresenter = new PermissionPresenterImpl(permissionAction, callback);
    }

    @Test
    public void shouldCallRequestAcceptedFromSelf() {
        when(permissionAction.hasSelfPermission(PermissionActionHelper.PERM_HELPER_READ_STORAGE)).thenReturn(true);
        when(permissionAction.hasSelfPermission(PermissionActionHelper.PERM_HELPER_WRITE_STORAGE)).thenReturn(true);

        permissionPresenter.checkAndRequestPermission(PermissionActionHelper.PERM_HELPER_READ_STORAGE);
        permissionPresenter.checkAndRequestPermission(PermissionActionHelper.PERM_HELPER_WRITE_STORAGE);

        verify(callback).permissionRequestAccepted(PermissionActionHelper.ACTION_GET_READ_STORAGE_PERMISSION);
        verify(callback).permissionRequestAccepted(PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION);
    }

    @Test
    public void shouldShowRequestAcceptedFromDialog() {
        when(permissionAction.checkIfPermissionGranted(PackageManager.PERMISSION_GRANTED)).thenReturn(true);

        permissionPresenter.checkPermission(0, new int[]{PackageManager.PERMISSION_GRANTED});

        verify(callback).permissionRequestAccepted(0);
    }

    @Test
    public void shouldShowRationale() {
        when(permissionAction.hasSelfPermission(PermissionActionHelper.PERM_HELPER_READ_STORAGE)).thenReturn(false);
        when(permissionAction.hasSelfPermission(PermissionActionHelper.PERM_HELPER_WRITE_STORAGE)).thenReturn(false);
        when(permissionAction.shouldShowPermissionRationale(PermissionActionHelper.PERM_HELPER_READ_STORAGE)).thenReturn(true);
        when(permissionAction.shouldShowPermissionRationale(PermissionActionHelper.PERM_HELPER_WRITE_STORAGE)).thenReturn(true);

        permissionPresenter.checkAndRequestPermission(PermissionActionHelper.PERM_HELPER_READ_STORAGE);
        permissionPresenter.checkAndRequestPermission(PermissionActionHelper.PERM_HELPER_WRITE_STORAGE);

        verify(callback).showRationale(PermissionActionHelper.ACTION_GET_READ_STORAGE_PERMISSION);
        verify(callback).showRationale(PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION);
    }
}
