package androis.example.filmapp.presentation.utils.provider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes res: Int, vararg args: Any): String
}