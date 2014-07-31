package com.synergykit.android.request;

import com.synergykit.android.resource.ISynergykitResponseListener;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class Request{

	/* Request GET */
	public static void get(String url, ISynergykitResponseListener listener, Class<?> classOfT) {
		GetAsyncTask get = new GetAsyncTask();
		get.mUrl=url;
		get.mListener=listener;
		get.mClassOfT=classOfT;
		
		get.execute();
	}

	/* Request POST */
	public static void post(String url, String json,ISynergykitResponseListener listener, Class<?> classOfT) {
		PostAsyncTask post = new PostAsyncTask();
		post.mJson=json;
		post.mUrl=url;
		post.mListener=listener;
		post.mClassOfT = classOfT;
		
		post.execute();
	}

	/* Request PUT */
	public static void put(String url, String json,ISynergykitResponseListener listener, Class<?> classOfT) {
		PutAsyncTask put = new PutAsyncTask();
		put.mJson=json;
		put.mUrl=url;
		put.mListener=listener;
		put.mClassOfT=classOfT;
		put.execute();
	}

	/* Request DELETE */
	public static void delete(String url) {
	/*	Delete delete = new Delete(url) {
		};
		try {
			HttpResponse response = delete.execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}*/
	}

}
