package com.example.myapplication

import EyeSetFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.StretchingEndBinding

class StretchingEnd : AppCompatActivity() {
    lateinit var binding : StretchingEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stretching_end)

        //StretchingMiddle:class.java를 눈 메인 화면으로 바꾸기
        //intent에 neckforehead랑 value 값 바꾸기
        binding.endButton.setOnClickListener{
            val intent = Intent(this, EyeSetFragment::class.java)
            intent.putExtra("neckforehead", 0)
            startActivity(intent)

            startActivity(intent)
        }
    }
}