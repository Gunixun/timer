package gunixun.timer.ui.timer

import androidx.lifecycle.ViewModel
import gunixun.timer.ui.utils.TimerId
import kotlinx.coroutines.flow.StateFlow

class TimerContract {
    abstract class TimerViewModel: ViewModel() {
        abstract val ticker: StateFlow<String>
        abstract val ticker2: StateFlow<String>
        abstract  fun start(id: TimerId)
        abstract  fun stop(id: TimerId)
        abstract  fun pause(id: TimerId)
    }
}