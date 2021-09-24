package com.example.pecodetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        changeThemeFromSplashToDefault()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun changeThemeFromSplashToDefault() {
        setTheme(R.style.Theme_PeCodeTask)
    }
}