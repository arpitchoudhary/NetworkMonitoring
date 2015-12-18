package com.createappfaster.checkconnectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arpit on 12/17/15.
 */
public class ConnectivityBroadCast extends BroadcastReceiver {

    private boolean isNetworkAvailble;
    private List<NetworkMonitoringListener> networkMonitoringListeners;

    ConnectivityBroadCast(Context context) {
        networkMonitoringListeners = new ArrayList<NetworkMonitoringListener>();
        getNetworkState(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (getNetworkState(context))
                notifyAllNetworkListener();
        }
    }

    private void notifyAllNetworkListener() {
        for (NetworkMonitoringListener listener : networkMonitoringListeners) {
            notifyNetworkListener(listener);
        }
    }

    private void notifyNetworkListener(NetworkMonitoringListener listener) {
        if (listener != null)
            if (isNetworkAvailble)
                listener.networkAvailable();
            else
                listener.networkUnavailable();
    }

    private boolean getNetworkState(Context context) {
        boolean prevNetworkState = isNetworkAvailble;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isNetworkAvailble = networkInfo != null && networkInfo.isConnected();
        return prevNetworkState != isNetworkAvailble;
    }

    public void registerNetworkMonitoringListener(NetworkMonitoringListener listener) {
        networkMonitoringListeners.add(listener);
        notifyNetworkListener(listener);
    }

    public void unregisterNetworkMonitoringListener(NetworkMonitoringListener listener) {
        networkMonitoringListeners.remove(listener);
    }


    public interface NetworkMonitoringListener {
        void networkUnavailable();

        void networkAvailable();
    }
}
