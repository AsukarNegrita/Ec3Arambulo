package com.gerson.ec3lmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gerson.ec3lmp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnMap.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCamera.setOnClickListener {
            val intent = Intent(this, Camera::class.java)
            startActivity(intent)
            finish()
        }
    }
}