package com.synergykit.android.response;

import java.util.List;

import org.apache.http.HttpResponse;

import com.synergykit.android.resource.SynergykitBaseObject;
import com.synergykit.android.resource.SynergykitErrorObject;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public interface GetRecordsResponseListener {
	public void doneCallback(HttpResponse httpResponse,SynergykitBaseObject[] baseObjects);
	public void errorCallback(HttpResponse httpResponse, SynergykitErrorObject errorObject);

}