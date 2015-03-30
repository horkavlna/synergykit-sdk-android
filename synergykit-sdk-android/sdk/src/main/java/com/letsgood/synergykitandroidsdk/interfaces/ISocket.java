package com.letsgood.synergykitandroidsdk.interfaces;


import android.support.annotation.Nullable;
import com.letsgood.synergykitandroidsdk.listeners.SocketEventListener;
import com.letsgood.synergykitandroidsdk.listeners.SocketStateListener;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitSocketFilter;

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
    public void onSocket(String eventName, @Nullable String collectionName, SocketEventListener listener);
    public void onSocket(String eventName, @Nullable String collectionName, @Nullable SynergyKitSocketFilter filter, SocketEventListener listener);

    public void offSocket(String eventName, SocketEventListener listener);
    public void offSocket(String eventName, @Nullable String collectionName, SocketEventListener listener);
    public void offSocket(String eventName, @Nullable String collectionName, @Nullable SynergyKitSocketFilter filter, SocketEventListener listener);

    public void emitViaSocket(String eventName, Object object);



}
