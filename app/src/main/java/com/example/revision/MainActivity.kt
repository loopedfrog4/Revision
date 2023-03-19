package com.example.revision

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val Tag = "Debug"

    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)
        var second = findViewById<Button>(R.id.viewSecondary)
        var textViewResult = findViewById<TextView>(R.id.texty)

        val viewModel: simpleViewModel = ViewModelProvider(this).get(simpleViewModel::class.java)
        viewModel.myLiveData.observe(this) { newValue ->
            textViewResult.text = newValue.toString()
        }

        second.setOnClickListener {
            val intent = Intent(this, SecondaryActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            viewModel.updateLiveData(1)
        }

        var startServiceBtn = findViewById<Button>(R.id.startServiceBtn)
        var stopServiceBtn = findViewById<Button>(R.id.stopServiceBtn)
        var sendDataBtn = findViewById<Button>(R.id.sendDataBtn)
        var serviceStatusTextView = findViewById<TextView>(R.id.serviceStatus)
        var inputText = findViewById<EditText>(R.id.numberInput)

        startServiceBtn.setOnClickListener {
            Intent(this, LocalService::class.java).also {
                startService(it)
                serviceStatusTextView.text = "Service is running..."
            }
        }

        stopServiceBtn.setOnClickListener {
            Intent(this, LocalService::class.java).also {
                stopService(it)
                serviceStatusTextView.text = "Service has stopped..."
            }
        }

        sendDataBtn.setOnClickListener {
            Intent(this, LocalService::class.java).also {
                val dataString = inputText.text.toString()
                Log.d("Check", dataString)
                it.putExtra("EXTRA_DATA", dataString)
                startService(it)
            }
        }

        var createNotification = findViewById<Button>(R.id.createNotification)
        createNotification.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            var notification: simpleNotification = simpleNotification()
            notification.createNotification(this, "First Notification", "Test", intent)
        }
    }

}