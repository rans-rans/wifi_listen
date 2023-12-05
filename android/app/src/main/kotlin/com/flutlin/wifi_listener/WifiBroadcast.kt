package com.flutlin.wifi_listener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.p2p.WifiP2pManager
import io.flutter.plugin.common.MethodChannel

class WifiBroadcast(
    private var methodChannel: MethodChannel
):BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {

            WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
                val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)

                    methodChannel.invokeMethod("wifi_change",state)

            }
        }
    }
}