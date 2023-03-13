package com.example.myapplication

import android.app.Dialog
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.DialogTimeBinding

class TimeDialog(private val context : AppCompatActivity) {
    private lateinit var binding: DialogTimeBinding
    private val dialog = Dialog(context)

    private lateinit var listener : TimeDialogOKClickedListener

    fun show(context : String){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        binding.hourPicker.minValue = 0
        binding.hourPicker.maxValue = 3
        binding.minutePicker.minValue = 0
        binding.minutePicker.maxValue = 59

        binding.cancel.setOnClickListener(){

            dialog.dismiss()
        }
        binding.ok.setOnClickListener(){

        }
        dialog.show()

    }

    fun setOnOKClickedListener(listener: (Int) -> Unit) {
        this.listener = object: TimeDialogOKClickedListener {
            override fun onOKClicked(content: Int) {
                listener(content)
            }
        }
    }
    interface TimeDialogOKClickedListener{
        fun onOKClicked(content : Int){

        }
    }

}