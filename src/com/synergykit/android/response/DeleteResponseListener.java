package com.synergykit.android.response;

import org.apache.http.HttpResponse;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public interface DeleteResponseListener {
	public void callback(HttpResponse httpResponse);
}
