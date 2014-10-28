package com.letsgood.synergykit.builders;

import java.io.BufferedReader;
import java.lang.reflect.Type;

import org.apache.http.HttpStatus;
import org.apache.http.ParseException;

import com.letsgood.synergykit.addons.GsonWrapper;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class ResultObjectBuilder {
	
	/* Build base object */
	public static SynergyKITObject buildObject(int statusCode,BufferedReader data,Type type){
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;
		
		
		try {	
			//Log.e("Synergykit", jsonContent);
			return (SynergyKITObject) GsonWrapper.getGson().fromJson(data, type);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Build base objects */
	public static SynergyKITObject[] buildObjects(int statusCode, BufferedReader data, Type type){
		SynergyKITObject[] baseObjects;
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;

		
		
		try {
			baseObjects = (SynergyKITObject[]) GsonWrapper.getGson().fromJson(data, type);																					
			
			return baseObjects;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/* Build error object */
	public static SynergyKITError buildError(int statusCode, BufferedReader data){	
		
		// Param check
		if(data == null)
			return null;
		
		
		try {
			return (SynergyKITError) GsonWrapper.getGson().fromJson(data, SynergyKITError.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
		
		return null;
	}
}
