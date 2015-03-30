package com.letsgood.synergykitandroidsdk.listeners;

import android.graphics.Bitmap;

import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface BitmapResponseListener {
	public void doneCallback(int statusCode, Bitmap bitmap);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
