package gunixun.timer.ui.timer

import gunixun.timer.domain.TimerRepo
import gunixun.timer.ui.utils.TimerId
import gunixun.timer.ui.utils.TimestampMillisecondsFormatter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class TimerViewModel(
    private val scope: CoroutineScope,
    private val timerFormatter: TimestampMillisecondsFormatter,
    private val timerRepo: TimerRepo
) : TimerContract.TimerViewModel() {
    private var job: Job? = null
    private var job2: Job? = null
    override val ticker = MutableStateFlow("")
    override val ticker2 = MutableStateFlow("")

    override fun start(id: TimerId) {
        if (id == TimerId.Timer1) {
            if (job == null) startJob(id)
        }else {
            if (job2 == null) startJob(id)
        }
        timerRepo.start(id.ordinal)
    }

    override fun stop(id: TimerId) {
        timerRepo.stop(id.ordinal)
        stopJob(id)
        clearValue(id)
    }

    override fun pause(id: TimerId) {
        timerRepo.pause(id.ordinal)
        stopJob(id)
    }

    private fun startJob(id: TimerId) {
        if (id == TimerId.Timer1) {
            job = scope.launch {
                while (isActive) {
                    ticker.value = timerFormatter.format(
                        timerRepo.getTime(id.ordinal)
                    )
                    delay(20)
                }
            }
        }else {
            job2 = scope.launch {
                while (isActive) {
                    ticker2.value = timerFormatter.format(
                        timerRepo.getTime(id.ordinal)
                    )
                    delay(20)
                }
            }
        }
    }

    private fun stopJob(id: TimerId) {
        if (id == TimerId.Timer1) {
            job?.cancel()
            job = null
        }else {
            job2?.cancel()
            job2 = null
        }
    }

    private fun clearValue(id: TimerId) {
        if (id == TimerId.Timer1){
            ticker.value = timerFormatter.format(0)
        } else {
            ticker2.value = timerFormatter.format(0)
        }

    }
}