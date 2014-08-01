package com.synergykit.android.response;

import java.util.List;

import org.apache.http.HttpResponse;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public interface GetAllResponseListener {
	public void doneCallback(HttpResponse httpResponse,SynergykitBaseObject[] baseObjects);
	public void errorCallback(HttpResponse httpResponse, SynergykitErrorObject errorObject);

}
