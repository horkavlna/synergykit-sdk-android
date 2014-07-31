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
		GetRunnable runnable = new GetRunnable();
		runnable.mUrl = url;
		runnable.mListener=listener;
		runnable.mClassOfT = classOfT;
		new Thread(runnable).start();
	}

	/* Request POST */
	public static void post(String url, String json,ISynergykitResponseListener listener, Class<?> classOfT) {
		PostRunnable runnabe = new PostRunnable();
		runnabe.mJson = json;
		runnabe.mUrl = url;
		runnabe.mListener=listener;
		runnabe.mClassOfT = classOfT;
		new Thread(runnabe).start();	
	}

	/* Request PUT */
	public static void put(String url, String json,ISynergykitResponseListener listener, Class<?> classOfT) {
		PutRunnable runnabe = new PutRunnable();
		runnabe.mJson = json;
		runnabe.mUrl = url;
		runnabe.mListener=listener;
		runnabe.mClassOfT = classOfT;
		new Thread(runnabe).start();	
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
