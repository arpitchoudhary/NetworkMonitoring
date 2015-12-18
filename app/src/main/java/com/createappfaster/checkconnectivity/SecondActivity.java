package com.createappfaster.checkconnectivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Arpit on 12/17/15.
 */
public class SecondActivity extends AppCompatActivity implements ConnectivityBroadCast.NetworkMonitoringListener {

    private ConnectivityBroadCast connectivityBroadCast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectivityBroadCast = new ConnectivityBroadCast(this);
        connectivityBroadCast.registerNetworkMonitoringListener(this);
        this.registerReceiver(connectivityBroadCast, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        connectivityBroadCast.unregisterNetworkMonitoringListener(this);
        this.unregisterReceiver(connectivityBroadCast);
    }

    @Override
    public void networkUnavailable() {
        Toast.makeText(this, " Sec Network Unavailable", Toast.LENGTH_LONG).show();
    }

    @Override
    public void networkAvailable() {
        Toast.makeText(this, "Sec Network Available", Toast.LENGTH_LONG).show();
    }
}
