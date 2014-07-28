package com.synergykit.android;

import com.google.gson.Gson;
import com.synergykit.android.request.Delete;
import com.synergykit.android.request.Get;
import com.synergykit.android.request.Post;
import com.synergykit.android.request.Put;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

/**
 * Created by tomas_000 on 27.2.14.
 */
public class Synergykit {
	private String application;
	private String key;
	private Gson gson;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Synergykit(String application, String key) {
		this.application = application;
		this.key = key;
		gson = new Gson();
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public HttpResponse get(String url) {
		Get get = new Get(url) {
		};
		try {
			HttpResponse response = get.execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public HttpResponse post(String url, String json) {
		Post post = new Post(url) {
		};
		try {
			HttpResponse response = post
					.execute(new StringEntity(json, "UTF-8"));
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public HttpResponse put(String url, String json) {
		Put put = new Put(url) {
		};
		try {
			HttpResponse response = put
					.execute(new StringEntity(json, "UTF-8"));
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public HttpResponse delete(String url) {
		Delete delete = new Delete(url) {
		};
		try {
			HttpResponse response = delete.execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
