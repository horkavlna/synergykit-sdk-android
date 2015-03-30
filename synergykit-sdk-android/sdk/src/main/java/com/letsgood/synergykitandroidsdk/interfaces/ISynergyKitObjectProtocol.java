package com.letsgood.synergykitandroidsdk.interfaces;


import com.letsgood.synergykitandroidsdk.listeners.DeleteResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.ResponseListener;

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
