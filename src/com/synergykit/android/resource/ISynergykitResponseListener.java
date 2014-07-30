package com.synergykit.android.resource;

import org.apache.http.HttpResponse;

public interface ISynergykitResponseListener<T> {
	public void run(HttpResponse httpResponse,T object);
}
