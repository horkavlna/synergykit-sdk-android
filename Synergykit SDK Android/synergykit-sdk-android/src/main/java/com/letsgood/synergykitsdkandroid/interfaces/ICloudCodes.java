package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

public interface ICloudCodes {
	public void invokeCloudCode(SynergykitConfig config, SynergykitObject object, ResponseListener listener);

}


