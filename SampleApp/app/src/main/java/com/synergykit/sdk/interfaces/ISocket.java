package com.synergykit.sdk.interfaces;

import android.net.sip.SipSession;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;

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
    public void onSocket(String event, String message, String collection, Emitter.Listener listener);
    public void offSocket(String event,String message, String collection, Emitter.Listener listener);
}
