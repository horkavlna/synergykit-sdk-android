package com.synergykit.android.request;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public abstract class Post {
	private String uri;
	private String accept;
	private String contentType;

	private DefaultHttpClient httpClient;
	private HttpPost httpPost;

	public Post(String uri) {
		this.uri = uri;
	}

	public HttpResponse execute(HttpEntity entity,String contentType)
			throws ClientProtocolException, IOException, IllegalStateException {

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 120000);
		HttpConnectionParams.setSoTimeout(httpParams, 90000);
		HttpConnectionParams.setLinger(httpParams, 0);
		httpClient = new DefaultHttpClient(httpParams);
		

		httpPost = new HttpPost(uri);
		httpPost.addHeader("User-Agent", "Android");
		httpPost.addHeader("Content-Type", contentType);
		httpPost.addHeader("Accept", "application/json");
		httpPost.setEntity(entity);

		HttpResponse response = httpClient.execute(httpPost);

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