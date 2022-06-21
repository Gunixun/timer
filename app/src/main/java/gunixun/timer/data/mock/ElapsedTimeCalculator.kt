package gunixun.timer.data.mock

import gunixun.timer.data.utils.TimerState

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: TimerState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart =
            if (currentTimestamp > state.startTime) {
                currentTimestamp - state.startTime
            } else {
                0
            }
        return timePassedSinceStart + state.elapsedTime
    }
}