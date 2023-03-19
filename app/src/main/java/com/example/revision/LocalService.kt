package com.example.revision

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class LocalService: Service() {

    init {
        Log.d("Test", "Service is running...")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val intentData = intent?.getStringExtra("EXTRA_DATA")

        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                intentData?.let {
                    Log.d("Service Recevied", intentData)
                }
            }

        }

        return START_STICKY
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        stopSelf()
//    }

    override fun onBind(p0: Intent?): IBinder?  = null
}