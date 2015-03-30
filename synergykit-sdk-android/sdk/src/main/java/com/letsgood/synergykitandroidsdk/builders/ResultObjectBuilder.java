package com.letsgood.synergykitandroidsdk.builders;



import com.letsgood.synergykitandroidsdk.addons.GsonWrapper;
import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;

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
	public static SynergyKitObject buildObject(int statusCode,BufferedReader data,Type type){
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;
		
		
		// Build object
		try {	
			return (SynergyKitObject) GsonWrapper.getGson().fromJson(data, type);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Build base objects */
	public static SynergyKitObject[] buildObjects(int statusCode, BufferedReader data, Type type){
		SynergyKitObject[] baseObjects;
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;
		
		// Build objects
		try {
			baseObjects = (SynergyKitObject[]) GsonWrapper.getGson().fromJson(data, type);
			
			return baseObjects;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Build error object */
	public static SynergyKitError buildError(int statusCode, BufferedReader data){
		SynergyKitError errorObject;
		
		// Param check
		if(data == null)
			return null;
		
		
		// Build error
		try {
			errorObject =  (SynergyKitError) GsonWrapper.getGson().fromJson(data, SynergyKitError.class);
			errorObject.setStatusCode(statusCode);
			return errorObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Build error object */
	public static SynergyKitError buildError(int statusCode){
		SynergyKitError error = new SynergyKitError();
		
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
