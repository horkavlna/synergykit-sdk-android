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
    private String uri;
    private String accept;
    private String contentType;

    private DefaultHttpClient httpClient;
    private HttpGet httpGet;

    public Get(String uri) {
        this.uri = uri;
    }

    public HttpResponse execute()
			throws ClientProtocolException, IOException, IllegalStateException {
    	
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
        HttpConnectionParams.setSoTimeout(httpParams, 30000);
        httpClient = new DefaultHttpClient(httpParams);


        httpGet = new HttpGet(uri);
        httpGet.addHeader("User-Agent", "Android");
        
        
        HttpResponse response = httpClient.execute(httpGet);

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