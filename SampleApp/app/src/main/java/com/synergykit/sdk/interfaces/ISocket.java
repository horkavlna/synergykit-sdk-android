package com.synergykit.sdk.interfaces;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.synergykit.sdk.listeners.SocketEventListener;
import com.synergykit.sdk.listeners.SocketStateListener;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public interface ISocket {
    public boolean initSocket();
    public boolean isSocketInited();
    public boolean isSocketConnected();
    public void connectSocket();
    public void connectSocket(SocketStateListener listener);
    public void disconnectSocket();
    public void emitViaSocket(String event, Object... args);
    public void emitViaSocket(String event, Object[] args,Ack ack);
    public void onSocket(String message, String collection, String filterName, String filter, SocketEventListener listener);
    public void onSocket(String message, String collection, SocketEventListener listener);
    public void onSocket(String event, Emitter.Listener listener);
    public void offSocket(String message, String collection, String filterName, String filter, SocketEventListener listener);
    public void offSocket(String message, String collection, SocketEventListener listener);
    public void offSocket(String event, Emitter.Listener listener);
}
