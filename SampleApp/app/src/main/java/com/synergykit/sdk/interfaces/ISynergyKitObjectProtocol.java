package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;

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
