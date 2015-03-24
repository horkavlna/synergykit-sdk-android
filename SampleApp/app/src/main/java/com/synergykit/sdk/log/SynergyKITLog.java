package com.synergykit.sdk.log;

import android.util.Log;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKitLog {
	
	/* Constants */
	private static final String TAG = "SynergyKitDebug";

	/* Attributes */
	private static SynergyKitLog instance = null;
	private boolean enabled = false;
	
	/* Instance getter */
	public static SynergyKitLog getInstance(){
		
		if(instance == null){
			instance = new SynergyKitLog();
		}
		
		return instance;
	}
	
	/* Enabled setter */
	public static void setEnabled(boolean enabled){
		SynergyKitLog.getInstance().enabled = enabled;
	}
	
	/* Enabled getter */
	public static boolean isEnabled(){
		return SynergyKitLog.getInstance().enabled;
	}
	
	/* Log */
	public static void print(String log){
		// null check
		if(log == null)
			return;
		
		//print 
		if(SynergyKitLog.getInstance().enabled == true){
			Log.d(TAG, log);
		}
			
	}
	
}
