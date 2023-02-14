import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentEyeSetBinding
import com.example.myapplication.databinding.FragmentWalkingSetBinding

class WalkingSetFragment : Fragment() {
    private var _binding: FragmentWalkingSetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentWalkingSetBinding.inflate(inflater, container, false)
        binding.npker.apply {
            maxValue = 10
            minValue = 0
            value = 0
        }
        return binding.root
    }
}