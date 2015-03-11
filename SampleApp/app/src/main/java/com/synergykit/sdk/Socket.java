package com.synergykit.sdk;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.interfaces.ISocket;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITSocketAuth;

import java.net.URISyntaxException;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class Socket implements ISocket{

    /* Constants */
    public static final String STATE_CONNECTED = "Socket connected";
    public static final String STATE_DISCONNECTED = "Socket disonnected";
    public static final String STATE_RECONNECTED = "Socket reconnected";
    public static final String STATE_SUBSCRIBED = "Socket subscribed";
    public static final String STATE_UNSUBSCRIBED = "Socket unsubscribed";
    private static final String EVENT_CONNECTED = "connect";
    private static final String EVENT_DISCONNECTED = "disconnect";
    private static final String EVENT_RECONNECTED = "reconnect";
    private static final String EVENT_SUBSCRIBED = "subscribed";
    private static final String EVENT_UNSUBSCRIBED = "unsubscribed";
    private static final String COLLECTION_ON = "on";
    private static final String COLLECTION_OFF = "off";

    /* Attributes */
    private com.github.nkzawa.socketio.client.Socket socket;
    private int subscribedCount = 0;
    private Object lock = new Object();

    private Emitter.Listener connectedListener;

    private final Emitter.Listener disconnectedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            SynergyKITLog.print(STATE_DISCONNECTED);
        }
    };

    private final Emitter.Listener reconnectedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            SynergyKITLog.print(STATE_RECONNECTED);
        }
    };

    private final Emitter.Listener subscribedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            synchronized (lock)
            {
                subscribedCount++;
            }
            SynergyKITLog.print(STATE_SUBSCRIBED);
        }
    };

    private final Emitter.Listener unsubscribedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            synchronized (lock)
            {
                subscribedCount--;
            }


            SynergyKITLog.print(STATE_UNSUBSCRIBED);
        }
    };

    /* Init socket */
    @Override
    public boolean initSocket() {
        UriBuilder uriBuilder = new UriBuilder();

        try {
            socket = IO.socket(uriBuilder.getSocketUrl().toString());
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            SynergyKITLog.print(Errors.MSG_SOCKET_INIT_FAILED);
            return false;
        }
    }

    /* Is socket inited*/
    @Override
    public boolean isSocketInited() {

        if(socket!=null)
            return true;

        return false;
    }

    /* Is socket connected*/
    @Override
    public boolean isSocketConnected() {

        if(socket!=null && socket.connected())
            return true;

        return false;
    }

    /* Connect socket*/
    @Override
    public void connectSocket(final String message, final String collection){

         if(socket!=null && subscribedCount==0) {

             //init connected listener
             connectedListener = new Emitter.Listener() {
                                     @Override
                                     public void call(Object... args) {
                                         SynergyKITLog.print(STATE_CONNECTED);
                                         subscribeSocket(message,collection);
                                     }
                                 };




             socket.on(EVENT_CONNECTED, connectedListener);
             socket.on(EVENT_DISCONNECTED, disconnectedListener);
             socket.on(EVENT_RECONNECTED, reconnectedListener);
             socket.on(EVENT_SUBSCRIBED,subscribedListener);
             socket.on(EVENT_UNSUBSCRIBED,unsubscribedListener);
             socket.connect();

         }else if(subscribedCount>0){
             subscribeSocket(message,collection);
         }
         else
             SynergyKITLog.print(Errors.MSG_SOCKET_CONNECT_FAILED);
    }

    /* Disconnect socket*/
    @Override
    public void disconnectSocket(final String message,final String collection) {

        if(socket==null){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        if(socket!=null && subscribedCount>0){
            unsubscribeSocket(message,collection);
        }else if(socket!=null){
            socket.disconnect();
            socket.off(EVENT_CONNECTED, connectedListener);
            socket.off(EVENT_DISCONNECTED, disconnectedListener);
            socket.off(EVENT_RECONNECTED, reconnectedListener);
            socket.off(EVENT_SUBSCRIBED,subscribedListener);
            socket.off(EVENT_UNSUBSCRIBED,unsubscribedListener);
        }
    }


    /*Emit via socket*/
    @Override
    public void emitViaSocket(String event, Object... args) {

        if(socket==null){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        socket.emit(event,args);
    }

    /* Emit via socket */
    @Override
    public void emitViaSocket(String event, Object[] args,Ack ack) {

        if(socket==null){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        socket.emit(event,args,ack);
    }

    /*On socket*/
    @Override
    public void onSocket(String event, Emitter.Listener listener) {

        if(socket==null){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        socket.on(event, listener);
    }

    /* Off socket*/
    @Override
    public void offSocket(String event, Emitter.Listener listener) {

        if(socket==null){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        socket.off(event, listener);
    }

    /* Subscribe socket*/
    public void subscribeSocket(String message, String collection) {
        SynergyKITSocketAuth socketAuth = new SynergyKITSocketAuth();
        socketAuth.setCollection(collection);
        socketAuth.setMessage(message);

        if(socket==null){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        socket.emit(COLLECTION_ON, socketAuth);

    }

    /* Unsubscribe socket*/
    private void unsubscribeSocket(String message, String collection) {
        SynergyKITSocketAuth socketAuth = new SynergyKITSocketAuth();
        socketAuth.setCollection(collection);
        socketAuth.setMessage(message);

        if(socket==null){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        socket.emit(COLLECTION_OFF,socketAuth);
    }


}
