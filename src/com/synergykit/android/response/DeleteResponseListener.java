package com.synergykit.android.response;

import org.apache.http.HttpResponse;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public interface DeleteResponseListener {
	public void doneCallback(HttpResponse httpResponse);
	public void errorCallback(HttpResponse httpResponse);
}
