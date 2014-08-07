package com.synergykit.android.request;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public abstract class Delete {
	/* Attributes */
    private String mUrl;
    private String mAccept;
    private String mContentType;

    private DefaultHttpClient mHttpClient;
    private HttpDelete mHttpDelete;

    /* Constructor */
    public Delete(String uri) {
        this.mUrl = uri;
    }

    /* Execute */
    public HttpResponse execute()
			throws ClientProtocolException, IOException, IllegalStateException {
    	
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
        HttpConnectionParams.setSoTimeout(httpParams, 30000);
        mHttpClient = new DefaultHttpClient(httpParams);


        mHttpDelete = new HttpDelete(mUrl);
        mHttpDelete.addHeader("User-Agent", "Android");
        
        
        HttpResponse response = mHttpClient.execute(mHttpDelete);

        return response;
    }

    /* URL getter */
    public String getUrl() {
        return mUrl;
    }

    /* Accept getter */
    public String getAccept() {
        return mAccept;
    }

    /* Accept setter */
    public void setAccept(String accept) {
        this.mAccept = accept;
    }

    /* Content type getter */
    public String getContentType() {
        return mContentType;
    }

    /* Content type setter */
    public void setContentType(String contentType) {
        this.mContentType = contentType;
    }

    /* Finalize */
    @Override
    protected void finalize() {
        try {
            mHttpClient.getConnectionManager().shutdown();
            super.finalize();
        } catch (Exception e) {

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}