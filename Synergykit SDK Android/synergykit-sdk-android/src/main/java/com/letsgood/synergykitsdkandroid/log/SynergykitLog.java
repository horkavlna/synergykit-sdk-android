package com.letsgood.synergykitsdkandroid.log;

import android.util.Log;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergykitLog {
	
	/* Constants */
	private static final String TAG = "SynergyKitDebug";

	/* Attributes */
	private static SynergykitLog instance = null;
	private boolean enabled = false;
	
	/* Instance getter */
	public static SynergykitLog getInstance(){
		
		if(instance == null){
			instance = new SynergykitLog();
		}
		
		return instance;
	}
	
	/* Enabled setter */
	public static void setEnabled(boolean enabled){
		SynergykitLog.getInstance().enabled = enabled;
	}
	
	/* Enabled getter */
	public static boolean isEnabled(){
		return SynergykitLog.getInstance().enabled;
	}
	
	/* Log */
	public static void print(String log){
		// null check
		if(log == null)
			return;
		
		//print 
		if(SynergykitLog.getInstance().enabled == true){
			Log.d(TAG, log);
		}
			
	}
	
}
