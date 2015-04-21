package com.letsgood.synergykitsdkandroid.builders.uri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class InLineCount {

	/* Attributes */
	boolean enabled = false;

    /* New instance */
    public static InLineCount newInstace(){
        return new InLineCount();
    }

	/* Enabled getter */
	public boolean isEnabled() {
		return enabled;
	}

	/* Enabled setter */
	public InLineCount setEnabled(boolean enabled) {
		this.enabled = enabled;
        return this;
	}

	/* Get inline count */
	public String getInLineCount() {
		if (enabled == true)
			return "$inlinecount=true";
		else
			return "";
	}
}
