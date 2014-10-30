package com.letsgood.synergykit.interfaces;

import java.lang.reflect.Type;

import com.letsgood.synergykit.listeners.DeleteResponseListener;
import com.letsgood.synergykit.listeners.UserResponseListener;
import com.letsgood.synergykit.listeners.UsersResponseListener;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITUser;

public interface IUsers{
	public void getUser(SynergyKITConfig config, UserResponseListener listener);
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode );
	public void getUsers(SynergyKITConfig config, UsersResponseListener listener);
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode);
	public void createUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode);
	public void updateUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode);
	public void deleteUser(String userId,DeleteResponseListener listener, boolean parallelMode);

}
