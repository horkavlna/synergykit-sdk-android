package com.synergykit.sdk.interfaces;

import android.support.annotation.Nullable;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.synergykit.sdk.listeners.SocketEventListener;
import com.synergykit.sdk.listeners.SocketStateListener;
import com.synergykit.sdk.resources.SynergyKitSocketFilter;
import com.synergykit.sdk.resources.SynergyKitUser;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public interface ISocket {
    public boolean initSocket();
    public boolean isSocketInitialized();
    public boolean isSocketConnected();

    public void connectSocket();
    public void connectSocket(@Nullable SocketStateListener listener);
    public void disconnectSocket();

    public void onSocket(String eventName, SocketEventListener listener);
    public void onSocket(String eventName,@Nullable String collectionName, SocketEventListener listener);
    public void onSocket(String eventName,@Nullable String collectionName,@Nullable SynergyKitSocketFilter filter, SocketEventListener listener);

    public void offSocket(String eventName, SocketEventListener listener);
    public void offSocket(String eventName, @Nullable String collectionName, SocketEventListener listener);
    public void offSocket(String eventName, @Nullable String collectionName, @Nullable SynergyKitSocketFilter filter, SocketEventListener listener);

    public void emitViaSocket(String eventName, Object object);



}
