package com.synergykit.android.request;

import java.lang.reflect.Type;

import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetAllResponseListener;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class Request{

	/* Request GET */
	public static void get(String url, BaseResponseListener listener, Type type) {
		GetAsyncTask get = new GetAsyncTask();
		
		get.setUrl(url);
		get.setListener(listener);
		get.setType(type);
		
		get.execute();
	}

	/* Request GET ALL */
	public static void getAll(String url, GetAllResponseListener listener, Type type){
		GetAllAsyncTask getAll = new GetAllAsyncTask();
		
		getAll.setUrl(url);
		getAll.setListener(listener);
		getAll.setType(type);
		
		getAll.execute();
	}
	
	/* Request POST */
	public static void post(String url, String json,BaseResponseListener listener, Type type) {
		PostAsyncTask post = new PostAsyncTask();
		
		post.setUrl(url);
		post.setJson(json);
		post.setListener(listener);
		post.setType(type);
		
		post.execute();
	}

	/* Request PUT */
	public static void put(String url, String json,BaseResponseListener listener, Type type) {
		PutAsyncTask put = new PutAsyncTask();
		
		put.setUrl(url);
		put.setJson(json);
		put.setListener(listener);
		put.setType(type);
		
		put.execute();
	}

	/* Request DELETE */
	public static void delete(String url, DeleteResponseListener listener) {
		DeleteAsyncTask delete = new DeleteAsyncTask();
		
		delete.setUrl(url);
		delete.setListener(listener);
		
		delete.execute();
	}

}
