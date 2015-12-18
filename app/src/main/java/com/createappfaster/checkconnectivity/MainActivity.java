package com.createappfaster.checkconnectivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ConnectivityBroadCast.NetworkMonitoringListener {

    private ConnectivityBroadCast connectivityBroadCast;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = (Button) findViewById(R.id.button);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectivityBroadCast = new ConnectivityBroadCast(this);
        connectivityBroadCast.registerNetworkMonitoringListener(this);
        registerReceiver(connectivityBroadCast, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        connectivityBroadCast.unregisterNetworkMonitoringListener(this);
        unregisterReceiver(connectivityBroadCast);
    }

    @Override
    public void networkUnavailable() {
        Toast.makeText(this, "Network Unavailable", Toast.LENGTH_LONG).show();
    }

    @Override
    public void networkAvailable() {
        Toast.makeText(this, "Network Available", Toast.LENGTH_LONG).show();
    }
}
