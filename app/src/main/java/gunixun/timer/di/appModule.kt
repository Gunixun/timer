package gunixun.timer.di

import gunixun.timer.data.mock.ElapsedTimeCalculator
import gunixun.timer.data.mock.MockTimerRepoImpl
import gunixun.timer.data.mock.TimerStateCalculator
import gunixun.timer.data.mock.TimestampProvider
import gunixun.timer.domain.TimerRepo
import gunixun.timer.ui.timer.TimerContract
import gunixun.timer.ui.timer.TimerViewModel
import gunixun.timer.ui.utils.TimestampMillisecondsFormatter
import org.koin.androidx.viewmodel.dsl.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val appModule = module {

    factory { TimestampProvider() }
    factory { ElapsedTimeCalculator(timestampProvider=get()) }
    single { TimestampMillisecondsFormatter() }

    factory { TimerStateCalculator(
        timestampProvider = get(),
        elapsedTimeCalculator = get(),
    ) }

    single<TimerRepo> {
        MockTimerRepoImpl(
            timerStateCalculator = get(),
            elapsedTimeCalculator = get(),
        )
    }

    viewModel<TimerContract.TimerViewModel> {
        TimerViewModel(
            scope = CoroutineScope(Dispatchers.Main),
            timerFormatter = get(),
            timerRepo = get()
        )
    }
}