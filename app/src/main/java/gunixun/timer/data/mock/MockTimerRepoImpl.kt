package gunixun.timer.data.mock

import android.os.Build
import androidx.annotation.RequiresApi
import gunixun.timer.data.utils.TimerState
import gunixun.timer.domain.TimerRepo

class MockTimerRepoImpl(
    private var timerStateCalculator: TimerStateCalculator,
    private var elapsedTimeCalculator: ElapsedTimeCalculator
): TimerRepo {
    private var states = HashMap<Int, TimerState>()
    private var defState = TimerState.Paused(0)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun start(id: Int) {
        states[id] = timerStateCalculator.calculateRunningState(states.getOrDefault(id, defState))
    }

    override fun stop(id: Int) {
        states[id] = TimerState.Paused(0)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun pause(id: Int) {
        states[id] = timerStateCalculator.calculatePausedState(states.getOrDefault(id, defState))
    }

    override fun getTime(id: Int): Long {
        if (id !in states){
            states[id] = TimerState.Paused(0)
        }
        return when (val currentState = states[id]) {
            is TimerState.Paused -> currentState.elapsedTime
            is TimerState.Running -> elapsedTimeCalculator.calculate(currentState)
            else -> {defState.elapsedTime}
        }
    }
}