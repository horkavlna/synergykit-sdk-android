package com.letsgood.synergykitsdkandroid.listeners;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 19. 3. 2015.
 */
public interface SocketStateListener {
    public void connected();
    public void disconnected();
    public void reconnected();
}
