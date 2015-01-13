package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.request.SynergyKITRequest;
import com.synergykit.sdk.resources.SynergyKITConfig;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface ISynergyKITSdk {
	public void init(String tenant, String applicationKey);
	public void reset();
	public void setTenant(String tenant);
	public String getTenant();
	public void setApplicationKey(String applicationKey);
	public String getApplicationKey();
	public boolean isInit();
	public void setConfig(SynergyKITConfig config);
	public SynergyKITConfig getConfig();
	public void synergylize(SynergyKITRequest request, boolean parallelMode);
	public boolean isDebugModeEnabled();
	public void setDebugModeEnabled(boolean debugModeEnabled);

}
