package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.StretchingMiddleBinding

class StretchingMiddle : AppCompatActivity() {

    fun page_num_check(page_num : Int): String{
        when (page_num){

            1 -> return getString(R.string.eyespin2_des)
            2 -> return getString(R.string.eyeupdown_des)
            3 -> return getString(R.string.eyeleftright_des)
            4 -> return getString(R.string.eyepush_des)
            5 -> return getString(R.string.eyeseefar_des)
            7 -> return getString(R.string.neckup_des)
            8 -> return getString(R.string.neckdown_des)
            9 -> return getString(R.string.neckleftright_des)

        }

    return "ERROR"
    }

    private lateinit var binding : StretchingMiddleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StretchingMiddleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var page_num = 0
        if(intent.hasExtra("eyespin1")){
            binding.stretchingDes.text = getString(R.string.eyespin1_des)
            page_num = 1
        }
        else if(intent.hasExtra("neckforehead")){
            binding.stretchingDes.text = getString(R.string.neckforehead_des)
            page_num = 7
        }

        binding.nextButton.setOnClickListener{
            if(page_num == 6 || page_num == 10){
                val intent = Intent(this, StretchingEnd::class.java)
                startActivity(intent)
            }
            else{
                binding.stretchingDes.text = page_num_check(page_num)
                page_num++
            }
        }

    }
}