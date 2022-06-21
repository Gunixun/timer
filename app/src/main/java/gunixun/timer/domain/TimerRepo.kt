package gunixun.timer.domain

interface TimerRepo {
    fun start(id: Int)
    fun stop(id: Int)
    fun pause(id: Int)
    fun getTime(id: Int): Long
}