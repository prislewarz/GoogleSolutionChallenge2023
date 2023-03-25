import android.Manifest
import android.app.AlertDialog
import android.content.Context.SENSOR_SERVICE
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEyeSetBinding
import com.example.myapplication.databinding.FragmentWalkingSetBinding
import java.util.*

class WalkingSetFragment : Fragment(), SensorEventListener {
    private var _binding: FragmentWalkingSetBinding? = null
    private val binding get() = _binding!!
    private lateinit var tts: TextToSpeech

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.getDefault()
            }
        })
    }

    private lateinit var sensorManager: SensorManager
    private lateinit var stepCountSensor: Sensor
    var currentSteps: Int = 0
    var walkingCount: Int? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentWalkingSetBinding.inflate(inflater, container, false)

        initNumberPicker()
        permissionCheck()
        setWalkingTime()
        return binding.root
    }

    private fun permissionCheck(){
        if (ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 0)
        }
    }

    private fun initNumberPicker(){
        val numberPicker: NumberPicker = binding.npker
        numberPicker.apply {
            maxValue = 10
            minValue = 0
            value = 0
        }
    }

    private fun setWalkingTime(){
        val walkingSetButton: Button = binding.walkingTimeSetButton
        walkingSetButton.setOnClickListener {
            walkingCount = binding.npker.value
            Toast.makeText(context, "적용", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        sensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        stepCountSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        if (stepCountSensor != null) {
            sensorManager!!.registerListener(
                this,
                stepCountSensor,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0f) {
                currentSteps++
                if(currentSteps==walkingCount){
                    val tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
                        if (status == TextToSpeech.SUCCESS) {
                            tts.language = Locale.getDefault()
                            if(currentSteps==walkingCount){
                                val builder = AlertDialog.Builder(context)
                                builder.setMessage(getString(R.string.smb_message))
                                builder.setPositiveButton("OK") { dialog, which -> }
                                val dialog = builder.create()
                                dialog.show()
                                tts.speak(getString(R.string.smb_message), TextToSpeech.QUEUE_FLUSH, null, null)
                            }
                        }
                    })
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
}