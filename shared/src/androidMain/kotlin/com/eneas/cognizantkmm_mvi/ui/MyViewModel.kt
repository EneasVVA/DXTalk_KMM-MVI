package com.eneas.cognizantkmm_mvi.ui

import androidx.lifecycle.ViewModel
import com.eneas.cognizantkmm_mvi.core.Either
import com.eneas.cognizantkmm_mvi.core.Executor
import com.eneas.cognizantkmm_mvi.redux.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual abstract class MyViewModel<V : State> : ViewModel(), KoinComponent {

    private var job = SupervisorJob()

    private val executor: Executor by inject()

    protected actual val viewModelScope: CoroutineScope get() = CoroutineScope(job + executor.main)

    actual abstract val viewState: StateFlow<V>

    actual open fun attach() {
    }

    actual open fun detach() {
        job.cancelChildren()
    }

    protected suspend fun <T> execute(f: suspend () -> Either<Error, T>): Either<Error, T> =
        withContext(executor.bg) { f() }

    fun <T> Flow<T>.observe(onChange: ((T) -> Unit)) {
        onEach {
            onChange(it)
        }.launchIn(
            viewModelScope
        )
    }
}