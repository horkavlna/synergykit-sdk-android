package com.synergykit.sdk.builders;

import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITObject;

import org.apache.http.HttpStatus;
import org.apache.http.ParseException;

import java.io.BufferedReader;
import java.lang.reflect.Type;

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
		
		
		// Build object
		try {	
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
		
		// Build objects
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
		SynergyKITError errorObject;
		
		// Param check
		if(data == null)
			return null;
		
		
		// Build error
		try {
			errorObject =  (SynergyKITError) GsonWrapper.getGson().fromJson(data, SynergyKITError.class);
			errorObject.setStatusCode(statusCode);
			return errorObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Build error object */
	public static SynergyKITError buildError(int statusCode){			
		SynergyKITError error = new SynergyKITError();
		
		error.setStatusCode(statusCode); //set status code
		
		switch (statusCode) {
		
		case Errors.SC_NO_OBJECT:
			error.setMessage(Errors.MSG_NO_OBJECT);
			break;
			
		case Errors.SC_URI_NOT_VALID:
			error.setMessage(Errors.MSG_URI_NOT_VALID);
			break;
			
		case Errors.SC_SK_NOT_INITIALIZED:
			error.setMessage(Errors.MSG_SK_NOT_INITIALIZED);
			break;

		default:
			error.setMessage(Errors.MSG_UNSPECIFIED_ERROR);
			break;
		}
		
		
		
		return error;
	}
}
