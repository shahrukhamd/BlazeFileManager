package com.sasiddiqui.blazefilemanager.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sasiddiqui.blazefilemanager.R;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shahrukhamd on 06/05/18.
 */

public class FileFolderListRVAdapter extends RecyclerView.Adapter<FileFolderListRVAdapter.ViewHolder> {

    private List<FileDir> fileDirList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.content_list_item_image) ImageView contentItemImage;
        @BindView(R.id.content_list_item_name) TextView contentItemText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindData(FileDir fileDir) {
            contentItemText.setText(fileDir.getName());
            contentItemImage.setImageResource(
                    fileDir.getType() == FileDir.TYPE_FILE ?
                            R.drawable.ic_file_purple_48px : R.drawable.ic_folder_purple_48px
            );
        }

        @Override
        public void onClick(View v) {

        }
    }

    public FileFolderListRVAdapter(@Nullable List<FileDir> fileDirList) {
        this.fileDirList = fileDirList;
    }

    public void updateList(@NonNull List<FileDir> fileDirList) {
        if (this.fileDirList != null) {
            this.fileDirList.clear();
        }

        this.fileDirList = fileDirList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_content_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(fileDirList.get(position));
    }

    @Override
    public int getItemCount() {
        if (fileDirList != null) {
            return fileDirList.size();
        } else {
            return 0;
        }
    }
}
