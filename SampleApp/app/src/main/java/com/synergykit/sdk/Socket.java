package com.synergykit.sdk;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.interfaces.ISocket;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITSocketAuth;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

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
    private com.github.nkzawa.socketio.client.Socket socket = null;
    private List<AuthItem> authItems = null;

    /* Connected listener */
    private Emitter.Listener connectedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            for(int i=0; authItems!=null && i < authItems.size(); i++){
                emitViaSocket(COLLECTION_ON,GsonWrapper.getGson().toJson(authItems.get(i).getSocketAuth()));
            }

            SynergyKITLog.print(STATE_CONNECTED);
        }
    };

    /* Disconnected listener */
    private Emitter.Listener disconnectedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            SynergyKITLog.print(STATE_DISCONNECTED);
        }
    };

    /* Reconnected listener */
    private Emitter.Listener reconnectedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            SynergyKITLog.print(STATE_RECONNECTED);
        }
    };

    /* Subscribed listener */
    private Emitter.Listener subscribedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            SynergyKITLog.print(STATE_SUBSCRIBED);
        }
    };

    /* Unsubscribed listener */
    private Emitter.Listener unsubscribedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            SynergyKITLog.print(STATE_UNSUBSCRIBED);
        }
    };


    /* Init socket*/
    @Override
    public boolean initSocket() {
        UriBuilder uriBuilder = new UriBuilder();

        try {
            socket = IO.socket(uriBuilder.getSocketUrl().toString());
            authItems = new LinkedList<>();
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            SynergyKITLog.print(Errors.MSG_SOCKET_INIT_FAILED);
            return false;
        }
    }

    /* In socket inited*/
    @Override
    public boolean isSocketInited() {
        if(socket!=null)
            return true;

        return false;
    }

    /* Is socket connected */
    @Override
    public boolean isSocketConnected() {

        if(socket!=null && socket.connected())
            return true;

        return false;
    }


    @Override
    public void connectSocket() {

        //init check
        if(!SynergyKIT.isInit()){
            SynergyKITLog.print(Errors.MSG_SK_NOT_INITIALIZED);
            return;
        }

        //init check
        if(!isSocketInited()){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        for(int i=0; authItems!=null && i <authItems.size();i++ )
            socket.on(authItems.get(i).getEvent(), authItems.get(i).getListener());

        socket.on(EVENT_CONNECTED,connectedListener);
        socket.on(EVENT_RECONNECTED,reconnectedListener);
        socket.on(EVENT_DISCONNECTED,disconnectedListener);
        socket.on(EVENT_SUBSCRIBED,subscribedListener);
        socket.on(EVENT_UNSUBSCRIBED,unsubscribedListener);
        socket.connect();
    }

    @Override
    public void disconnectSocket() {
        socket.disconnect(); //

    }

    @Override
    public void emitViaSocket(String event, Object... args) {
        socket.emit(event,args);
    }

    @Override
    public void emitViaSocket(String event, Object[] args, Ack ack) {
        socket.emit(event,args,ack);
    }


    @Override
    public void onSocket(String event, String message, String collection, Emitter.Listener listener) {


        //init check
        if(!SynergyKIT.isInit()){
            SynergyKITLog.print(Errors.MSG_SK_NOT_INITIALIZED);
            return;
        }

        //init check
        if(!isSocketInited()){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }


          AuthItem authItem = new AuthItem(event,message,collection,listener);
          authItems.add(authItem);
    }

    @Override
    public void offSocket(String event, String message, String collection, Emitter.Listener listener) {

            //init check
            if(!SynergyKIT.isInit()){
                SynergyKITLog.print(Errors.MSG_SK_NOT_INITIALIZED);
                return;
            }

            //init check
            if(!isSocketInited()){
                SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
                return;
            }

           AuthItem authItem = new AuthItem(event,message,collection,listener);
           authItems.remove(authItem);
           socket.off(event);
    }



    private class AuthItem{

        /* Attributes */
        private SynergyKITSocketAuth socketAuth;
        private Emitter.Listener listener;
        private String event;

        /* Constructor */
        public AuthItem(String event,String message, String collection, Emitter.Listener listener){
            this.socketAuth = new SynergyKITSocketAuth();
            this.socketAuth.setMessage(message);
            this.socketAuth.setCollection(collection);
            this.event = event;
            this.listener = listener;
        }

        /* Socket auth getter */
        public SynergyKITSocketAuth getSocketAuth() {
            return socketAuth;
        }

        /* Socket auth setter */
        public void setSocketAuth(SynergyKITSocketAuth socketAuth) {
            this.socketAuth = socketAuth;
        }

        /* Socket listener getter */
        public Emitter.Listener getListener() {
            return listener;
        }

        /* Socket listener setter */
        public void setListener(Emitter.Listener listener) {
            this.listener = listener;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        /* Equal */
        @Override
        public boolean equals(Object o) {
            AuthItem authItem;


            if(o==null || !(o instanceof AuthItem))
                return false;

            authItem = (AuthItem) o;

            if(socketAuth!=null)
                return this.getSocketAuth().equals(authItem.getSocketAuth());


            if(this.getSocketAuth()==null && ((AuthItem) o).getSocketAuth()==null)
                return true;

            return false;
        }
    }
}
