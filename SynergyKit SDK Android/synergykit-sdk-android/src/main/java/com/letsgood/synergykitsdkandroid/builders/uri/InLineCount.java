package com.letsgood.synergykitsdkandroid.builders.uri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class InLineCount {

	/* Attributes */
	boolean enabled = false;

	/* Enabled getter */
	public boolean isEnabled() {
		return enabled;
	}

	/* Enabled setter */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/* Get inline count */
	public String getInLineCount() {
		if (enabled == true)
			return "&$inlinecount=true";
		else
			return "";
	}
}
