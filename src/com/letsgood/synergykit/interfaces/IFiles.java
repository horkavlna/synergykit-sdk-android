package com.letsgood.synergykit.interfaces;

import android.graphics.Bitmap;

import com.letsgood.synergykit.listeners.BitmapResponseListener;
import com.letsgood.synergykit.listeners.BytesResponseListener;
import com.letsgood.synergykit.listeners.FileDataResponseListener;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IFiles {
	public void uploadFile(byte[] data, FileDataResponseListener listener);
	public void uploadBitmap(Bitmap bitmap,FileDataResponseListener listener);
	public void downloadBitmap(String uri, BitmapResponseListener listener);
	public void downloadFile(String uri,BytesResponseListener listener);
	
}
