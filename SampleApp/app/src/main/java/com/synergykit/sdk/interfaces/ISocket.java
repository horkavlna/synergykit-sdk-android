package com.synergykit.sdk.interfaces;

import android.support.annotation.Nullable;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.synergykit.sdk.listeners.SocketEventListener;
import com.synergykit.sdk.listeners.SocketStateListener;
import com.synergykit.sdk.resources.SynergyKitSocketFilter;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public interface ISocket {
    public boolean initSocket();
    public boolean isSocketInitialized();
    public boolean isSocketConnected();

    public void connectSocket(String token);
    public void connectSocket(String token,@Nullable SocketStateListener listener);
    public void disconnectSocket();

    public void onSocket(String event, SocketEventListener listener);
    public void onSocket(String event, String collection, SocketEventListener listener);
    public void onSocket(String event, String collection, SynergyKitSocketFilter filter, SocketEventListener listener);

    public void offSocket(String event, Emitter.Listener listener);
    public void offSocket(String event, String collection, SocketEventListener listener);
    public void offSocket(String event, String collection, SynergyKitSocketFilter filter, SocketEventListener listener);


    public void emitViaSocket(String event, Object object);



}
