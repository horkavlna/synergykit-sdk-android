package com.synergykit.android.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;

public abstract class Get {
	private String mUrl;
    private String mAccept;
    private String mContentType;

    private DefaultHttpClient mHttpClient;
    private HttpGet mHttpGet;

    public Get(String uri) {
        this.mUrl = uri;
    }

    public HttpResponse execute()
			throws ClientProtocolException, IOException, IllegalStateException {
    	
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
        HttpConnectionParams.setSoTimeout(httpParams, 30000);
        mHttpClient = new DefaultHttpClient(httpParams);


        mHttpGet = new HttpGet(mUrl);
        mHttpGet.addHeader("User-Agent", "Android");
        
        
        HttpResponse response = mHttpClient.execute(mHttpGet);

        return response;
    }

    public String getUri() {
        return mUrl;
    }

    public String getAccept() {
        return mAccept;
    }

    public void setAccept(String accept) {
        this.mAccept = accept;
    }

    public String getContentType() {
        return mContentType;
    }

    public void setContentType(String contentType) {
        this.mContentType = contentType;
    }

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