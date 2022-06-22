package gunixun.timer.ui.timer

import android.os.Bundle
import android.view.View
import gunixun.timer.databinding.FragmentTimerBinding
import gunixun.timer.ui.BaseFragment
import gunixun.timer.ui.utils.TimerId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Dispatchers


import org.koin.androidx.viewmodel.ext.android.viewModel

class TimerFragment : BaseFragment<FragmentTimerBinding>(FragmentTimerBinding::inflate) {

    companion object {
        fun newInstance() = TimerFragment()
    }

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val viewModel: TimerContract.TimerViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectSignals()
    }

    private fun connectSignals() {
        binding.buttonStart.setOnClickListener {
            viewModel.start(TimerId.Timer1)
        }
        binding.buttonStop.setOnClickListener {
            viewModel.stop(TimerId.Timer1)
        }
        binding.buttonPause.setOnClickListener {
            viewModel.pause(TimerId.Timer1)
        }

        binding.buttonStart2.setOnClickListener {
            viewModel.start(TimerId.Timer2)
        }
        binding.buttonStop2.setOnClickListener {
            viewModel.stop(TimerId.Timer2)
        }
        binding.buttonPause2.setOnClickListener {
            viewModel.pause(TimerId.Timer2)
        }

        scope.launch {
            viewModel.ticker.collect {
                binding.textTime.text = it
            }
        }
        scope.launch {
            viewModel.ticker2.collect {
                binding.textTime2.text = it
            }
        }
    }

}