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
import com.sasiddiqui.blazefilemanager.presentation.presenter.MainPresenter;
import com.sasiddiqui.blazefilemanager.presentation.ui.listener.ContentListItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shahrukhamd on 06/05/18.
 */

public class FileFolderListRVAdapter extends RecyclerView.Adapter<FileFolderListRVAdapter.ViewHolder> implements
        ContentListItemClickListener {

    private List<FileDir> fileDirList;
    private MainPresenter presenter;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.content_list_item_image) ImageView contentItemImage;
        @BindView(R.id.content_list_item_name) TextView contentItemText;

        private ContentListItemClickListener listener;

        public ViewHolder(View itemView, ContentListItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        void bindData(FileDir fileDir) {
            contentItemText.setText(fileDir.getName());
            contentItemImage.setImageResource(
                    fileDir.getType() == FileDir.TYPE_FILE ?
                            R.drawable.ic_file_magenta_48px : R.drawable.ic_folder_purple_48px
            );
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                listener.onContentItemClicked(position);
            }
        }
    }

    public FileFolderListRVAdapter(@Nullable List<FileDir> fileDirList, MainPresenter presenter) {
        this.fileDirList = fileDirList;
        this.presenter = presenter;
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

        return new ViewHolder(itemView, this);
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

    @Override
    public void onContentItemClicked(int position) {
        presenter.onClickContentListItem(fileDirList.get(position));
    }
}
