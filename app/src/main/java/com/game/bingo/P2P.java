package com.game.bingo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class P2P extends BroadcastReceiver {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private Start activity5;

    public P2P(WifiP2pManager mManager, WifiP2pManager.Channel mChannel, Start activity5) {
        this.mManager = mManager;
        this.mChannel = mChannel;
        this.activity5 = activity5;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                showToast(context, "WiFi is ON");
            } else {
                showToast(context, "WiFi is OFF");
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (mManager != null) {
                if (checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // The permission is granted, proceed with the requestPeers call
                    mManager.requestPeers(mChannel, activity5.peerListListener);
                } else {
                    // Permission is not granted, request it from the user
                    requestLocationPermission(context);
                }
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            if (mManager != null) {
                NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

                if (networkInfo != null && networkInfo.isConnected()) {
                    mManager.requestConnectionInfo(mChannel, activity5.connectionInfoListener);
                }
            }
        }
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private int checkSelfPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission);
    }

    private void requestLocationPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity5, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Show an explanation to the user and then request the permission
            new AlertDialog.Builder(context)
                    .setTitle("Permission Needed")
                    .setMessage("This app requires location permission to discover nearby devices.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity5,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    LOCATION_PERMISSION_REQUEST_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(activity5,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
}
