package com.synergykit.sdk.interfaces;

import android.net.sip.SipSession;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.synergykit.sdk.listeners.SocketListener;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public interface ISocket {
    public boolean initSocket();
    public boolean isSocketInited();
    public boolean isSocketConnected();
    public void connectSocket();
    public void disconnectSocket();
    public void emitViaSocket(String event, Object... args);
    public void emitViaSocket(String event, Object[] args,Ack ack);
    public void onSocket(String message, String collection, String filterName, String filter, SocketListener listener);
    public void onSocket(String message, String collection, SocketListener listener);
    public void offSocket(String message, String collection, String filterName, String filter, SocketListener listener);
    public void offSocket(String message, String collection, SocketListener listener);
   // public void offSocket(String message, String collection, String filterName);
   // public void offSocket(String message, String collection);
}
