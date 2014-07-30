package com.synergykit.android.request;

import com.synergykit.android.resource.ISynergykitResponseListener;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class Request{

	/* Request GET */
	public static void get(String url) {
	/*	Get get = new Get(url) {
		};
		try {
			HttpResponse response = get.execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		*/
	}

	/* Request POST */
	public static void post(String url, String json,ISynergykitResponseListener listener) {
		PostRunnable runnabe = new PostRunnable();
		runnabe.mJson = json;
		runnabe.mUrl = url;
		runnabe.mListener=listener;
		new Thread(runnabe).start();	
	}

	/* Request PUT */
	public static void put(String url, String json) {
	/*	Put put = new Put(url) {
		};
		try {
			HttpResponse response = put
					.execute(new StringEntity(json, "UTF-8"));
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}*/
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
