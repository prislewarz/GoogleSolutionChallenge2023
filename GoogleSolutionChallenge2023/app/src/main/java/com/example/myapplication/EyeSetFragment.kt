import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.AlertReceiver
import com.example.myapplication.EyeStretchingStart
import com.example.myapplication.R
import com.example.myapplication.TimeDialog
import com.example.myapplication.databinding.FragmentEyeSetBinding
import java.util.*

class EyeSetFragment : Fragment() {
    private var _binding: FragmentEyeSetBinding? = null
    private val binding get() = _binding!!
    private var alertMessage = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEyeSetBinding.inflate(inflater, container, false)


        initButton()
        initAlertMessage()
        return binding.root
    }

    private fun initAlertMessage() {
        val setButton: Button = binding.setMessageButton
        val setMessage: EditText = binding.setMessage

        setButton.setOnClickListener {
            alertMessage = "${setMessage.text}"
            Toast.makeText(context, alertMessage, Toast.LENGTH_LONG).show()

        }

    }

    private fun initButton(){
        binding.eyeStrechingStart.setOnClickListener {
            val alertIntent = Intent(activity, EyeStretchingStart::class.java)
            if(alertMessage != "null"){
                alertIntent.putExtra("경고문", alertMessage)
            }
            startActivity(alertIntent)
        }

        binding.timeDialogBtn.setOnClickListener{
            val timeDialog = TimeDialog(requireContext())

            timeDialog.setOnClickedListener(object : TimeDialog.BtnClickListner{
                override fun onClicked(hour: Int, minute: Int) {
                    val fullMinute = ((hour*60 + minute) as Number).toLong()
                    startAlarm(fullMinute)
                    updateTimeText(hour, minute)
                }
            } )

            timeDialog.show()



        }
    }
    private fun startAlarm(fullMinute : Long){
        var alarmManager : AlarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(requireContext(), AlertReceiver::class.java)

        intent.putExtra("identifyStretching", 2)

        var pendingIntent = PendingIntent.getBroadcast(requireContext(), 2, intent, PendingIntent.FLAG_IMMUTABLE )

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + fullMinute * 60000, fullMinute * 60000 ,pendingIntent)
        Log.d("abc", "abc")
    }

    private fun updateTimeText(hour : Int , minute : Int){
        if(hour == 0) {
            if(Locale.getDefault().language == "kr"){
                binding.hourText.text = "$minute" + getString(R.string.eye_set_minute)
            }
            else if(Locale.getDefault().language == "en"){
                binding.hourText.text =  getString(R.string.eye_set) +" $minute"+"m"
            }

        }
        else{
            if(Locale.getDefault().language == "kr"){
                binding.hourText.text = "$minute" + getString(R.string.eye_set_minute)
            }
            else if(Locale.getDefault().language == "en"){
                binding.hourText.text =  getString(R.string.eye_set) +" $hour"+"h"+" $minute"+"m"
            }

        }

    }


}