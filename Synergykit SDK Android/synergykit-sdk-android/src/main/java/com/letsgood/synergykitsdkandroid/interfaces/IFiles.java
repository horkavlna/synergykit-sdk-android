package com.letsgood.synergykitsdkandroid.interfaces;





/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import android.graphics.Bitmap;

import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.BytesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FilesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;

public interface IFiles {

    public void getFile(SynergykitConfig config, FileResponseListener listener);
    public void getFile(String fileId, FileResponseListener listener, boolean parallelMode);
    public void getFiles(SynergykitConfig config, FilesResponseListener listener);
    public void getFiles(FilesResponseListener listener, boolean parallelMode);
    public void updateFile(SynergykitFile file, FileResponseListener listener, boolean parallelMode);
    public void patchFile(SynergykitFile file, FileResponseListener listener, boolean parallelMode);
    public void deleteFile(String fileId, DeleteResponseListener listener, boolean parallelMode);
    public void createFile(byte[] data, FileResponseListener listener);
    public void createFile(Bitmap bitmap, FileResponseListener listener);
    public void downloadBitmap(String path, BitmapResponseListener listener);
    public void downloadFile(String path, BytesResponseListener listener);
    public void addAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode);
    public void removeAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode);

}
