package com.synergykit.sdk;

import android.support.annotation.Nullable;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.interfaces.ISocket;
import com.synergykit.sdk.listeners.SocketEventListener;
import com.synergykit.sdk.listeners.SocketStateListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.resources.SynergyKitSocketAuth;

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
    //private List<SocketItem> socketItemBuffer = null;
    private static SocketStateListener socketStateListener = null;



    //----------------------------------------------------------------------------------------------
    private class StateListener{

        /* Attributes */
        private SocketStateListener socketStateListener = null;

        /* Connected listener */
        private final Emitter.Listener connectedListener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
//            for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size(); i++){
//                emitViaSocket(EMIT_ON,GsonWrapper.getGson().toJson(socketItemBuffer.get(i).getSocketAuth()));
//            }

                SynergyKitLog.print(STATE_CONNECTED);

                if(socketStateListener!=null)
                    socketStateListener.connected();

            }
        };

        /* Disconnected listener */
        private final Emitter.Listener disconnectedListener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {



                // unsetBufferedListeners(); //unset buffered listeners

                SynergyKitLog.print(STATE_DISCONNECTED);

                if(socketStateListener!=null)
                    socketStateListener.disconnected();
            }
        };

        /* Reconnected listener */
        private final Emitter.Listener reconnectedListener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                //setBufferedListeners(); //set buffured listeners

                SynergyKitLog.print(STATE_RECONNECTED);

                if(socketStateListener!=null)
                    socketStateListener.reconnected();
            }
        };

        /* Socket state listener setter*/
        public void setSocketStateListener(@Nullable SocketStateListener socketStateListener) {
            this.socketStateListener = socketStateListener;
        }

        /* Connected listener getter*/
        public Emitter.Listener getConnectedListener() {
            return connectedListener;
        }

        /* Disconnected listener getter*/
        public Emitter.Listener getDisconnectedListener() {
            return disconnectedListener;
        }

        /* Reconnected listener getter*/
        public Emitter.Listener getReconnectedListener() {
            return reconnectedListener;
        }
    }

    //----------------------------------------------------------------------------------------------

//    /* Init socket*/
//    @Override
//    public boolean initSocket() {
//        UriBuilder uriBuilder = new UriBuilder();
//
//        try {
//              socket = IO.socket(uriBuilder.getSocketUrl().toString());
//
//            socketItemBuffer = new LinkedList<>();
//            return true;
//        } catch (URISyntaxException e) {
//            SynergyKitLog.print(Errors.MSG_SOCKET_INIT_FAILED);
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /* In socket inited*/
//    @Override
//    public boolean isSocketInitialized() {
//
//        if(socket!=null)
//            return true;
//
//        return false;
//    }
//
//    /* Is socket connected */
//    @Override
//    public boolean isSocketConnected() {
//
//        if(socket!=null && socket.connected())
//            return true;
//
//        return false;
//    }
//
//    /* Is prepared*/
//    private boolean isPrepared(){
//        //init check
//        if(!SynergyKit.isInit()){
//            SynergyKitLog.print(Errors.MSG_SK_NOT_INITIALIZED);
//            return false;
//        }
//
//        //init check
//        if(!isSocketInitialized()){
//            SynergyKitLog.print(Errors.MSG_SOCKET_NOT_INITED);
//            return false;
//        }
//
//        return true;
//    }
//
//    /* Connect socket */
//    @Override
//    public void connectSocket() {
//        this.connectSocket(null);
//    }
//
//    /* Connect socket */
//    @Override
//    public void connectSocket(SocketStateListener listener) {
//
//        if(!isPrepared())
//            return;  //prepared check
//
//        socketStateListener = listener; //set socket state listener
//
//        this.setBaseListeners(); //set base listeners
//        this.setBufferedListeners(); //set buffered listeners
//
//        socket.connect();
//    }
//
//    /* Disconnect */
//    @Override
//    public void disconnectSocket() {
//
//        if(!isPrepared())
//            return;  //prepared check
//
//
//        for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size(); i++){
//            emitViaSocket(EMIT_OFF,GsonWrapper.getGson().toJson(socketItemBuffer.get(i).getSocketAuth()));
//        } //unsubcribe
//
//        unsetBufferedListeners(); //unset buffer listener
//
//        if(socketItemBuffer!=null)
//            socketItemBuffer.clear(); // clear buffer
//
//        unsetBaseListeners(); //unset base listeners
//
//        if(socket!=null)
//          socket.disconnect(); //disconnect
//
//        SynergyKitLog.print(STATE_DISCONNECTED); //state disconnected
//
//        if(socketStateListener!=null)
//            socketStateListener.disconnected(); //
//
//    }
//
//    /* Emit via socket */
//    @Override
//    public void emitViaSocket(String event, Object... args) {
//
//        if(!isPrepared())
//            return;  //prepared check
//
//        socket.emit(event, args);
//    }
//
//    /* Emit via socket */
//    @Override
//    public void emitViaSocket(String event, Object[] args, Ack ack) {
//
//        if(!isPrepared())
//            return;  //prepared check
//
//        socket.emit(event,args,ack);
//    }
//
//    /* On socket */
//    @Override
//    public void onSocket(String message, String collection, String filterName, String filter, SocketEventListener listener) {
//        SocketItem socketItem = null;
//
//        if(!isPrepared())
//            return;  //prepared check
//
//        //init onSocketItem
//        if(filterName!=null && !filterName.isEmpty() && filter!=null && !filter.isEmpty() )
//            socketItem = new SocketItem(message, collection,filterName,filter, listener); //init onSocket item
//        else
//            socketItem = new SocketItem(message,collection,listener);
//
//
//        //on and buffer
//        if(isSocketConnected()){
//            socket.on(EVENT_SUBSCRIBED, socketItem.getSubscribedListener());
//            socket.on(EVENT_UNSUBSCRIBED,socketItem.getUnsubscribedListener());
//            socket.emit(EMIT_ON,GsonWrapper.getGson().toJson(socketItem.getSocketAuth()));
//        }
//
//        socketItemBuffer.add(socketItem);
//    }
//
//    /* On socket */
//    @Override
//    public void onSocket(String message, String collection, SocketEventListener listener) {
//        this.onSocket(message, collection, null, null, listener);
//    }
//
//    @Override
//    public void onSocket(String event, Emitter.Listener listener) {
//
//        //TODO DOPSAT, AZ BUDE FUNKCI NA SERVERU
//
////        if(!isPrepared())
////            return;  //prepared check
////
////        socket.on(event,listener);
//    }
//
//
//    /* Off socket */
//    @Override
//    public void offSocket(String message, String collection, String filterName, String filter, SocketEventListener listener) {
//        SocketItem socketItem = null;
//        SocketItem selectedSocketItem;
//
//        if(!isPrepared())
//            return;  //prepared check
//
//        //init socketItem
//        if(filterName!=null && !filterName.isEmpty() && filter!=null && !filter.isEmpty() )
//            socketItem = new SocketItem(message, collection,filterName,filter, listener); //init onSocket item
//        else
//            socketItem = new SocketItem(message,collection,listener);
//
//        //off listener if buffered
//        if(isSocketConnected()){
//            for(int i=0; socketItemBuffer!=null && i<socketItemBuffer.size(); i++){
//                selectedSocketItem = socketItemBuffer.get(i);
//                if(selectedSocketItem!=null && selectedSocketItem.equals(socketItem)){
//
//                    /* off */
//                    if(selectedSocketItem.getCallListener()!=null)
//                        socket.off(selectedSocketItem.getEvent(),selectedSocketItem.getCallListener());
//                    else
//                        socket.off(selectedSocketItem.getEvent());
//
//                }
//            }
//        }
//    }
//
//    /* Off socket */
//    @Override
//    public void offSocket(String message, String collection, SocketEventListener listener) {
//       this.onSocket(message, collection, null, null, listener);
//    }
//
//    @Override
//    public void offSocket(String event, Emitter.Listener listener) {
//
//        //TODO dopsat, az bude hotovo na serveru
//
////        if(!isPrepared())
////            return;  //prepared check
////
////        socket.off(event,listener);
//    }
//
//    /* Base listeners setter */
//    private void setBaseListeners(){
//        socket.on(EVENT_CONNECTED,connectedListener); //on connected
//        socket.on(EVENT_RECONNECTED,reconnectedListener); //on reconnected
//        socket.on(EVENT_DISCONNECTED,disconnectedListener); //on disconnected
//    }
//
//    /* Base listeners unsetter */
//    private void unsetBaseListeners(){
//        socket.off(EVENT_CONNECTED, connectedListener); //on connected
//        socket.off(EVENT_RECONNECTED, reconnectedListener); //on reconnected
//        socket.off(EVENT_DISCONNECTED, disconnectedListener); //on disconnected
//    }
//
//    /* Buffered listeners setter */
//    private void setBufferedListeners(){
//
//        /* Set buffered listeners */
//        for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size();i++ ) {
//            socket.on(socketItemBuffer.get(i).getEvent(),socketItemBuffer.get(i).getCallListener());
//            socket.on(EVENT_SUBSCRIBED, socketItemBuffer.get(i).subscribedListener); //TODO must have unique subscribed listener
//            socket.on(EVENT_UNSUBSCRIBED, socketItemBuffer.get(i).unsubscribedListener); //TODO must have unique unsubscribed listener
//
//        }
//    }
//
//    /* Buffered listeners setter */
//    private void unsetBufferedListeners(){
//
//        /* Set buffered listeners */
//        for(int i=0; socketItemBuffer !=null && i < socketItemBuffer.size();i++ ) {
//            socket.off(socketItemBuffer.get(i).getEvent(), socketItemBuffer.get(i).getCallListener());
//            socket.off(EVENT_SUBSCRIBED, socketItemBuffer.get(i).getSubscribedListener()); //TODO must have unique subscribed listener
//            socket.off(EVENT_UNSUBSCRIBED, socketItemBuffer.get(i).getUnsubscribedListener()); //TODO must have unique unsubscribed listener
//
//        }
//    }
//
//    //----------------------------------------------------------------------------------------------
//
//    /* Auth item*/
//    private class SocketItem {
//
//        /* Attributes */
//        private SynergyKitSocketAuth socketAuth;
//        private SocketEventListener socketListener = null;
//        private Emitter.Listener callListener;
//        private Emitter.Listener subscribedListener;
//        private Emitter.Listener unsubscribedListener;
//        private String event;
//
//        /* Constructor */
//        public SocketItem(String message, String collection, SocketEventListener listener){
//            this(message,collection,null,null,listener);
//        }
//
//        /* Constructor */
//        public SocketItem(String message, String collection, String filterName, String filter,SocketEventListener listener){
//
//            this.socketAuth = new SynergyKitSocketAuth();
//            this.socketAuth.setMessage(message);
//            this.socketAuth.setCollection(collection);
//            this.event = message + "_" + collection;
//
//            //filter addons
//            if(filter!=null && !filter.isEmpty() && filterName!=null && !filterName.isEmpty()){
//                this.socketAuth.setQuery(filterName, filter);
//                this.event += "_" + filterName;
//            }
//
//            this.socketListener = listener;
//            this.callListener = initCallListener(listener);
//            this.subscribedListener = initSubscribedListener(listener);
//            this.unsubscribedListener = initUnsubscribedListener(listener);
//        }
//
//        /* Socket auth getter */
//        public SynergyKitSocketAuth getSocketAuth() {
//            return socketAuth;
//        }
//
//        /* Socket auth setter */
//        public void setSocketAuth(SynergyKitSocketAuth socketAuth) {
//            this.socketAuth = socketAuth;
//        }
//
//        /* Call listener getter */
//        public Emitter.Listener getCallListener() {
//            return callListener;
//        }
//
//        /* Subscribed listener getter */
//        public Emitter.Listener getSubscribedListener() {
//            return subscribedListener;
//        }
//
//        /* Unsubscribed listener getter */
//        public Emitter.Listener getUnsubscribedListener() {
//            return unsubscribedListener;
//        }
//
//        /* Event getter */
//        public String getEvent() {
//            return event;
//        }
//
//
//        /* Init call listener */
//        private Emitter.Listener initCallListener(final SocketEventListener listener){
//            return new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//
//                    if(listener!=null)
//                        listener.call(args);
//                    else
//                        SynergyKitLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);
//
//                }
//            };
//        }
//
//
//        /* Init subscribed listener */
//        private Emitter.Listener initSubscribedListener(final SocketEventListener listener){
//            return new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//
//                    SynergyKitLog.print(getEvent() + ": " + STATE_SUBSCRIBED); //print state
//
//                    //subscribed listener
//                    if(listener!=null)
//                        listener.subscribed();
//                    else
//                        SynergyKitLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);
//                }
//            };
//        }
//
//
//        /* Init subscribed listener */
//        private Emitter.Listener initUnsubscribedListener(final SocketEventListener listener){
//            return new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//
//                    SynergyKitLog.print(getEvent() + ": " + STATE_UNSUBSCRIBED); //print state
//
//                    //unsubscribed listener
//                    if(listener!=null)
//                        listener.unsubscribed();
//                    else
//                        SynergyKitLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);
//
//
//                }
//            };
//        }
//
//
//
//        /* Equal */
//        @Override
//        public boolean equals(Object o) {
//            SocketItem socketItem;
//
//
//            if(o==null || !(o instanceof SocketItem))
//                return false;
//
//            socketItem = (SocketItem) o;
//
//            if(this.socketAuth!=null && socketItem.socketAuth==null)
//                return false;
//
//            if(this.socketAuth==null && socketItem.socketAuth!=null)
//                return false;
//
//            if(!this.socketAuth.equals(socketItem.socketAuth))
//                return false;
//
//
//            if(!this.event.equals(((SocketItem) o).event))
//                return false;
//
//            if(this.socketListener!=null && socketItem.socketListener==null)
//                return false;
//
//            if(this.socketListener==null &&  socketItem.socketListener!=null)
//                return false;
//
//            if(this.socketListener != socketItem.socketListener)
//                return false;
//
//
//            return true;
//        }
//    }
}
