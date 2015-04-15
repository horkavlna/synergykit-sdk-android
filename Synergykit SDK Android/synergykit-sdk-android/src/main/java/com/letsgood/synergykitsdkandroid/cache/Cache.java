package com.letsgood.synergykitsdkandroid.cache;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import android.content.Context;

import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.interfaces.ICache;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

import java.io.File;

public class Cache implements ICache {
	
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
			SynergykitLog.print(Errors.MSG_CACHE_INIT_FAILED + ": " + e);
		}
		
	}
}
