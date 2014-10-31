package com.letsgood.synergykit.cache;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.letsgood.synergykit.SynergyKITSdk;
import com.letsgood.synergykit.interfaces.ICache;

import android.content.Context;
import android.util.Log;

public class Cache implements ICache{
	/* Constants */
	private final static String CACHE_DIR_NAME = "http";
	private final static int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;

	/* Init cache */
	@Override
	public void installCache(Context context) {
		File httpCacheDir = null;

		try {
			httpCacheDir = new File(context.getCacheDir(), CACHE_DIR_NAME); // init cache dir
			//HttpResponseCache.install(httpCacheDir, DEFAULT_CACHE_SIZE); //init cache
			Class.forName("android.net.http.HttpResponseCache")
				 .getMethod("install", File.class, long.class)      
				 .invoke(null, httpCacheDir, DEFAULT_CACHE_SIZE);
			
		} catch (Exception e) {
			Log.i(SynergyKITSdk.TAG, "HTTP response cache installation failed:" + e);
		}
	}
}
