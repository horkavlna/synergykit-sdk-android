package com.letsgood.synergykit.log;

import android.util.Log;

public class SynergyKITLog {
	/* Constants */
	private static final String TAG = "SynergyKIT";

	/* Attributes */
	private static SynergyKITLog instance = null;
	private boolean enabled = false;
	
	/* Instance getter */
	public static SynergyKITLog getInstance(){
		
		if(instance == null){
			instance = new SynergyKITLog();
		}
		
		return instance;
	}
	
	/* Enabled setter */
	public static void setEnabled(boolean enabled){
		SynergyKITLog.getInstance().enabled = enabled;
	}
	
	/* Enabled getter */
	public static boolean isEnabled(){
		return SynergyKITLog.getInstance().enabled;
	}
	
	/* Log */
	public static void print(String log){
		// null check
		if(log == null)
			return;
		
		//print 
		if(SynergyKITLog.getInstance().enabled == true){
			Log.d(TAG, log);
		}
			
	}
	
}
