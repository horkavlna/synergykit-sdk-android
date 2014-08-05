package com.synergykit.android.response;

import org.apache.http.HttpResponse;

import com.synergykit.android.resource.SynergykitBaseObject;
import com.synergykit.android.resource.SynergykitErrorObject;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public interface BaseResponseListener {
	public void doneCallback(HttpResponse httpResponse,SynergykitBaseObject baseObject);
	public void errorCallback(HttpResponse httpResponse, SynergykitErrorObject errorObject);
}
