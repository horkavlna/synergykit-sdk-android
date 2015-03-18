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
    private static final String EMIT_ON = "on";
    private static final String EMIT_OFF = "off";

    /* Attributes */
    private com.github.nkzawa.socketio.client.Socket socket = null;
    private List<SocketItem> socketItemBuffer = null;

    /* Connected listener */
    private Emitter.Listener connectedListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size(); i++){
                emitViaSocket(EMIT_ON,GsonWrapper.getGson().toJson(socketItemBuffer.get(i).getSocketAuth()));
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

            socketItemBuffer = new LinkedList<>();
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

        //SynergyKit init check
        if(!SynergyKIT.isInit()){
            SynergyKITLog.print(Errors.MSG_SK_NOT_INITIALIZED);
            return;
        }

        //Socket init check
        if(!isSocketInited()){
            SynergyKITLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return;
        }

        for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size();i++ )
            socket.on(socketItemBuffer.get(i).getEvent(), socketItemBuffer.get(i).getListener());


        socket.on(EVENT_CONNECTED,connectedListener); //on connected
        socket.on(EVENT_RECONNECTED,reconnectedListener); //on reconnected
        socket.on(EVENT_DISCONNECTED,disconnectedListener); //on disconnected
        socket.on(EVENT_SUBSCRIBED,subscribedListener); //on subscribed
        socket.on(EVENT_UNSUBSCRIBED,unsubscribedListener); //on unsubscribed


        socket.connect();
    }

    /* Disconnect */
    @Override
    public void disconnectSocket() {

        for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size(); i++){
            emitViaSocket(EMIT_OFF,GsonWrapper.getGson().toJson(socketItemBuffer.get(i).getSocketAuth()));
        }

        socketItemBuffer.clear();

        socket.disconnect(); //

    }

    /* Emit via socket */
    @Override
    public void emitViaSocket(String event, Object... args) {
        socket.emit(event,args);
    }

    /* Emit via socket */
    @Override
    public void emitViaSocket(String event, Object[] args, Ack ack) {
        socket.emit(event,args,ack);
    }

    /* On socket */
    @Override
    public void onSocket(String message, String collection, String filterName, String filter, Emitter.Listener listener) {
        SocketItem onSocketItem = null;


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

        //init onSocketItem
        if(filterName!=null && !filterName.isEmpty() && filter!=null && !filter.isEmpty() )
            onSocketItem = new SocketItem(message, collection,filterName,filter, listener); //init onSocket item
        else
            onSocketItem = new SocketItem(message,collection,listener);


        //on or buffer
        if(isSocketConnected()){
            socket.on(onSocketItem.getEvent(),onSocketItem.getListener());
            socket.emit(EMIT_ON,GsonWrapper.getGson().toJson(onSocketItem.getSocketAuth()));
        }

        socketItemBuffer.add(onSocketItem);
    }

    /* On socket */
    @Override
    public void onSocket(String message, String collection, Emitter.Listener listener) {
        this.onSocket(message, collection, null, null, listener);
    }

    /* Off socket */
    @Override
    public void offSocket(String message, String collection, String filterName) {

    }

    /* Off socket */
    @Override
    public void offSocket(String message, String collection) {

    }

    /* Off socket */
    @Override
    public void offSocket(String message, String collection, String filterName, String filter, Emitter.Listener listener) {
        SocketItem socketItem = null;


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

        //init socketItem
        if(filterName!=null && !filterName.isEmpty() && filter!=null && !filter.isEmpty() )
            socketItem = new SocketItem(message, collection,filterName,filter, listener); //init onSocket item
        else
            socketItem = new SocketItem(message,collection,listener);


        //on or buffer
        if(isSocketConnected()){
            socket.emit(EMIT_OFF, GsonWrapper.getGson().toJson(socketItem.getSocketAuth()));
            socket.off(socketItem.getEvent(), socketItem.getListener());
        }

        socketItemBuffer.remove(socketItem);
    }

    /* Off socket */
    @Override
    public void offSocket(String message, String collection, Emitter.Listener listener) {
        this.onSocket(message, collection, null, null, listener);
    }

    /* Auth item*/
    private class SocketItem {

        /* Attributes */
        private SynergyKITSocketAuth socketAuth;
        private Emitter.Listener listener;
        private String event;

        /* Constructor */
        public SocketItem(String message, String collection, Emitter.Listener listener){
            this.socketAuth = new SynergyKITSocketAuth();
            this.socketAuth.setMessage(message);
            this.socketAuth.setCollection(collection);
            this.event = message + "_" + collection;
            this.listener = listener;
        }

        /* Constructor */
        public SocketItem(String message, String collection, String filterName, String filter, Emitter.Listener listener){
            this.socketAuth = new SynergyKITSocketAuth();
            this.socketAuth.setMessage(message);
            this.socketAuth.setCollection(collection);
            this.socketAuth.setQuery(filterName,filter);
            this.event = message + "_" + collection + "_" + filterName;
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
            SocketItem authItem;


            if(o==null || !(o instanceof SocketItem))
                return false;

            authItem = (SocketItem) o;

            if(socketAuth!=null)
                return this.getSocketAuth().equals(authItem.getSocketAuth());


            if(this.getSocketAuth()==null && ((SocketItem) o).getSocketAuth()==null)
                return true;

            return false;
        }
    }
}
