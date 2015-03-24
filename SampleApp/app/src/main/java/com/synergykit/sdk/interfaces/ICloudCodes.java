package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface ICloudCodes {
	public void invokeCloudCode(SynergyKitConfig config,SynergyKitObject object,  ResponseListener listener);

}


