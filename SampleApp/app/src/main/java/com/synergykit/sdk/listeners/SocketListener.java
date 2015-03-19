package com.synergykit.sdk.listeners;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 19. 3. 2015.
 */
public interface SocketListener {
    public void call(Object... args);
    public void subscribed();
    public void unsubscribed();
}
