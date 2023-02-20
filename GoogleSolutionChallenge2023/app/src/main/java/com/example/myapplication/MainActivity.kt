package com.example.myapplication

import EyeSetFragment
import MainFragment
import NeckSetFragment
import WalkingSetFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var backKeyPressedTime : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2500){
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "종료하려면 뒤로가기를 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2500){
            finishAffinity()
        }
    }

    private fun initViewPager() {
        //circle indicator 설정
        val indicator = binding.indicator
        indicator.apply {
            setViewPager(binding.viewPager)
            createIndicators(4, 0)
        }

        //ViewPager2 Adapter 셋팅
        var viewPager2Adatper = ViewPager2Adapter(this)

        viewPager2Adatper.apply {
            addFragment(MainFragment())
            addFragment(EyeSetFragment())
            addFragment(NeckSetFragment())
            addFragment(WalkingSetFragment())
        }

        //Adapter 연결
        binding.viewPager.apply {
            adapter = viewPager2Adatper
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicator.animatePageSelected(position%4)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tlNavigationView, binding.viewPager) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "메인"
                1 -> tab.text = "눈"
                2 -> tab.text = "목"
                3 -> tab.text = "스몸비"
            }
        }.attach()
    }
}