package com.synergykit.android.request;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class Request implements IRequest{

	/* Request GET */
	@Override
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

	/* Request POST */
	@Override
	public HttpResponse post(String url, String json) {
		boolean state;
		Post post = new Post(url){};
		try {
			HttpResponse response = post
					.execute(new StringEntity(json, "UTF-8"));
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* Request PUT */
	@Override
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

	/* Request DELETE */
	@Override
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
