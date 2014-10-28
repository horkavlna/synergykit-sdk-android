package com.letsgood.synergykit;

import com.letsgood.synergykit.request.SynergyKITRequest;

public class SynergyKIT {

	/* Synergylize */
	public static void synergylize(SynergyKITRequest request, boolean parallelMode){
		SynergyKITSdk.getInstance().synergylize(request, parallelMode);
	}
}
