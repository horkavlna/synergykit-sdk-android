package com.synergykit.sdk;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.interfaces.ISocket;
import com.synergykit.sdk.listeners.SocketListener;
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

            unsetBufferedListeners(); //unset buffered listeners
            unsetBaseListeners(); //unset base listeners

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

    //----------------------------------------------------------------------------------------------

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

    /* Connect socket */
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

        this.setBufferedListeners(); //set buffered listeners
        this.setBaseListeners(); //set base listeners

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
    public void onSocket(String message, String collection, String filterName, String filter, SocketListener listener) {
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

        //init onSocketItem
        if(filterName!=null && !filterName.isEmpty() && filter!=null && !filter.isEmpty() )
            socketItem = new SocketItem(message, collection,filterName,filter, listener); //init onSocket item
        else
            socketItem = new SocketItem(message,collection,listener);


        //on or buffer
        if(isSocketConnected()){
            socket.on(EVENT_SUBSCRIBED,socketItem.getSubscribedListener());
            socket.on(EVENT_UNSUBSCRIBED,socketItem.getUnsubscribedListener());
            socket.emit(EMIT_ON,GsonWrapper.getGson().toJson(socketItem.getSocketAuth()));
        }

        socketItemBuffer.add(socketItem);
    }

    /* On socket */
    @Override
    public void onSocket(String message, String collection, SocketListener listener) {
        this.onSocket(message, collection, null, null, listener);
    }



    /* Off socket */
    @Override
    public void offSocket(String message, String collection, String filterName, String filter, SocketListener listener) {
        SocketItem socketItem = null;
        SocketItem selectedSocketItem = null;

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

        //off listener if buffered
        if(isSocketConnected()){
            for(int i=0; socketItemBuffer!=null && i<socketItemBuffer.size(); i++){
                selectedSocketItem = socketItemBuffer.get(i);
                if(selectedSocketItem!=null && selectedSocketItem.equals(socketItem)){

                    /* off */
                    if(selectedSocketItem.getCallListener()!=null)
                        socket.off(selectedSocketItem.getEvent(),selectedSocketItem.getCallListener());
                    else
                        socket.off(selectedSocketItem.getEvent());

                }
            }
        }
    }

    /* Off socket */
    @Override
    public void offSocket(String message, String collection, SocketListener listener) {
       this.onSocket(message, collection, null, null, listener);
    }

    /* Base listeners setter */
    private void setBaseListeners(){
        socket.on(EVENT_CONNECTED,connectedListener); //on connected
        socket.on(EVENT_RECONNECTED,reconnectedListener); //on reconnected
        socket.on(EVENT_DISCONNECTED,disconnectedListener); //on disconnected
    }

    /* Base listeners unsetter */
    private void unsetBaseListeners(){
        socket.off(EVENT_CONNECTED, connectedListener); //on connected
        socket.off(EVENT_RECONNECTED, reconnectedListener); //on reconnected
        socket.off(EVENT_DISCONNECTED, disconnectedListener); //on disconnected
    }

    /* Buffered listeners setter */
    private void setBufferedListeners(){

        /* Set buffered listeners */
        for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size();i++ ) {

            socket.on(socketItemBuffer.get(i).getEvent(),socketItemBuffer.get(i).getCallListener());
            socket.on(EVENT_SUBSCRIBED, socketItemBuffer.get(i).subscribedListener); //TODO must have unique subscribed listener
            socket.on(EVENT_UNSUBSCRIBED, socketItemBuffer.get(i).unsubscribedListener); //TODO must have unique unsubscribed listener

        }
    }

    /* Buffered listeners setter */
    private void unsetBufferedListeners(){

        /* Set buffered listeners */
        for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size();i++ ) {

            socket.off(socketItemBuffer.get(i).getEvent(), socketItemBuffer.get(i).getCallListener());
            socket.off(EVENT_SUBSCRIBED, socketItemBuffer.get(i).getSubscribedListener()); //TODO must have unique subscribed listener
            socket.off(EVENT_UNSUBSCRIBED, socketItemBuffer.get(i).getUnsubscribedListener()); //TODO must have unique unsubscribed listener

        }
    }

    //----------------------------------------------------------------------------------------------

    /* Auth item*/
    private class SocketItem {

        /* Attributes */
        private SynergyKITSocketAuth socketAuth;
        private SocketListener   socketListener = null;
        private Emitter.Listener callListener;
        private Emitter.Listener subscribedListener;
        private Emitter.Listener unsubscribedListener;
        private String event;

        /* Constructor */
        public SocketItem(String message, String collection, SocketListener listener){
            this(message,collection,null,null,listener);
        }

        /* Constructor */
        public SocketItem(String message, String collection, String filterName, String filter,SocketListener listener){

            this.socketAuth = new SynergyKITSocketAuth();
            this.socketAuth.setMessage(message);
            this.socketAuth.setCollection(collection);
            this.event = message + "_" + collection;

            //filter addons
            if(filter!=null && !filter.isEmpty() && filterName!=null && !filterName.isEmpty()){
                this.socketAuth.setQuery(filterName, filter);
                this.event += "_" + filterName;
            }

            this.socketListener = listener;
            this.callListener = initCallListener(listener);
            this.subscribedListener = initSubscribedListener(listener);
            this.unsubscribedListener = initUnsubscribedListener(listener);
        }

        /* Socket auth getter */
        public SynergyKITSocketAuth getSocketAuth() {
            return socketAuth;
        }

        /* Socket auth setter */
        public void setSocketAuth(SynergyKITSocketAuth socketAuth) {
            this.socketAuth = socketAuth;
        }

        /* Call listener getter */
        public Emitter.Listener getCallListener() {
            return callListener;
        }

        /* Subscribed listener getter */
        public Emitter.Listener getSubscribedListener() {
            return subscribedListener;
        }

        /* Unsubscribed listener getter */
        public Emitter.Listener getUnsubscribedListener() {
            return unsubscribedListener;
        }

        /* Event getter */
        public String getEvent() {
            return event;
        }


        /* Init call listener */
        private Emitter.Listener initCallListener(final SocketListener listener){
            return new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    if(listener!=null)
                        listener.call(args);
                    else
                        SynergyKITLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);

                }
            };
        }


        /* Init subscribed listener */
        private Emitter.Listener initSubscribedListener(final SocketListener listener){
            return new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    //on listener
                    if(callListener!=null)
                        socket.on(getEvent(), callListener);

                    SynergyKITLog.print(getEvent() + ": " + STATE_SUBSCRIBED); //print state

                    //subscribed listener
                    if(listener!=null)
                        listener.subscribed();
                    else
                        SynergyKITLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);
                }
            };
        }


        /* Init subscribed listener */
        private Emitter.Listener initUnsubscribedListener(final SocketListener listener){
            return new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    SynergyKITLog.print(getEvent() + ": " + STATE_UNSUBSCRIBED); //print state

                    //unsubscribed listener
                    if(listener!=null)
                        listener.unsubscribed();
                    else
                        SynergyKITLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);


                }
            };
        }



        /* Equal */
        @Override
        public boolean equals(Object o) {
            SocketItem socketItem;


            if(o==null || !(o instanceof SocketItem))
                return false;

            socketItem = (SocketItem) o;

            if(this.socketAuth!=null && socketItem.socketAuth==null)
                return false;

            if(this.socketAuth==null && socketItem.socketAuth!=null)
                return false;

            if(!this.socketAuth.equals(socketItem.socketAuth))
                return false;


            if(!this.event.equals(((SocketItem) o).event))
                return false;

            if(this.socketListener!=null && socketItem.socketListener==null)
                return false;

            if(this.socketListener==null &&  socketItem.socketListener!=null)
                return false;

            if(this.socketListener != socketItem.socketListener)
                return false;


            return true;
        }
    }
}
