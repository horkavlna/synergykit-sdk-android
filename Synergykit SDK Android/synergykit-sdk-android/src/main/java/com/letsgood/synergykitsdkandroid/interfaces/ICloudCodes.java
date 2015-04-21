package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitCloudCode;

import java.lang.reflect.Type;

public interface ICloudCodes {
	public void invokeCloudCode(SynergykitCloudCode cloudCodeObject,Type type, ResponseListener listener, boolean parallelMode);

}


