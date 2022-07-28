package androis.example.filmapp.presentation.utils

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

open class DelegationAdapter<T : List<*>>(
    vararg delegates: AdapterDelegate<T>,
    observable: Pair<StateFlow<T>, CoroutineScope>? = null
) : ListDelegationAdapter<T>(*delegates) {

    private var flowData: StateFlow<T>? = null

    private fun Pair<StateFlow<T>, CoroutineScope>.observe() {
        flowData = first
        first.onEach {
            items = it
            notifyDataSetChanged()
        }.launch(second)
    }

    init {
        observable?.observe()
    }
}

fun <T> Flow<T>.launch(scope: CoroutineScope) {
    scope.launch {
        this@launch.collect()
    }
}