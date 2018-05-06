package com.sasiddiqui.blazefilemanager.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sasiddiqui.blazefilemanager.domain.model.FileDir;

import java.util.List;

/**
 * Created by shahrukhamd on 06/05/18.
 */

public class FileFolderListRVAdpter extends RecyclerView.Adapter<FileFolderListRVAdpter.ViewHolder> {

    private List<FileDir> fileDirList;

    public static class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return fileDirList.size();
    }
}
