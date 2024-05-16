package com.example.filmlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmlist.databinding.ActivityMainBinding
    // gradle.properties kısmına android.enableJetifier=true eklendi
    // build.gradle.app kısmında VERSION_17 olarak guncelledik
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}