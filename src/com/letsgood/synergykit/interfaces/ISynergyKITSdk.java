package com.letsgood.synergykit.interfaces;

import com.letsgood.synergykit.request.SynergyKITRequest;
import com.letsgood.synergykit.resources.SynergyKITConfig;

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
