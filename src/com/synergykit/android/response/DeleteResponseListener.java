package com.synergykit.android.response;

import com.synergykit.android.resource.SynergykitErrorObject;


/**
 * 
 * @author Pavel Stambrecht
 *
 */

public interface DeleteResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergykitErrorObject errorObject);
}
