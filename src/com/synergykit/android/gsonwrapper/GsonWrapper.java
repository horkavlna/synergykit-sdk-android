package com.synergykit.android.gsonwrapper;

import com.google.gson.Gson;

public class GsonWrapper {

	/* Attributes */
	private static GsonWrapper mInstance = null;
	private Gson mGson = new Gson();
	
	/* Instance getter */
	public static GsonWrapper getInstance(){
		
		if(mInstance==null)
			mInstance=new GsonWrapper();
		
		return mInstance;
	}
	
	/* Gson getter */
	public Gson getGson(){
		return mGson;
	}

	
}
