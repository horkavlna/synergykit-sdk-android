package com.synergykit.android.response;

import com.synergykit.android.resource.SynergykitBaseObject;
import com.synergykit.android.resource.SynergykitErrorObject;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public interface BaseResponseListener {
	public void doneCallback(int statusCode,SynergykitBaseObject baseObject);
	public void errorCallback(int statusCode, SynergykitErrorObject errorObject);
}
