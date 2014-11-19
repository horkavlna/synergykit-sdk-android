package com.letsgood.synergykit.listeners;

import android.graphics.Bitmap;

import com.letsgood.synergykit.resources.SynergyKITError;

public interface BitmapResponseListener {
	public void doneCallback(int statusCode,Bitmap bitmap);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
