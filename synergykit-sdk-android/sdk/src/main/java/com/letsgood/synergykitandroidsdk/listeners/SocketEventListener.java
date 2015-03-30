package com.letsgood.synergykitandroidsdk.listeners;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 19. 3. 2015.
 */
public interface SocketEventListener {
    public void call(Object... args);
    public void subscribed();
    public void unsubscribed();
    public void unauthorized();
}
