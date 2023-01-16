package com.example.basicpermissions

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity: AppCompatActivity(R.layout.activity_second) {

    private val textView: TextView by lazy { findViewById(R.id.textView) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}