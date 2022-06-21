package gunixun.timer.data.mock

class TimestampProvider {
    fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}