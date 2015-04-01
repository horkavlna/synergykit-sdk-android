package com.letsgood.synergykitsdkandroid.listeners;





/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import android.graphics.Bitmap;

import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;

public interface BitmapResponseListener {
	public void doneCallback(int statusCode, Bitmap bitmap);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
