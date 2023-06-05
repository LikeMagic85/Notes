package com.like_magic.notes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.like_magic.notes.R
import com.like_magic.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListNotesFragment()
    }

    private fun setListNotesFragment(){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, ListNotesFragment.newInstance())
            .commit()
    }
}