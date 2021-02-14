package com.example.nasapictures.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasapictures.R
import com.example.nasapictures.ui.grid.GridFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GridFragment.newInstance())
                .commitNow()
        }
    }
}