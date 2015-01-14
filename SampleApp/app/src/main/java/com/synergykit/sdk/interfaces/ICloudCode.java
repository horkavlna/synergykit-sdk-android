package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface ICloudCode {
	public void invokeCloudCode(SynergyKITConfig config,SynergyKITObject object,  ResponseListener listener);
}


