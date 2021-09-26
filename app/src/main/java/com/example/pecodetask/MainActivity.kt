package com.example.pecodetask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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