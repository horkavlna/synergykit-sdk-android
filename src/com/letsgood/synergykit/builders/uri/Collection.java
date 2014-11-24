package com.letsgood.synergykit.builders.uri;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.log.SynergyKITLog;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Collection {

	/* Attributes */
	private String collection = null;

	/* Resource setter */
	public void setCollection(String collection) {
		
		// null check
		if (collection == null || collection.length() == 0) {			
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);		
		}
		
		this.collection = collection;
	}

	/* Resource getter */
	public String getCollection() {
		return collection;
	}
}
