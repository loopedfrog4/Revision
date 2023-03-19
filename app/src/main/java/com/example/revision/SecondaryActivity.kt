package com.example.revision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        var secondTextView = findViewById<TextView>(R.id.second)

        val viewModel: simpleViewModel = ViewModelProvider(this).get(simpleViewModel::class.java)
        viewModel.myLiveData.observe(this) { newValue ->
            secondTextView.text = newValue.toString()
        }
    }
}