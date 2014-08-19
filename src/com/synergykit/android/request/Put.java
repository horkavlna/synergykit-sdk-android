package com.synergykit.android.request;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public abstract class Put {
	private String uri;
	private String accept;
	private String contentType;

	private DefaultHttpClient httpClient;
	private HttpPut httpPut;

	public Put(String uri) {
		this.uri = uri;
	}

	public HttpResponse execute(HttpEntity entity)
			throws ClientProtocolException, IOException, IllegalStateException {

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
		HttpConnectionParams.setSoTimeout(httpParams, 30000);
		HttpConnectionParams.setLinger(httpParams, 0);
		httpClient = new DefaultHttpClient(httpParams);

		httpPut = new HttpPut(uri);
		httpPut.addHeader("User-Agent", "Android");
		httpPut.addHeader("Content-Type", "application/json");
		httpPut.addHeader("Accept", "application/json");
		httpPut.setEntity(entity);

		HttpResponse response = httpClient.execute(httpPut);

		return response;
	}

	public String getUri() {
		return uri;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	protected void finalize() {
		try {
			httpClient.getConnectionManager().shutdown();
			super.finalize();
		} catch (Exception e) {

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}