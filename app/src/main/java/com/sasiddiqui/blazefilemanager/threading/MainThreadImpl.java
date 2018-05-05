package com.sasiddiqui.blazefilemanager.threading;

import android.os.Handler;
import android.os.Looper;

import com.sasiddiqui.blazefilemanager.domain.executor.MainThread;

/**
 * Created by shahrukhamd on 05/05/18.
 *
 * A singleton class which will be used by interactors to post updates on main thread.
 */

public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }
}
