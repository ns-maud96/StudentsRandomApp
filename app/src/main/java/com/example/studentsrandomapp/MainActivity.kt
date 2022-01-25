package com.example.studentsrandomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(StartFragment.TAG)
                .add(R.id.fragmentContainer, StartFragment.newInstance())
                .commit()
    }
}