package com.synergykit.android.requestmanager;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.resource.SynergyKITBaseObject;
import com.synergykit.android.resource.SynergyKITErrorObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class ResultObjectBuilder {
	
	/* Build base object */
	public static SynergyKITBaseObject buildBaseObject(HttpResponse httpResponse,Type type){
		String jsonContent;		
		
		// Param check
		if(httpResponse == null || httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
			return null;
		
		try {
			jsonContent = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			Log.e("Synergykit", jsonContent);
			return (SynergyKITBaseObject) GsonWrapper.getInstance().getGson().fromJson(jsonContent, type);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Build base objects */
	public static SynergyKITBaseObject[] buildBaseObjects(HttpResponse httpResponse, Type type){
		String jsonContent;
		SynergyKITBaseObject[] baseObjects;
		
		// Param check
		if(httpResponse == null || httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
			return null;
		
		
		try {
			jsonContent = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			Log.e("Synergykit", jsonContent);
			baseObjects = (SynergyKITBaseObject[]) GsonWrapper.getInstance().getGson().fromJson(jsonContent, type);
			
			return baseObjects;
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/* Build error object */
	public static SynergyKITErrorObject buildErrorObject(HttpResponse httpResponse){
		String jsonContent;		
		
		// Param check
		if(httpResponse == null)
			return null;
		
		try {
			jsonContent = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			Log.e("Synergykit", jsonContent);
			return (SynergyKITErrorObject) GsonWrapper.getInstance().getGson().fromJson(jsonContent, SynergyKITErrorObject.class);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
