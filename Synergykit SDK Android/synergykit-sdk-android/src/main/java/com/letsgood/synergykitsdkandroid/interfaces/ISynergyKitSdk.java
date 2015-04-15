package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.request.SynergykitRequest;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

public interface ISynergyKitSdk {
	public void init(String tenant, String applicationKey);
	public void reset();
	public void setTenant(String tenant);
	public String getTenant();
	public void setApplicationKey(String applicationKey);
	public String getApplicationKey();
    public String getToken();
    public void setToken(String token);
    public SynergykitUser getLoggedUser();
    public void setLoggedUser(SynergykitUser user);
	public boolean isInit();
	public void setConfig(SynergykitConfig config);
	public SynergykitConfig getConfig();
	public void synergylize(SynergykitRequest request, boolean parallelMode);
	public boolean isDebugModeEnabled();
	public void setDebugModeEnabled(boolean debugModeEnabled);

}
