import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.myapplication.*
import com.example.myapplication.databinding.FragmentNeckSetBinding
import java.text.DateFormat
import java.util.*


class NeckSetFragment : Fragment() {
    private var _binding: FragmentNeckSetBinding? = null
    private val binding get() = _binding!!
    lateinit var mainActivity: MainActivity
    private var alertMessage: String = "null"
    lateinit var neckPrefs : PreferenceUtil
    private var calendar = Calendar.getInstance()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentNeckSetBinding.inflate(inflater, container, false)
        neckPrefs = PreferenceUtil(requireContext())


        initButton()
        applySavedData()
        startNeckStreching()
        initAlertMessage()
        return binding.root
    }
    private fun applySavedData(){
        alertMessage = neckPrefs.get("neckSetMessage", getString(R.string.necks_message))

        calendar.set(Calendar.HOUR_OF_DAY, neckPrefs.get("neckHour", -1))
        calendar.set(Calendar.MINUTE, neckPrefs.get("neckMinute", -1))
        updateTimeText(calendar)

    }

    private fun initButton(){
        binding.setBtn.setOnClickListener{
            var time = Calendar.getInstance()

            var hour = time.get(Calendar.HOUR)
            var minute = time.get(Calendar.MINUTE)
            var timeListener = object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {

                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.SECOND, 0)

                    neckPrefs.set("neckHour", hourOfDay)
                    neckPrefs.set("neckMinute", minute)

                    updateTimeText(calendar)

                    startAlarm(calendar)
                }
            }
            var builder = TimePickerDialog(mainActivity,timeListener,hour,minute,false)
            builder.show()
        }
    }


    private fun updateTimeText(calendar: Calendar) {
        if(calendar.get(Calendar.HOUR_OF_DAY) != - 1 && calendar.get(Calendar.MINUTE) != -1){
            var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)

            binding.hourText.text = curTime
        }

    }

    private fun startAlarm(calendar: Calendar){
        var alarmManager : AlarmManager = mainActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(mainActivity, AlertReceiver::class.java)
        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)
        intent.putExtra("time", curTime)

        var pendingIntent = PendingIntent.getBroadcast(mainActivity, 1, intent, PendingIntent.FLAG_IMMUTABLE )

        if(calendar.before(Calendar.getInstance())){
            calendar.add(Calendar.DATE, 1)
        }

        if(binding.isEverydaycb.isChecked){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY ,pendingIntent)

        }
        else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }

    private fun startNeckStreching(){
        val startNeckStrechingButton: Button = binding.neckStrechingStart
        startNeckStrechingButton.setOnClickListener {
            val alertIntent = Intent(activity, NeckStretchingStart::class.java)
            if(alertMessage != "null"){
                alertIntent.putExtra("경고문", alertMessage)
            }

            startActivity(alertIntent)
        }
    }

    private fun initAlertMessage() {
        val setButton: Button = binding.setMeesageButton
        val setMessage: EditText = binding.setMessage
        setMessage.hint = alertMessage


        setButton.setOnClickListener {
            alertMessage = "${setMessage.text}"
            Toast.makeText(context, alertMessage, Toast.LENGTH_LONG).show()
            neckPrefs.set("neckSetMessage", setMessage.text.toString())
        }

    }
}