package com.synergykit.sdk.listeners;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 19. 3. 2015.
 */
public interface SocketEventListener<T> {
    public void call(T object);
    public void subscribed();
    public void unsubscribed();
    public void unauthorized();
}
