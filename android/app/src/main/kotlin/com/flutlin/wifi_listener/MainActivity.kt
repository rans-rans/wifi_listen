package com.flutlin.wifi_listener

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    private lateinit var methodChannel: MethodChannel
    private lateinit var broadcastReceiver: BroadcastReceiver

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "channel")
        broadcastReceiver = WifiBroadcast(methodChannel)

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        intentFilter.also {
            registerReceiver(broadcastReceiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}
