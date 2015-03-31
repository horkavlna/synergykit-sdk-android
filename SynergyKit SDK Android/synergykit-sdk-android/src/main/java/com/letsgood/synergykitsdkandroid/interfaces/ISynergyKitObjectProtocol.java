package com.letsgood.synergykitsdkandroid.interfaces;


import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 3. 2015.
 */
public interface ISynergyKitObjectProtocol {
    public void save();
    public void fetch();
    public void delete();
    public void save(ResponseListener listener);
    public void fetch(ResponseListener listener);
    public void delete(DeleteResponseListener listener);
}
