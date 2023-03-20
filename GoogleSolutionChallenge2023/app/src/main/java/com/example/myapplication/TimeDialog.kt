package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager.LayoutParams
import com.example.myapplication.databinding.DialogTimeBinding

class TimeDialog(context: Context) {
    private lateinit var binding: DialogTimeBinding
    private val dialog = Dialog(context)
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    fun show(){

        binding = DialogTimeBinding.inflate(inflater)
        val params: LayoutParams = dialog.getWindow()!!.getAttributes()

        val lp = LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = 800
        lp.height = LayoutParams.WRAP_CONTENT


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        binding.hourPicker.minValue = 0
        binding.hourPicker.maxValue = 3

        binding.minutePicker.minValue = 0
        binding.minutePicker.maxValue = 59


        dialog.show()

        val window = dialog.window
        window!!.attributes = lp

        binding.cancel.setOnClickListener(){


            dialog.dismiss()
        }
        binding.ok.setOnClickListener(){
            onClickedListner.onClicked(binding.hourPicker.value, binding.minutePicker.value)
            dialog.dismiss()
        }





    }
    interface BtnClickListner{
        fun  onClicked(hour : Int, minute : Int)

    }
    private lateinit var onClickedListner : BtnClickListner

    fun setOnClickedListener(listner: BtnClickListner){
        onClickedListner = listner
    }



}