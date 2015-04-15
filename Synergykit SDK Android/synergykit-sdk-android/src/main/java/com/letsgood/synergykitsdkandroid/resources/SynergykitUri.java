package com.letsgood.synergykitsdkandroid.resources;


import android.webkit.URLUtil;

import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

import java.io.Serializable;

public class SynergykitUri implements Serializable {
	
	/* Constants */
	private static final String URI = "URI: ";
	
	/* Attributes */
	private String baseUri;
    private SynergykitEndpoint synergyKitEndpoint;

	
	/* Constructor */
	public SynergykitUri(String uri){
		this.baseUri = uri;
	}

    /* Endpoint getter */
    public SynergykitEndpoint getEndpoint() {
        return synergyKitEndpoint;
    }

    /* Endpoint setter */
    public void setEndpoint(SynergykitEndpoint synergyKitEndpoint) {
        this.synergyKitEndpoint = synergyKitEndpoint;
    }

    /* Uri getter */
	public String toString(){
		String uri = this.baseUri;

        if(synergyKitEndpoint!=null)
            uri += synergyKitEndpoint.toString();

        SynergykitLog.print(URI + uri);

		if( URLUtil.isValidUrl(uri)==false || uri.contains(" ")){
			
			SynergykitLog.print(Errors.MSG_URI_NOT_VALID);
			
			uri=null;
		}

		return uri;
	}
}
