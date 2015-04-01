package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitObject;

public interface ICloudCodes {
	public void invokeCloudCode(SynergyKitConfig config, SynergyKitObject object, ResponseListener listener);

}


