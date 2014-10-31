package com.letsgood.synergykit.interfaces;

import com.letsgood.synergykit.listeners.UserResponseListener;
import com.letsgood.synergykit.resources.SynergyKITUser;

public interface IAuthorization {
	public void registerUser(SynergyKITUser user, UserResponseListener listener);
	public void loginUser(SynergyKITUser user,UserResponseListener listener);
}
