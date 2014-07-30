package com.synergykit.android.request;


import org.apache.http.HttpResponse;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public interface IRequest {
	public HttpResponse get(String url); //get request
	public HttpResponse post(String url, String json); //post request
	public HttpResponse put(String url, String json); //put request
	public HttpResponse delete(String url); //delete request
}
