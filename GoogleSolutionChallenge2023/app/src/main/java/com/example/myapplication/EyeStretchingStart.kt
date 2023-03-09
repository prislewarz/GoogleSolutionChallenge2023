package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.EyeStretchingStartBinding

class EyeStretchingStart : AppCompatActivity() {
    private lateinit var binding : EyeStretchingStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EyeStretchingStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener{
            val intent = Intent(this, StretchingMiddle::class.java)
            intent.putExtra("eyespin1", 0)
            startActivity(intent)
        }
    }
}