package com.example.myapplication

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TTSActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 체크박스 가져오기
        val checkBox = findViewById<CheckBox>(R.id.withvoice)

        // TextToSpeech 초기화
        textToSpeech = TextToSpeech(this, this)

        // 체크박스 클릭 리스너 등록
        checkBox.setOnClickListener {
            // 체크박스가 체크되어 있을 경우에만 TTS 실행
            if (checkBox.isChecked) {
                // 지정한 걸음 수 가져오기

                // TTS로 경고음 출력하기
                textToSpeech.speak("보행 중 스마트폰 사용은 위험합니다.", TextToSpeech.QUEUE_FLUSH, null)
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.KOREAN)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // 언어 데이터가 없거나 지원되지 않는 경우
                // 에러 처리 코드 작성
            }
        } else {
            // TTS 초기화 실패
            // 에러 처리 코드 작성
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // TextToSpeech 종료
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}