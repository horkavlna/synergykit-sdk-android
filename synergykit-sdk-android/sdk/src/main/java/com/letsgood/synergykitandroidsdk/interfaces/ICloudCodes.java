package com.letsgood.synergykitandroidsdk.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.listeners.ResponseListener;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;

public interface ICloudCodes {
	public void invokeCloudCode(SynergyKitConfig config, SynergyKitObject object, ResponseListener listener);

}


