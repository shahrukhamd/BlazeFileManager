package com.sasiddiqui.blazefilemanager.presentation.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
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
import com.sasiddiqui.blazefilemanager.presentation.ui.adapter.FileFolderListRVAdapter;
import com.sasiddiqui.blazefilemanager.storage.SystemRepositoryImpl;
import com.sasiddiqui.blazefilemanager.threading.MainThreadImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shahrukhamd on 04/05/18.
 */

public class MainActivity extends AppCompatActivity implements
    PermissionPresenter.View,
    MainPresenter.View {

    private PermissionPresenter permissionPresenter;
    private MainPresenter mainPresenter;
    private FileFolderListRVAdapter adapter;

    @BindView(R.id.main_content_recycler_view) RecyclerView contentRecyclerView;
    @BindView(R.id.main_help_text) TextView emptyScreenTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        PermissionAction permissionAction = new PermissionActionImpl(this);

        permissionPresenter = new PermissionPresenterImpl(permissionAction, this);
        mainPresenter = new MainPresenterImpl(
                ExecutorImpl.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new SystemRepositoryImpl()
        );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new FileFolderListRVAdapter(null, mainPresenter);

        contentRecyclerView.setLayoutManager(layoutManager);
        contentRecyclerView.setAdapter(adapter);

        permissionPresenter.checkAndRequestPermission(PermissionActionHelper.PERM_HELPER_READ_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionPresenter.checkPermission(requestCode, grantResults);
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

                String homeDirectory = Environment.getExternalStorageDirectory().toString();
                mainPresenter.getDirectoryContent(homeDirectory);
                break;

            case PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION:
                break;
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void permissionRequestDenied(int requestCode) {
        switch (requestCode) {

            case PermissionActionHelper.ACTION_GET_READ_STORAGE_PERMISSION:
                showRationaleDialog(R.string.message_rationale_read_storage);
                onContentRetrievalFailedOrEmpty();
                break;

            case PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION:
                showRationaleDialog(R.string.message_rationale_write_storage);
                break;
        }
    }

    @Override
    public void showRationale(int requestCode) {
        switch (requestCode) {

            case PermissionActionHelper.ACTION_GET_READ_STORAGE_PERMISSION:
                showRationaleDialog(R.string.message_rationale_read_storage);
                break;

            case PermissionActionHelper.ACTION_GET_WRITE_STORAGE_PERMISSION:
                showRationaleDialog(R.string.message_rationale_write_storage);
                break;
        }
    }

    @Override
    public void onDirectoryContentRetrieved(List<FileDir> fileDirList) {
        emptyScreenTextView.setVisibility(View.GONE);
        contentRecyclerView.setVisibility(View.VISIBLE);

        adapter.updateList(fileDirList);
    }

    @Override
    public void onContentRetrievalFailedOrEmpty() {
        emptyScreenTextView.setVisibility(View.VISIBLE);
        contentRecyclerView.setVisibility(View.GONE);
    }

    private void showRationaleDialog(int messageId) {
        new AlertDialog.Builder(this)
                .setMessage(messageId)
                .setPositiveButton(R.string.text_ok, null)
                .show();
    }

    @Override
    public void onBackPressed() {
        if (!mainPresenter.goToPreviousDirectory()) super.onBackPressed();
    }
}
