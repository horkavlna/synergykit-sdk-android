package com.letsgood.synergykitandroidsdk.builders.uri;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;

public class Collection {

	/* Attributes */
	private String collection = null;

	/* Resource setter */
	public void setCollection(String collection) {
		
		// null check
		if (collection == null || collection.length() == 0) {			
			SynergyKitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		this.collection = collection;
	}

	/* Resource getter */
	public String getCollection() {
		return collection;
	}
}
