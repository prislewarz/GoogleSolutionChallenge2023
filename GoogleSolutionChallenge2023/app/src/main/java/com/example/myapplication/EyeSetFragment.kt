import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.EyeStretchingStart
import com.example.myapplication.NeckStretchingStart
import com.example.myapplication.databinding.FragmentEyeSetBinding

class EyeSetFragment : Fragment() {
    private var _binding: FragmentEyeSetBinding? = null
    private val binding get() = _binding!!
    private var alertMessage = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentEyeSetBinding.inflate(inflater, container, false)
        val numberPicker = binding.npker
        numberPicker.apply {
            maxValue = 10
            minValue = 0
            value = 0
        }
        binding.eyeStrechingStart.setOnClickListener {
            val alertIntent = Intent(activity, EyeStretchingStart::class.java)
            if(alertMessage != "null"){
                alertIntent.putExtra("경고문", alertMessage)
            }
            startActivity(alertIntent)
        }

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
}