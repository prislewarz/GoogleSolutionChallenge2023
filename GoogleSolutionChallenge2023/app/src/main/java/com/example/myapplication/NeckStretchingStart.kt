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

        val alertMessage = intent.getStringExtra("경고문")
        if(alertMessage != null){
            binding.neckStretchingIntro.text = alertMessage
        }


        binding.startButton.setOnClickListener{
            val startIntent = Intent(this, StretchingMiddle::class.java)
            startIntent.putExtra("neckforehead", 0)
            startActivity(startIntent)
        }
    }
}