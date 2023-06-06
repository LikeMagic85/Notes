package com.like_magic.notes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.like_magic.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}