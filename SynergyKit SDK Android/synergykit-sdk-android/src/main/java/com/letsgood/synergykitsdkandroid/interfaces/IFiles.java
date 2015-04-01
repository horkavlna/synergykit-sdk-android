package com.letsgood.synergykitsdkandroid.interfaces;





/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import android.graphics.Bitmap;

import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.BytesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileDataResponseListener;

public interface IFiles {
	public void uploadFile(byte[] data, FileDataResponseListener listener);
	public void uploadBitmap(Bitmap bitmap, FileDataResponseListener listener);
	public void downloadBitmap(String uri, BitmapResponseListener listener);
	public void downloadFile(String uri, BytesResponseListener listener);
	
}
