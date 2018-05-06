package com.sasiddiqui.blazefilemanager.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.sasiddiqui.blazefilemanager.R;
import com.sasiddiqui.blazefilemanager.domain.model.FileDir;

import java.io.File;

/**
 * Created by shahrukhamd on 06/05/18.
 */

public class FileUtils {

    public static void openFile(Activity activity, FileDir fileDir) {
        File file = new File(fileDir.getPath());

        try {
            MimeTypeMap myMime = MimeTypeMap.getSingleton();
            Intent newIntent = new Intent(Intent.ACTION_VIEW);
            String mimeType = myMime.getMimeTypeFromExtension(getFileExt(file.toString()).substring(1));
            Uri url = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);
            newIntent.setDataAndType(url,mimeType);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.startActivity(newIntent);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, R.string.message_file_handler_not_found, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(activity, R.string.message_some_error, Toast.LENGTH_LONG).show();
        }
    }

    public static String getFileExt(String url) {
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.contains("%")) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.contains("/")) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();
        }
    }
}
