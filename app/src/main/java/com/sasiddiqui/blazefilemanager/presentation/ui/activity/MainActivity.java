package com.sasiddiqui.blazefilemanager.presentation.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sasiddiqui.blazefilemanager.R;
import com.sasiddiqui.blazefilemanager.action.PermissionAction;
import com.sasiddiqui.blazefilemanager.action.PermissionActionHelper;
import com.sasiddiqui.blazefilemanager.action.implementation.PermissionActionImpl;
import com.sasiddiqui.blazefilemanager.domain.executor.implementation.ExecutorImpl;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;
import com.sasiddiqui.blazefilemanager.presentation.presenter.MainPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.PermissionPresenter;
import com.sasiddiqui.blazefilemanager.presentation.presenter.implementation.MainPresenterImpl;
import com.sasiddiqui.blazefilemanager.presentation.presenter.implementation.PermissionPresenterImpl;
import com.sasiddiqui.blazefilemanager.storage.SystemRepositoryImpl;
import com.sasiddiqui.blazefilemanager.threading.MainThreadImpl;

import java.util.List;
import java.util.Stack;

/**
 * Created by shahrukhamd on 04/05/18.
 */

public class MainActivity extends AppCompatActivity implements
    PermissionPresenter.View,
    MainPresenter.View {

    private PermissionPresenter mPermissionPresenter;
    private MainPresenter mainPresenter;
    private Stack<String> foldersStack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foldersStack = new Stack<>();
        foldersStack.push(Environment.getExternalStorageDirectory().toString());

        PermissionAction permissionAction = new PermissionActionImpl(this);

        mPermissionPresenter = new PermissionPresenterImpl(permissionAction, this);
        mainPresenter = new MainPresenterImpl(
                ExecutorImpl.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new SystemRepositoryImpl()
        );

        mPermissionPresenter.checkAndRequestPermission(PermissionActionHelper.PERM_HELPER_READ_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionPresenter.checkPermission(requestCode, grantResults);
    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onHideProgress() {

    }

    @Override
    public void permissionRequestAccepted(int requestCode) {
        switch (requestCode) {
            case PermissionActionHelper.ACTION_GET_READ_STORAGE_PERMISSION:
                Toast.makeText(this, "Read storage perm granted", Toast.LENGTH_SHORT).show();
                mainPresenter.getDirectoryContent(foldersStack.peek());
                break;

            case PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION:
                break;
        }
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void permissionRequestDenied(int requestCode) {
        switch (requestCode) {
            case PermissionActionHelper.ACTION_GET_READ_STORAGE_PERMISSION:
                Toast.makeText(this, "Read storage perm denied", Toast.LENGTH_SHORT).show();
                break;

            case PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION:
                break;
        }
    }

    @Override
    public void showRationale(int requestCode) {
        switch (requestCode) {
            case PermissionActionHelper.ACTION_GET_READ_STORAGE_PERMISSION:
                Toast.makeText(this, "Read storage perm rationale", Toast.LENGTH_SHORT).show();
                break;

            case PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION:
                break;
        }
    }

    @Override
    public void onDirectoryContentRetrieved(List<FileDir> fileDirList) {

    }
}
