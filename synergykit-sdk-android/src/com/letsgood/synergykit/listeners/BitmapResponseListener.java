package com.letsgood.synergykit.listeners;

import android.graphics.Bitmap;

import com.letsgood.synergykit.resources.SynergyKITError;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface BitmapResponseListener {
	public void doneCallback(int statusCode,Bitmap bitmap);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
