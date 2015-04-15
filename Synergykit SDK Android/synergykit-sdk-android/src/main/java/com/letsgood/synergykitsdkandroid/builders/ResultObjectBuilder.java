package com.letsgood.synergykitsdkandroid.builders;


import android.net.ParseException;

import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

import org.apache.http.HttpStatus;

import java.io.BufferedReader;
import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class ResultObjectBuilder {
	
	/* Build base object */
	public static SynergykitObject buildObject(int statusCode,BufferedReader data,Type type){
		
		// Param check
		if(data == null || statusCode!= HttpStatus.SC_OK)
			return null;
		
		
		// Build object
		try {	
			return (SynergykitObject) GsonWrapper.getGson().fromJson(data, type);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Build base objects */
	public static SynergykitObject[] buildObjects(int statusCode, BufferedReader data, Type type){
		SynergykitObject[] baseObjects;
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;
		
		// Build objects
		try {
			baseObjects = (SynergykitObject[]) GsonWrapper.getGson().fromJson(data, type);
			
			return baseObjects;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Build error object */
	public static SynergykitError buildError(int statusCode, BufferedReader data){
		SynergykitError errorObject;
		
		// Param check
		if(data == null)
			return null;
		
		
		// Build error
		try {
			errorObject =  (SynergykitError) GsonWrapper.getGson().fromJson(data, SynergykitError.class);
			errorObject.setStatusCode(statusCode);
			return errorObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Build error object */
	public static SynergykitError buildError(int statusCode){
		SynergykitError error = new SynergykitError();
		
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

        case HttpStatus.SC_SERVICE_UNAVAILABLE:
            error.setMessage(Errors.MSG_SERVICE_UNAVAILABLE);
            break;

		default:
			error.setMessage(Errors.MSG_UNSPECIFIED_ERROR);
			break;
		}


		
		
		return error;
	}
}
