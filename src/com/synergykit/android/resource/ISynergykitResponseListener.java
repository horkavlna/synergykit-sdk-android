package com.synergykit.android.resource;

import org.apache.http.HttpResponse;

import com.synergykit.android.response.SynergykitBaseObject;
import com.synergykit.android.response.SynergykitErrorObject;

public interface ISynergykitResponseListener {
	public void doneCallback(HttpResponse httpResponse,SynergykitBaseObject baseObject);
	public void errorCallback(HttpResponse httpResponse, SynergykitErrorObject errorObject);
}
