package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.request.SynergyKitRequest;
import com.synergykit.sdk.resources.SynergyKitConfig;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface ISynergyKitSdk {
	public void init(String tenant, String applicationKey);
	public void reset();
	public void setTenant(String tenant);
	public String getTenant();
	public void setApplicationKey(String applicationKey);
	public String getApplicationKey();
    public String getToken();
    public void setToken(String token);
	public boolean isInit();
	public void setConfig(SynergyKitConfig config);
	public SynergyKitConfig getConfig();
	public void synergylize(SynergyKitRequest request, boolean parallelMode);
	public boolean isDebugModeEnabled();
	public void setDebugModeEnabled(boolean debugModeEnabled);

}
