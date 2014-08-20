package com.synergykit.android.responsemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import android.content.Entity;
import android.util.Log;

import com.google.gson.stream.JsonReader;
import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.resource.SynergyKITBaseObject;
import com.synergykit.android.resource.SynergyKITErrorObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class ResultObjectBuilder {
	
	/* Build base object */
	public static SynergyKITBaseObject buildBaseObject(int statusCode,BufferedReader data,Type type){
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;
		
		
		try {	
			//Log.e("Synergykit", jsonContent);
			return (SynergyKITBaseObject) GsonWrapper.getInstance().getGson().fromJson(data, type);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Build base objects */
	public static SynergyKITBaseObject[] buildBaseObjects(int statusCode, BufferedReader data, Type type){
		SynergyKITBaseObject[] baseObjects;
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;

		
		
		try {
			baseObjects = (SynergyKITBaseObject[]) GsonWrapper.getInstance().getGson().fromJson(data, type);																					
			
			return baseObjects;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/* Build error object */
	public static SynergyKITErrorObject buildErrorObject(int statusCode, BufferedReader data){	
		
		// Param check
		if(data == null)
			return null;
		
		
		try {
			return (SynergyKITErrorObject) GsonWrapper.getInstance().getGson().fromJson(data, SynergyKITErrorObject.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
		
		return null;
	}
}
