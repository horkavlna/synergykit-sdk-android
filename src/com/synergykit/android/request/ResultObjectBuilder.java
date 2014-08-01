package com.synergykit.android.request;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.response.SynergykitBaseObject;
import com.synergykit.android.response.SynergykitErrorObject;

/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class ResultObjectBuilder {
	
	/* Build base object */
	public static SynergykitBaseObject buildBaseObject(HttpResponse httpResponse,Type type){
		String jsonContent;		
		
		// Param check
		if(httpResponse == null || httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
			return null;
		
		try {
			jsonContent = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			return (SynergykitBaseObject) GsonWrapper.getInstance().getGson().fromJson(jsonContent, type);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Build base objects */
	public static SynergykitBaseObject[] buildBaseObjects(HttpResponse httpResponse, Type type){
		String jsonContent;
		SynergykitBaseObject[] baseObjects;
		
		// Param check
		if(httpResponse == null || httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
			return null;
		
		
		try {
			jsonContent = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			baseObjects = (SynergykitBaseObject[]) GsonWrapper.getInstance().getGson().fromJson(jsonContent, type);
			
			return baseObjects;
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/* Build error object */
	public static SynergykitErrorObject buildErrorObject(HttpResponse httpResponse){
		String jsonContent;		
		
		// Param check
		if(httpResponse == null)
			return null;
		
		try {
			jsonContent = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			return (SynergykitErrorObject) GsonWrapper.getInstance().getGson().fromJson(jsonContent, SynergykitErrorObject.class);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}