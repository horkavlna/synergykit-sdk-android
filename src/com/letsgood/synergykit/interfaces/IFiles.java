package com.letsgood.synergykit.interfaces;

import android.graphics.Bitmap;

import com.letsgood.synergykit.listeners.FileResponseListener;

public interface IFiles {
	public void uploadFile(byte[] data, FileResponseListener listener);
	public void uploadBitmap(Bitmap bitmap,FileResponseListener listener);
	public void downloadBitmap();
	
}
