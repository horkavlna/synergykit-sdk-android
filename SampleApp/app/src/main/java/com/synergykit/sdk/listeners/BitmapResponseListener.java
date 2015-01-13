package com.synergykit.sdk.listeners;

import android.graphics.Bitmap;

import com.synergykit.sdk.resources.SynergyKITError;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface BitmapResponseListener {
	public void doneCallback(int statusCode,Bitmap bitmap);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
