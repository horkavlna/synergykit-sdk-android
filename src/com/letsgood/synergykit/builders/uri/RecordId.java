package com.letsgood.synergykit.builders.uri;

import android.util.Log;

import com.letsgood.synergykit.SynergyKIT;
import com.letsgood.synergykit.SynergyKITSdk;
import com.letsgood.synergykit.builders.errors.Errors;

public class RecordId {

	/* Constructor */

	
	/* Attributes */
	private String recordId = null;
	
	
	/* Resource setter */
	public void setRecordId(String recordId){	
		
		//null check
		if(recordId==null || recordId.length()==0){
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}	
		
		this.recordId = recordId;		
	}
	
	
	/* Resource getter */
	public String getRecordId(){		
		return recordId;
	}
}
