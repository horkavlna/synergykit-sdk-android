package com.synergykit.android.request;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import com.synergykit.android.resource.ISynergykitResponseListener;

public class PostRunnable implements Runnable{

	/*Attributes */
	public ISynergykitResponseListener mListener;
	public String mUrl;
	public String mJson;
	
	
	@Override
	public void run() {
		Post post = new Post(mUrl){};
		try {
			HttpResponse response = post
					.execute(new StringEntity(mJson, "UTF-8"));

			if(mListener!=null)
				mListener.run(response,null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
