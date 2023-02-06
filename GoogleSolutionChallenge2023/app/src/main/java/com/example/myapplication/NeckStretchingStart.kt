package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.NeckStretchingStartBinding

class NeckStretchingStart : AppCompatActivity() {

    lateinit var binding : NeckStretchingStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NeckStretchingStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener{
            val intent = Intent(this, StretchingMiddle::class.java)
            intent.putExtra("neckforehead", 0)
            startActivity(intent)
        }
    }
}