package com.letsgood.synergykit.builders.uri;

import com.letsgood.synergykit.SynergyKIT;
import com.letsgood.synergykit.SynergyKITSdk;
import com.letsgood.synergykit.builders.errors.Errors;

import android.util.Log;

public class Collection {

	/* Attributes */
	private String collection = null;

	/* Resource setter */
	public void setCollection(String collection) {
		
		// null check
		if (collection == null || collection.length() == 0) {

			if (SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		this.collection = collection;
	}

	/* Resource getter */
	public String getCollection() {
		return collection;
	}
}
